package fr.dauphine.project.msa.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishService {
    private final Producer producer;

    @Autowired
    public PublishService(Producer producer) {
        this.producer = producer;
    }

    public String createOperationPublish(OperationBancaire operationBancaire) throws JsonProcessingException {
        return producer.sendMessage(operationBancaire);
    }

}
