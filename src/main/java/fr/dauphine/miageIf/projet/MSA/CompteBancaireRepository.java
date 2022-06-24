package fr.dauphine.miageIf.projet.MSA;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Creation du JPA Repository basé sur Spring Data
//  Permet de "générer" toutes sortes de requêtes JPA, sans implementation
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long>{
    CompteBancaire findByIban(String iban);
    List<CompteBancaire> findAll();
    CompteBancaire save(CompteBancaire compteBancaire);
    Optional<CompteBancaire> findOneById(Long id);

}