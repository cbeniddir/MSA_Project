package fr.dauphine.miageIf.projet.MSA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@RestController
public class OperationBancaireController {

    @Autowired
    private Environment environment;

    @Autowired
    private OperationBancaireRepository operationBancaireRepository;

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
    public OperationBancaire createCompte(@RequestParam String typeOperation, @RequestParam String ibanSource, @RequestParam String ibanDestination, @RequestParam BigDecimal montant, @RequestParam String date){

        Random rd = new Random();
        Long id = rd.nextLong();
        OperationBancaire operationBancaire = new OperationBancaire(id, typeOperation, ibanSource, ibanDestination, montant, date);

        operationBancaireRepository.save(operationBancaire);

        return operationBancaire;
    }

    //Update an operation

    //Get list of operations by date

    //Get list of operations by iban_destination

    //Get list of operations by iban_source

    //Get list of operations by montant

    //Get list of operations by type
}
