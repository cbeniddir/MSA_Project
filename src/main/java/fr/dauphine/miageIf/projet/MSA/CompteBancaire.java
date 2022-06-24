package fr.dauphine.miageIf.projet.MSA;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompteBancaire {

    @Id
    private Long id;

    private String iban;

    private String type;

    private BigDecimal interet;

    private String frais;

    public CompteBancaire() {

    }

    public CompteBancaire(Long id, String iban, String type, BigDecimal interet, String frais) {
        super();
        this.id = id;
        this.iban = iban;
        this.type = type;
        this.interet = interet;
        this.frais = frais;
    }

    public Long getId() {
        return id;
    }

    public String getIban() {
        return iban;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getInteret() {
        return interet;
    }

    public String getFrais() {
        return frais;
    }

    public CompteBancaire updateWith(CompteBancaire item) {
        return new CompteBancaire(
                this.id,
                item.iban,
                item.type,
                item.interet,
                item.frais
        );
    }

}