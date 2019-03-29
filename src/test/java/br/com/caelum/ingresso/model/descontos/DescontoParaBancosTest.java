package br.com.caelum.ingresso.model.descontos;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class DescontoParaBancosTest {

    @Test
    public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos() {

        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
        Ingresso ingresso = new Ingresso(sessao, new DescontoParaBancos());

        BigDecimal precoEsperado = new BigDecimal("22.75");

        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }

}