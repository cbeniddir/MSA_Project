package fr.dauphine.miageIf.projet.MSA;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

// Classe persistente representant  une opération bancaire
@Entity
public class OperationBancaire {

    @Id
    private Long id;

    private String type_operation;

    private String iban_source;

    private String iban_destination;

    private BigDecimal montant;

    private String date;

    public OperationBancaire() {

    }

    public OperationBancaire(Long id, String type_operation, String iban_source, String iban_destination, BigDecimal montant, String date) {
        super();
        this.id = id;
        this.type_operation = type_operation;
        this.iban_source = iban_source;
        this.iban_destination = iban_destination;
        this.montant = montant;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTypeOperation() {
        return type_operation;
    }

    public String getIbanSource() {
        return iban_source;
    }

    public String getIbanDestination() {
        return iban_destination;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public String getDate() {
        return date;
    }

}