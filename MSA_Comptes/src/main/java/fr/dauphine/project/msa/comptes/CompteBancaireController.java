package fr.dauphine.project.msa.comptes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CompteBancaireController {

  @Autowired
  private Environment environment;

  @Autowired
  private CompteBancaireRepository compteBancaireRepository;

  @Autowired
  private CompteBancaireService service;

  public CompteBancaireController(CompteBancaireService service) {
    this.service = service;
  }

  //Create an instance of account and insert it into database
  @PostMapping(path = "/create-account")
  public void createCompte(@RequestBody CompteBancaire compteBancaire){
    service.create(compteBancaire);
  }

  //Get an account informations by iban
  @GetMapping("/get-compte-by-iban/{iban}")
  public CompteBancaire getCompteByIban(@PathVariable String iban){
    CompteBancaire compteBancaire = compteBancaireRepository.findByIban(iban);
    return compteBancaire;
  }

  //Get account informations by id
  @GetMapping("/get-compte-by-id/{id}")
  public Optional<CompteBancaire> getCompteById(@PathVariable Long id){
    Optional<CompteBancaire> compteBancaire = compteBancaireRepository.findOneById(id);
    return compteBancaire;
  }

  //Get account informations of every accounts in the database
  @GetMapping("/get-all-comptes")
  public List<CompteBancaire> getAllComptes(){

    List<CompteBancaire> compteBancaire = compteBancaireRepository.findAll();

    return compteBancaire;
  }

  //Update account informations
  @PutMapping("/update-compte/{id}")
  public ResponseEntity<CompteBancaire> updateCompteById(@PathVariable("id") Long id, @RequestBody CompteBancaire updatedItem) {

    Optional<CompteBancaire> updated = service.update(id, updatedItem);

    return updated
            .map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> {
              CompteBancaire created = compteBancaireRepository.save(updatedItem);
              URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(created.getId())
                      .toUri();
              return ResponseEntity.created(location).body(created);
            });
  }

  @DeleteMapping("/delete-compte/{id}")
  public void deleteCompte(@PathVariable Long id){
    service.deleteCompteById(id);
  }

  @DeleteMapping("/delete-comptes")
  public void deleteComptes(@RequestBody Map<String, List<Long>> map){
    List<Long> idList = map.get("ids");
    service.deleteCompteByIds(idList);
  }

}