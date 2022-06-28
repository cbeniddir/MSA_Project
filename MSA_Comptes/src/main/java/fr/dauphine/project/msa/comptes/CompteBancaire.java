package fr.dauphine.project.msa.comptes;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String iban;

    private String type;

    private BigDecimal interet;

    private String frais;

    private BigDecimal solde;

    public CompteBancaire() {

    }

    public CompteBancaire(Long id, String iban, String type, BigDecimal interet, String frais, BigDecimal solde) {
        super();
        this.id = id;
        this.iban = iban;
        this.type = type;
        this.interet = interet;
        this.frais = frais;
        this.solde = solde;
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

    public BigDecimal getSolde() { return solde; }

    public CompteBancaire updateWith(CompteBancaire item) {
        return new CompteBancaire(
                this.id,
                item.iban,
                item.type,
                item.interet,
                item.frais,
                item.solde
        );
    }

}