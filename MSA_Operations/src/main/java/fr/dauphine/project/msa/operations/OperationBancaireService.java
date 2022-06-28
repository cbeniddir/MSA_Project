package fr.dauphine.project.msa.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OperationBancaireService {

    @Autowired
    private OperationBancaireRepository operationBancaireRepository;
    private PublishService publishService;

    @Autowired
    public OperationBancaireService(PublishService publishService){
        this.publishService = publishService;
    }

    public OperationBancaire createOperation(OperationBancaire operationBancaire)  throws JsonProcessingException {
        operationBancaireRepository.save(operationBancaire);
        publishService.createOperationPublish(operationBancaire);
        return operationBancaire;
    }

    public Optional<OperationBancaire> updateOperation(Long id, OperationBancaire newOperationBancaire){
        return operationBancaireRepository.findOneById(id)
                .map(oldItem -> {
                    OperationBancaire updated = oldItem.updateWith(newOperationBancaire);
                    return operationBancaireRepository.save(updated);
                });
    }

    public void deleteOperationByIds(Iterable<Long> ids){
        operationBancaireRepository.deleteAllByIdInBatch(ids);
    }

    public List<OperationBancaire> getOperationsByIbanSource(String ibanSource){
        List<OperationBancaire> operations = operationBancaireRepository.findByIbanSource(ibanSource);
        return operations;
    }

    public List<OperationBancaire> getOperationsByIbanDestination(String ibanDestination){
        List<OperationBancaire> operations = operationBancaireRepository.findByIbanDestination(ibanDestination);
        return operations;
    }

    public List<OperationBancaire> getOperationsByMontant(BigDecimal montant){
        List<OperationBancaire> operations = operationBancaireRepository.findByMontant(montant);
        return operations;
    }

    public List<OperationBancaire> getOperationsByTypeOperation(String typeOperation){
        List<OperationBancaire> operations = operationBancaireRepository.findByTypeOperation(typeOperation);
        return operations;
    }
}
