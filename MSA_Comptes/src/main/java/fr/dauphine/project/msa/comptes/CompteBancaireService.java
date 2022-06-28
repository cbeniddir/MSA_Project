package fr.dauphine.project.msa.comptes;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class CompteBancaireService {
    private CompteBancaireRepository compteBancaireRepository;
    private final ModelMapper modelMapper;

    public CompteBancaireService(CompteBancaireRepository compteBancaireRepository, ModelMapper modelMapper){
        this.compteBancaireRepository = compteBancaireRepository;
        this.modelMapper = modelMapper;
    }

    public CompteBancaire create(CompteBancaire compteBancaire){
        return compteBancaireRepository.save(compteBancaire);
    }

    public Optional<CompteBancaire> update(Long id, CompteBancaire newCompteBancaire){
        return compteBancaireRepository.findOneById(id)
                .map(oldItem -> {
                    CompteBancaire updated = oldItem.updateWith(newCompteBancaire);
                    return compteBancaireRepository.save(updated);
                });
    }

    public void updateCompteSource(OperationDto operationDto) {
        CompteBancaire compteSource = compteBancaireRepository.findByIban(operationDto.ibanSource);
        BigDecimal newSolde = compteSource.getSolde().add(operationDto.montant);
        CompteBancaire newCompteBancaire = new CompteBancaire(compteSource.getIban(), compteSource.getType(), compteSource.getInteret(), compteSource.getFrais(), newSolde);
        update(compteSource.getId(), newCompteBancaire);
        log.info("account persisted {}", newCompteBancaire);
    }

    public void updateCompteDestination(OperationDto operationDto) {
        CompteBancaire compteSource = compteBancaireRepository.findByIban(operationDto.ibanDestination);
        BigDecimal newSolde = compteSource.getSolde().subtract(operationDto.montant);
        CompteBancaire newCompteBancaire = new CompteBancaire(compteSource.getIban(), compteSource.getType(), compteSource.getInteret(), compteSource.getFrais(), newSolde);
        update(compteSource.getId(), newCompteBancaire);
        log.info("account persisted {}", newCompteBancaire);
    }

    public void deleteCompteById(Long id){
        compteBancaireRepository.deleteById(id);
    }

    public void deleteCompteByIds(Iterable<Long> ids){
        compteBancaireRepository.deleteAllByIdInBatch(ids);
    }

}
