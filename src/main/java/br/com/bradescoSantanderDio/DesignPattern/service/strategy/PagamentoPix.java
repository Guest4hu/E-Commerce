package br.com.bradescoSantanderDio.DesignPattern.service.strategy;


import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("PIX")
public class PagamentoPix implements EstrategiaPagamento {


    @Override
    public StatusPagamento processar(BigDecimal valorTotal) {

        //Simulando sistema simples de pagamento Pix

        return StatusPagamento.PAGO;
    }
}