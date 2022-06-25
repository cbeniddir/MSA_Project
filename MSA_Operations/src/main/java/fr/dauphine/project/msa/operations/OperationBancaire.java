package fr.dauphine.project.msa.operations;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

// Classe persistente representant  une opération bancaire
@Entity
public class OperationBancaire {

    @Id
    private Long id;

    private String typeOperation;

    private String ibanSource;

    private String ibanDestination;

    private BigDecimal montant;

    private String date;

    public OperationBancaire() {

    }

    public OperationBancaire(Long id, String typeOperation, String ibanSource, String ibanDestination, BigDecimal montant, String date) {
        super();
        this.id = id;
        this.typeOperation = typeOperation;
        this.ibanSource = ibanSource;
        this.ibanDestination = ibanDestination;
        this.montant = montant;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public String getIbanSource() {
        return ibanSource;
    }

    public String getIbanDestination() {
        return ibanDestination;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public String getDate() {
        return date;
    }

    public OperationBancaire updateWith(OperationBancaire item) {
        return new OperationBancaire(
                this.id,
                item.typeOperation,
                item.ibanSource,
                item.ibanDestination,
                item.montant,
                item.date
        );
    }

}