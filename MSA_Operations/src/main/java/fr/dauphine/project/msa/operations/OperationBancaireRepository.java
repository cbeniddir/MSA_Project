package fr.dauphine.project.msa.operations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OperationBancaireRepository extends JpaRepository<OperationBancaire, Long> {
    List<OperationBancaire> findAll();
    OperationBancaire save(OperationBancaire operationBancaire);
    Optional<OperationBancaire> findOneById(Long id);
    List<OperationBancaire> findByIbanDestination(String ibanDestination);
    List<OperationBancaire> findByIbanSource(String ibanSource);
    List<OperationBancaire> findByMontant(BigDecimal montant);
    List<OperationBancaire> findByTypeOperation(String typeOperation);


}