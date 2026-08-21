package br.com.bradescoSantanderDio.DesignPattern.dto;

import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;

import java.math.BigDecimal;
import java.util.UUID;

public class PedidoResponseDTO {

    private UUID idPedido;
    private BigDecimal valorFrete;
    private BigDecimal valorTotal;
    private StatusPagamento statusPagamento;
    private String mensagemGeral;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(UUID id, BigDecimal valorFrete, BigDecimal valorTotal, StatusPagamento statusPagamento, String mensagemGeral) {
        this.idPedido = id;
        this.valorFrete = valorFrete;
        this.valorTotal = valorTotal;
        this.statusPagamento = statusPagamento;
        this.mensagemGeral = mensagemGeral;
    }


    public UUID getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(UUID idPedido) {
        this.idPedido = idPedido;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getMensagemGeral() {
        return mensagemGeral;
    }

    public void setMensagemGeral(String mensagemGeral) {
        this.mensagemGeral = mensagemGeral;
    }
}