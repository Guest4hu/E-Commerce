package br.com.bradescoSantanderDio.DesignPattern.service.facade;

import br.com.bradescoSantanderDio.DesignPattern.dto.PedidoRequestDTO;
import br.com.bradescoSantanderDio.DesignPattern.dto.PedidoResponseDTO;
import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;
import br.com.bradescoSantanderDio.DesignPattern.model.Pedido;
import br.com.bradescoSantanderDio.DesignPattern.repository.PedidoRepository;
import br.com.bradescoSantanderDio.DesignPattern.service.factory.FreteFactory;
import br.com.bradescoSantanderDio.DesignPattern.service.factory.ServicoFrete;
import br.com.bradescoSantanderDio.DesignPattern.service.strategy.EstrategiaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CheckoutFacade {

    @Autowired
    private PedidoRepository repository;

    // 🌟 PADRÃO STRATEGY: O Spring injeta automaticamente o Pix e o Cartão aqui
    @Autowired
    private Map<String, EstrategiaPagamento> estrategiasPagamento;

    // 🌟 PADRÃO FACTORY: Fábrica responsável por decidir qual frete chamar
    @Autowired
    private FreteFactory freteFactory;

    public PedidoResponseDTO processarCheckout(PedidoRequestDTO request) {

        System.out.println("--- Iniciando processamento do Checkout ---");

        // 1. CALCULA O FRETE (Via Factory -> ViaCEP)
        // A fábrica decide se cria FreteNormal ou FreteExpresso baseado no Enum
        ServicoFrete calculadorDeFrete = freteFactory.obterServico(request.getTipoFrete());
        BigDecimal valorFrete = calculadorDeFrete.calcular(request.getCep());

        // Soma frete + valor dos itens
        BigDecimal valorTotal = request.getValorItens().add(valorFrete);

        // 2. PROCESSA O PAGAMENTO (Via Strategy)
        // Busca no Map a estratégia certa (PIX ou CARTAO_CREDITO) de forma dinâmica
        EstrategiaPagamento estrategia = estrategiasPagamento.get(request.getTipoPagamento().name());

        if (estrategia == null) {
            throw new IllegalArgumentException("Meio de pagamento não suportado: " + request.getTipoPagamento());
        }

        StatusPagamento status = estrategia.processar(valorTotal);

        // 3. SALVA NO BANCO DE DADOS (Via Repository)
        // Usamos nosso construtor inteligente que já joga a data de hoje e os Enums
        Pedido novoPedido = new Pedido(
                request.getCep(),
                request.getValorItens(),
                request.getTipoFrete(),
                request.getTipoPagamento()
        );
        novoPedido.setValorFrete(valorFrete); // Adicionando o frete calculado
        novoPedido.setStatusPagamento(status); // Adicionando o status do pagamento (APROVADO/RECUSADO)

        // O banco salva e devolve o objeto com o ID preenchido
        Pedido pedidoSalvo = repository.save(novoPedido);

        System.out.println("--- Checkout finalizado com sucesso ---");

        // 4. DEVOLVE A RESPOSTA LIMPA PARA O CLIENTE (DTO)
        return new PedidoResponseDTO(
                pedidoSalvo.getId(),
                pedidoSalvo.getValorFrete(),
                valorTotal,
                pedidoSalvo.getStatusPagamento(),
                "Pedido orquestrado e processado com sucesso!"
        );
    }
}
