package br.com.bradescoSantanderDio.DesignPattern.service.factory;

import java.math.BigDecimal;

public interface ServicoFrete {
    BigDecimal calcular(String cep);
}