package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class GerenciadorDeSessaoTest {

    private Filme rogueOne;
    private Sala sala3D;
    private Sessao sessaoDasDez;
    private Sessao sessaoDasTreze;
    private Sessao sessaoDasDezoito;

    @Before
    public void setUp() throws Exception {
        rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", BigDecimal.ONE);
        sala3D = new Sala("Sala 3D", BigDecimal.TEN);
        sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), sala3D, rogueOne);
        sessaoDasTreze = new Sessao(LocalTime.parse("13:00:00"), sala3D, rogueOne);
        sessaoDasDezoito = new Sessao(LocalTime.parse("18:00:00"), sala3D, rogueOne);
    }

    @Test
    public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
        List<Sessao> sessoes = Arrays.asList(sessaoDasDez);

        GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

        Assert.assertFalse(gerenciador.cabe(sessaoDasDez));
    }

    @Test
    public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente() {
        List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
        Sessao sessao = new Sessao(sessaoDasDez.getHorario().minusHours(1), sala3D, rogueOne);

        GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

        Assert.assertFalse(gerenciador.cabe(sessao));
    }

    @Test
    public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente() {
        List<Sessao> sessoesDaSala = Arrays.asList(sessaoDasDez);
        Sessao sessao = new Sessao(sessaoDasDez.getHorario().plusHours(1), sala3D, rogueOne);

        GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);

        Assert.assertFalse(gerenciador.cabe(sessao));
    }

    @Test
    public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes() {
        List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);

        GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

        Assert.assertTrue(gerenciador.cabe(sessaoDasTreze));
    }
}