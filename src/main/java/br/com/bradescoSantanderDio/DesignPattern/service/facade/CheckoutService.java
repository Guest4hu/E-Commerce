package br.com.bradescoSantanderDio.DesignPattern.service.facade;

import br.com.bradescoSantanderDio.DesignPattern.dto.PedidoRequestDTO;
import br.com.bradescoSantanderDio.DesignPattern.dto.PedidoResponseDTO;
import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoFrete;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoPagamento;
import br.com.bradescoSantanderDio.DesignPattern.model.Pedido;
import br.com.bradescoSantanderDio.DesignPattern.repository.PedidoRepository;
import br.com.bradescoSantanderDio.DesignPattern.service.factory.FreteFactory;
import br.com.bradescoSantanderDio.DesignPattern.service.factory.ServicoFrete;
import br.com.bradescoSantanderDio.DesignPattern.service.strategy.EstrategiaPagamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
public class CheckoutService {


    @Autowired
    private PedidoRepository repository;

    @Autowired
    private Map<String, EstrategiaPagamento> estrategiasPagamento;

    @Autowired
    private FreteFactory freteFactory;

    public PedidoResponseDTO processarCheckout(PedidoRequestDTO request) {

        BigDecimal valorFrete = calcularValorDoFrete(request.tipoFrete(), request.cep());
        BigDecimal valorTotal = request.valorItens().add(valorFrete);

        StatusPagamento status = processarPagamento(request.tipoPagamento(), valorTotal);

        Pedido pedidoSalvo = salvarPedidoNoBanco(request, valorFrete, status);

        log.info("Checkout do pedido ID {} finalizado com sucesso.", pedidoSalvo.getId());

        return converterParaResponseDTO(pedidoSalvo, valorTotal);
    }


    private BigDecimal calcularValorDoFrete(TipoFrete tipoFrete, String cep) {
        ServicoFrete calculadorDeFrete = freteFactory.obterServico(tipoFrete);
        return calculadorDeFrete.calcular(cep);
    }

    private StatusPagamento processarPagamento(TipoPagamento tipoPagamento, BigDecimal valorTotal) {
        EstrategiaPagamento estrategia = estrategiasPagamento.get(tipoPagamento.name());

        if (estrategia == null) {
            log.error("Tentativa de checkout com meio de pagamento inválido: {}", tipoPagamento);
            throw new IllegalArgumentException("Meio de pagamento não suportado: " + tipoPagamento);
        }

        return estrategia.processar(valorTotal);
    }

    private Pedido salvarPedidoNoBanco(PedidoRequestDTO request, BigDecimal valorFrete, StatusPagamento status) {
        Pedido novoPedido = new Pedido(
                request.cep(),
                request.valorItens(),
                request.tipoFrete(),
                request.tipoPagamento()
        );
        novoPedido.setValorFrete(valorFrete);
        novoPedido.setStatusPagamento(status);

        return repository.save(novoPedido);
    }

    private PedidoResponseDTO converterParaResponseDTO(Pedido pedido, BigDecimal valorTotal) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getValorFrete(),
                valorTotal.toString(),
                pedido.getStatusPagamento().toString(),
                pedido.getTipoPagamento().toString()
        );
    }

}
