package fr.dauphine.project.msa.comptes;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
public class OperationDto {
    BigDecimal montant;
    String date;
    String typeOperation;
    String ibanSource;
    String ibanDestination;
}
