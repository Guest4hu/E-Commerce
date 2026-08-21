package br.com.bradescoSantanderDio.DesignPattern.model;

import br.com.bradescoSantanderDio.DesignPattern.enums.StatusPagamento;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoFrete;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    private UUID id;

    private String cepDestino;

    private BigDecimal valorItens;
    private BigDecimal valorFrete;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFrete tipoFrete;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento statusPagamento;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;


    @ManyToOne
    @JoinColumn(name = "usuario_id") // Opcional, mas deixa claro o nome da coluna no banco
    private Usuario usuario;



    // Construtor vazio (obrigatório para o JPA)
    public Pedido() {
        this.dataCriacao = LocalDateTime.now();
        this.statusPagamento = StatusPagamento.AGUARDANDO;
    }

    public Pedido(String cepDestino, BigDecimal valorItens, TipoFrete tipoFrete, TipoPagamento tipoPagamento) {
        this();
        this.cepDestino = cepDestino;
        this.valorItens = valorItens;
        this.tipoFrete = tipoFrete;
        this.tipoPagamento = tipoPagamento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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