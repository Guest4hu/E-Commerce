package br.com.bradescoSantanderDio.DesignPattern.service.strategy;


import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("PIX")
public class PagamentoPix implements EstrategiaPagamento {

    @Override
    public StatusPagamento processar(BigDecimal valorTotal) {

        System.out.println("[PIX] Conectando ao Banco Central...");
        System.out.println("[PIX] Gerando QR Code / Chave Copia e Cola no valor de R$ " + valorTotal);

        System.out.println("[PIX] Pagamento processado com sucesso!");

        return StatusPagamento.PAGO;
    }
}