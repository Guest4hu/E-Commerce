package br.com.bradescoSantanderDio.DesignPattern.service.strategy;

import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

@Service("CARTAO")
public class PagamentoCartao implements EstrategiaPagamento {

    @Override
    public StatusPagamento processar(BigDecimal valorTotal) {
        // Simulando a lógica de negócio do Cartão
        System.out.println("[CARTÃO] Conectando com a adquirente (Cielo/Rede)...");
        System.out.println("[CARTÃO] Validando limite para o valor de R$ " + valorTotal);

        // Simulando uma regra: se o valor for maior que R$ 5.000,00, bloqueia a compra por segurança (Anti-fraude)
        BigDecimal limiteDeSeguranca = new BigDecimal("5000.00");

        if (valorTotal.compareTo(limiteDeSeguranca) > 0) {
            System.out.println("[CARTÃO] Pagamento RECUSADO! Valor acima do limite de segurança.");
            return StatusPagamento.CANCELADO;
        }

        System.out.println("[CARTÃO] Pagamento APROVADO!");
        return StatusPagamento.PAGO;
    }
}