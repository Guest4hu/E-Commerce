package br.com.bradescoSantanderDio.DesignPattern.dto;

import br.com.bradescoSantanderDio.DesignPattern.enums.TipoFrete;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoPagamento;

import java.math.BigDecimal;

public record PedidoRequestDTO(String cep, BigDecimal valorItens, TipoFrete tipoFrete, TipoPagamento tipoPagamento) {

}
