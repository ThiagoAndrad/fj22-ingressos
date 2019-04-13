package br.com.caelum.ingresso.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Ingresso> ingressos = new ArrayList<>();

    @Deprecated
    private Compra() {}

    public Compra(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
}
