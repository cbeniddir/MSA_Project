package fr.dauphine.miageIf.projet.MSA;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteBancaireService {
    private CompteBancaireRepository compteBancaireRepository;

    public CompteBancaireService(CompteBancaireRepository compteBancaireRepository){
        this.compteBancaireRepository = compteBancaireRepository;
    }

    public Optional<CompteBancaire> update(Long id, CompteBancaire newCompteBancaire){
        return compteBancaireRepository.findOneById(id)
                .map(oldItem -> {
                    CompteBancaire updated = oldItem.updateWith(newCompteBancaire);
                    return compteBancaireRepository.save(updated);
                });
    }

}
