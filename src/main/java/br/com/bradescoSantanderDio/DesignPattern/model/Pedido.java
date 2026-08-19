package br.com.bradescoSantanderDio.DesignPattern.model;

import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoFrete;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cepDestino;

    private BigDecimal valorItens;
    private BigDecimal valorFrete;

    @Enumerated(EnumType.STRING)
    private TipoFrete tipoFrete;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    private LocalDateTime dataCriacao;

    // Construtor vazio (obrigatório para o JPA)
    public Pedido() {
        this.dataCriacao = LocalDateTime.now();
        this.statusPagamento = statusPagamento.AGUARDANDO;
    }

    public Pedido(String cepDestino, BigDecimal valorItens, TipoFrete tipoFrete, TipoPagamento tipoPagamento) {
        this();
        this.cepDestino = cepDestino;
        this.valorItens = valorItens;
        this.tipoFrete = tipoFrete;
        this.tipoPagamento = tipoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public BigDecimal getValorItens() {
        return valorItens;
    }

    public void setValorItens(BigDecimal valorItens) {
        this.valorItens = valorItens;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public TipoFrete getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(TipoFrete tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}