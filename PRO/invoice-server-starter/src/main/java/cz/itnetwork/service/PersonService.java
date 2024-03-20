package cz.itnetwork.service;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatDTO;

import java.util.List;

/**
 * PersonService poskytuje abstraktní vrstvu pro operace související s osobami.
 * Definuje metody pro přidávání, odstraňování, získávání a úpravu osob,
 * a také pro získání statistických údajů o osobách. Toto rozhraní slouží jako kontrakt,
 * který musí být implementován službami, jež se zabývají logikou práce s osobami.
 */
public interface PersonService {


    /**
     * Přidá novou osobu do systému na základě poskytnutých údajů.
     *
     * @param personDTO Data nově přidávané osoby.
     * @return Objekt PersonDTO reprezentující přidanou osobu.
     */
    PersonDTO addPerson(PersonDTO personDTO);


    /**
     * Odstraní osobu ze systému na základě jejího identifikačního čísla.
     *
     * @param id Identifikační číslo osoby, která má být odstraněna.
     */
    void removePerson(long id);


    /**
     * Získá seznam všech osob v systému.
     *
     * @return Seznam všech osob jako List<PersonDTO>.
     */
    List<PersonDTO> getAll();


    /**
     * Získá údaje o konkrétní osobě na základě jejího identifikačního čísla.
     *
     * @param id Identifikační číslo požadované osoby.
     * @return Objekt PersonDTO reprezentující požadovanou osobu.
     */
    PersonDTO getPerson(long id);


    /**
     * Upraví údaje o existující osobě.
     *
     * @param personId Identifikační číslo osoby, jejíž údaje mají být upraveny.
     * @param personDTO Nové údaje pro osobu.
     * @return Objekt PersonDTO s aktualizovanými údaji.
     */
    PersonDTO editPerson(Long personId, PersonDTO personDTO);


    /**
     * Získá statistické údaje o osobách.
     *
     * @return Seznam statistických údajů jako List<PersonStatDTO>.
     */
    List<PersonStatDTO> getPersonStatistics();

    Long getRevenue(long personId);
}
