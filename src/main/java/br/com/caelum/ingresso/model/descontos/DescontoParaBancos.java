package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoParaBancos implements Desconto {

    @Override
    public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
        return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));
    }

    @Override
    public String getDescricao() {
        return "Desconto Banco";
    }

    private BigDecimal trintaPorCentoSobre(BigDecimal valor) {
        return valor.multiply(new BigDecimal("0.3"));
    }
}
