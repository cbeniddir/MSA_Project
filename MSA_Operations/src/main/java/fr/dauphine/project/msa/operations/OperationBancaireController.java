package fr.dauphine.project.msa.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class OperationBancaireController {

    @Autowired
    private Environment environment;

    @Autowired
    private OperationBancaireRepository operationBancaireRepository;

    @Autowired
    private OperationBancaireService operationBancaireService;

    public OperationBancaireController() {

    }

    //Get list operations
    @GetMapping("/get-all-operations")
    public List<OperationBancaire> getAllOperations(){
        List<OperationBancaire> operations = operationBancaireRepository.findAll();
        return operations;
    }

    //Create an instance of operation and insert it into database
    @PostMapping(path = "/create-operation")
    public OperationBancaire createCompte(@RequestParam String typeOperation, @RequestParam String ibanSource, @RequestParam String ibanDestination, @RequestParam BigDecimal montant, @RequestParam String date) throws JsonProcessingException {
        return operationBancaireService.createOperation(typeOperation, ibanSource, ibanDestination, montant, date);
    }

    //Update an operation
    @PutMapping("/update-operation/{id}")
    public ResponseEntity<OperationBancaire> updateOperationById(@PathVariable("id") Long id, @RequestBody OperationBancaire updatedItem) {

        Optional<OperationBancaire> updated = operationBancaireService.updateOperation(id, updatedItem);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    OperationBancaire created = operationBancaireRepository.save(updatedItem);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }


    //Get list of operations by date

    //Get list of operations by iban_destination

    //Get list of operations by iban_source

    //Get list of operations by montant

    //Get list of operations by type
}
