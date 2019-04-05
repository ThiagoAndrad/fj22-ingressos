package br.com.caelum.ingresso.model.descontos;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class SemDescontoTest {

    @Test
    public void naoDeveConcederDescontoParaIngressoNormal() {
        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
                "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
        Lugar lugar = new Lugar("A",1);
        Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);

        BigDecimal precoEsperado = new BigDecimal("32.50");

        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }
}