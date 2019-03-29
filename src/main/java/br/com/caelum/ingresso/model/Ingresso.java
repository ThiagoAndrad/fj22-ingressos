package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.descontos.Desconto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ingresso {

    private Sessao sessao;
    private BigDecimal preco = BigDecimal.ZERO;

    public Ingresso(Sessao sessao, Desconto desconto) {
        this.sessao = sessao;
        this.preco = desconto.aplicarDescontoSobre(sessao.getPreco());
    }

    /**
     * @deprecated hibernate only
     */
    public Ingresso() {
    }

    public BigDecimal getPreco() {
        return preco.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
