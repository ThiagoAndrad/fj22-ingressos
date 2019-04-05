package br.com.caelum.ingresso.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Sessao sessao;

    @Enumerated(EnumType.STRING)
    private TipoDeIngresso tipoDeIngresso;

    private BigDecimal preco = BigDecimal.ZERO;

    @ManyToOne
    private Lugar lugar;

    public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
        this.sessao = sessao;
        this.tipoDeIngresso = tipoDeIngresso;
        this.preco = tipoDeIngresso.aplicaDescontoSobreValor(sessao.getPreco());
        this.lugar = lugar;
    }

    /**
     * @deprecated hibernate only
     */
    public Ingresso() {
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getPreco() {
        return preco.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public TipoDeIngresso getTipoDeIngresso() {
        return tipoDeIngresso;
    }

    public Lugar getLugar() {
        return lugar;
    }
}
