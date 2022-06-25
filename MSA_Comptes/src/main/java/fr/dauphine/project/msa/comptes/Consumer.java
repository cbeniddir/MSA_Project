package fr.dauphine.project.msa.comptes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    private static final String orderTopic = "${topic.name}";

    private final ObjectMapper objectMapper;
    private final CompteBancaireService compteBancaireService;

    @Autowired
    public Consumer(ObjectMapper objectMapper, CompteBancaireService compteBancaireService) {
        this.objectMapper = objectMapper;
        this.compteBancaireService = compteBancaireService;
    }

    @KafkaListener(topics = orderTopic)
    public void consumeMessage(String message) throws JsonProcessingException {
        log.info("message consumed {}", message);

        OperationDto operationDto = objectMapper.readValue(message, OperationDto.class);
        compteBancaireService.updateCompteSource(operationDto);
        compteBancaireService.updateCompteDestination(operationDto);
    }

}