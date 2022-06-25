package fr.dauphine.project.msa.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
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

    public OperationBancaire createOperation(String typeOperation, String ibanSource, String ibanDestination, BigDecimal montant, String date)  throws JsonProcessingException {
        Random rd = new Random();
        Long id = rd.nextLong();
        OperationBancaire operationBancaire = new OperationBancaire(id, typeOperation, ibanSource, ibanDestination, montant, date);
        operationBancaireRepository.save(operationBancaire);
        publishService.createOperationPublish(operationBancaire);
        return operationBancaire;
    }
}
