package fr.dauphine.miageIf.projet.MSA;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperationBancaireRepository extends JpaRepository<OperationBancaire, Long> {
    List<OperationBancaire> findAll();
    OperationBancaire save(CompteBancaire compteBancaire);
    Optional<OperationBancaire> findOneById(Long id);
}