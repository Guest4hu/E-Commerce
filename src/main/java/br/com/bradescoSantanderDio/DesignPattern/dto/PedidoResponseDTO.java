package br.com.bradescoSantanderDio.DesignPattern.dto;

import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;

import java.math.BigDecimal;
import java.util.UUID;

public record PedidoResponseDTO(UUID id, BigDecimal valorItens, String cep, String tipoFrete, String tipoPagamento) {

}