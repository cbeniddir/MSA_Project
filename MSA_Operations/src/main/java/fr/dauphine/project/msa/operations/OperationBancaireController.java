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
    public OperationBancaire createCompte(@RequestBody OperationBancaire operationBancaire) throws JsonProcessingException {
        return operationBancaireService.createOperation(operationBancaire);
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


    //Get list of operations by iban source
    @GetMapping("/get-operations-by-iban-source/{ibanSource}")
    public List<OperationBancaire> getOperationsByIbanSource(@PathVariable String ibanSource){
        List<OperationBancaire> operations = operationBancaireRepository.findByIbanSource(ibanSource);
        return operations;
    }

    //Get list of operations by iban_destination
    @GetMapping("/get-operations-by-iban-destination/{ibanDestination}")
    public List<OperationBancaire> getOperationsByIbanDestination(@PathVariable String ibanDestination){
        List<OperationBancaire> operations = operationBancaireRepository.findByIbanDestination(ibanDestination);
        return operations;
    }

    //Get list of operations by montant
    @GetMapping("/get-operations-by-montant/{montant}")
    public List<OperationBancaire> getOperationsByMontant(@PathVariable BigDecimal montant){
        List<OperationBancaire> operations = operationBancaireRepository.findByMontant(montant);
        return operations;
    }

    //Get list of operations by type operation
    @GetMapping("/get-operations-by-type-operation/{typeOperation}")
    public List<OperationBancaire> getOperationsByTypeOperation(@PathVariable String typeOperation){
        List<OperationBancaire> operations = operationBancaireRepository.findByTypeOperation(typeOperation);
        return operations;
    }
}
