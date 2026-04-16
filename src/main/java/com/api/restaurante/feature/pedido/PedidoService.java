package com.api.restaurante.feature.pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.api.restaurante.feature.cliente.Cliente;
import com.api.restaurante.feature.cliente.ClienteRepository;
import com.api.restaurante.feature.funcionario.Funcionario;
import com.api.restaurante.feature.funcionario.FuncionarioRepository;
import com.api.restaurante.feature.prato.Prato;
import com.api.restaurante.feature.prato.PratoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PratoRepository pratoRepository;

    @Transactional
    public PedidoDTOs.Response criarPedido(PedidoDTOs.Request request) {

        Cliente cliente = clienteRepository.findById(request.getClienteCpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado: " + request.getClienteCpf()));

        String funcionarioNome = "N/A"; //não tou entendendo segue com erro. mas o codigo funciona. =x
        if (request.getFuncionarioId() != null) {
            Funcionario func = funcionarioRepository.findById(request.getFuncionarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado: " + request.getFuncionarioId()));
            funcionarioNome = func.getNome(); //era pra retirar o erro =/
        }

        Pedido pedido = Pedido.builder()
                .clienteCpf(cliente.getCpf())
                .funcionarioId(request.getFuncionarioId())
                .valorTotal(BigDecimal.ZERO)
                .build();

        pedido = pedidoRepository.save(pedido);
        final Long pedidoIdGerado = pedido.getId();

        BigDecimal valorTotalAcumulado = BigDecimal.ZERO;

        // (N:M) - socorro.
        for (PedidoDTOs.ItemRequest itemReq : request.getItens()) {
            Prato prato = pratoRepository.findById(itemReq.getPratoId())
                    .orElseThrow(() -> new EntityNotFoundException("Prato ID " + itemReq.getPratoId() + " não existe"));

            ItemPedido item = ItemPedido.builder()
                    .pedidoId(pedidoIdGerado)
                    .pratoId(prato.getId())
                    .quantidade(itemReq.getQuantidade())
                    .precoUnitarioNoPedido(prato.getPreco())
                    .build();

            item.calcularSubtotal();
            valorTotalAcumulado = valorTotalAcumulado.add(item.getSubtotal());

            itemPedidoRepository.save(item);
        }

        // Atualiza o valor total
        pedido.setValorTotal(valorTotalAcumulado);
        pedidoRepository.save(pedido);

        return buscarPorId(pedidoIdGerado);
    }

    @Transactional(readOnly = true) //tem como melhorar ?
    public PedidoDTOs.Response buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado: " + id));

        Cliente cliente = clienteRepository.findById(pedido.getClienteCpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente do pedido sumiu do sistema"));

        String funcionarioNome = (pedido.getFuncionarioId() != null)
            ? funcionarioRepository.findById(pedido.getFuncionarioId()).map(Funcionario::getNome).orElse("N/A") : "N/A";

        List<ItemPedido> itens = itemPedidoRepository.findByPedidoId(pedido.getId());

        return mapToResponse(pedido, cliente.getNome(), funcionarioNome, itens);
    }

    private PedidoDTOs.Response mapToResponse(Pedido pedido, String clienteNome, String funcionarioNome, List<ItemPedido> itens) {
        return PedidoDTOs.Response.builder()
                .id(pedido.getId())
                .data(pedido.getData())
                .status(pedido.getStatus())
                .valorTotal(pedido.getValorTotal())
                .clienteNome(clienteNome)
                .funcionarioNome(funcionarioNome)
                .itens(itens.stream().map(this::mapToItemResponse).collect(Collectors.toList())) // vai da erro com muitos pedidos? testar!!!
                .build();
    }

    private PedidoDTOs.ItemResponse mapToItemResponse(ItemPedido item) {
        String pratoNome = pratoRepository.findById(item.getPratoId())
                .map(Prato::getNomePrato)
                .orElse("Prato Removido");

        return PedidoDTOs.ItemResponse.builder()
                .pratoId(item.getPratoId())
                .pratoNome(pratoNome)
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPrecoUnitarioNoPedido())
                .subtotal(item.getSubtotal())
                .build();
    }
}