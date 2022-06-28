package fr.dauphine.project.msa.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

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
}
