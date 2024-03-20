package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * PersonRepository je rozhraní pro přístup k databázi pro entitu PersonEntity.
 * Rozšiřuje JpaRepository, což poskytuje sadu standardních metod pro CRUD operace,
 * a také přidává specifické metody pro hledání osob na základě různých kritérií.
 *
 * Toto rozhraní slouží jako most mezi aplikační logikou a databázovou vrstvou,
 * což umožňuje snadné a efektivní provádění databázových operací bez přímého psaní SQL.
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {


    /**
     * Najde všechny osoby na základě hodnoty jejich atributu 'hidden'.
     *
     * @param hidden Určuje, zda hledat skryté osoby (true) nebo ne (false).
     * @return Seznam entit PersonEntity, které odpovídají kritériím.
     */
    List<PersonEntity> findByHidden(boolean hidden);


    /**
     * Najde osoby na základě jejich identifikačního čísla.
     *
     * @return Seznam entit PersonEntity s daným identifikačním číslem.
     */
    List<PersonEntity> findByIdentificationNumber(String personDTO);
}
