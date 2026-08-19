package br.com.bradescoSantanderDio.DesignPattern.service.strategy;

import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;
import java.math.BigDecimal;

public interface EstrategiaPagamento {

    StatusPagamento processar(BigDecimal valorTotal);

}