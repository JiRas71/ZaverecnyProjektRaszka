package cz.itnetwork.controller;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatDTO;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller poskytující API endpointy pro správu osob v systému.
 * Umožňuje přidávání, mazání, editaci a získávání osob, jakož i získání statistik o osobách.
 */
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Přidá novou osobu do systému na základě poskytnutých údajů.
     *
     * @param personDTO Data nové osoby.
     * @return DTO nově přidané osoby.
     */
    @PostMapping("/persons")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {

        return  personService.addPerson(personDTO);
    }


    /**
     * Získá seznam všech osob v systému.
     *
     * @return Seznam všech osob.
     */
    @GetMapping("/persons")
    public List<PersonDTO> getPersons() {

        return personService.getAll();
    }


    /**
     * Odstraní osobu ze systému na základě jejího ID.
     *
     * @param personId ID osoby, která má být odstraněna.
     */
    @DeleteMapping("/persons/{personId}")
    public void deletePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
    }


    /**
     * Získá detailní informace o osobě na základě jejího ID.
     *
     * @param personId ID osoby, o které chceme získat informace.
     * @return DTO s informacemi o osobě.
     */
    @GetMapping("/persons/{personId}")
    public PersonDTO getPerson(@PathVariable Long personId) {

        return personService.getPerson(personId);
    }


    /**
     * Aktualizuje údaje o osobě na základě jejího ID a poskytnutých údajů.
     *
     * @param personId ID osoby, jejíž údaje chceme aktualizovat.
     * @param personDTO Nové údaje o osobě.
     * @return DTO aktualizované osoby.
     */
    @PutMapping("/persons/{personId}")
    public PersonDTO editPerson(@PathVariable Long personId, @RequestBody PersonDTO personDTO)  {

        return personService.editPerson(personId, personDTO);
    }


    /**
     * Získá statistiky o osobách v systému.
     *
     * @return ResponseEntity obsahující seznam DTO s statistikami o osobách.
     */
    @GetMapping("/persons/statistics")
    public ResponseEntity<List<PersonStatDTO>> getPersonStatistics() {
        List<PersonStatDTO> statistics = personService.getPersonStatistics();

        return ResponseEntity.ok(statistics);
    }
}
