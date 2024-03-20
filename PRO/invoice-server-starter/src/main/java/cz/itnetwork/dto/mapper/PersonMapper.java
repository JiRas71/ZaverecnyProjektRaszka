package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.PersonEntity;
import org.mapstruct.Mapper;

/**
 * PersonMapper je rozhraní pro MapStruct, které definuje mapování mezi PersonEntity a PersonDTO.
 * Tento mapper automatizuje proces převodu dat mezi entitou a DTO, což zjednodušuje transformace
 * dat během operací CRUD v rámci aplikace. Anotace @Mapper signalizuje MapStruct frameworku, aby
 * v době kompilace vygeneroval implementaci tohoto rozhraní, což eliminuje potřebu manuálního
 * mapování mezi objekty a snižuje riziko chyb při transformaci dat.
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    /**
     * Převede PersonDTO na PersonEntity.
     * Tato metoda se používá, když přijímáme data osoby z API (ve formě DTO) a chceme je
     * uložit do databáze, což vyžaduje transformaci na entitu.
     *
     * @param source PersonDTO, zdrojový objekt, který obsahuje data osoby z API.
     * @return PersonEntity, cílová entita připravená pro uložení do databáze.
     */
    PersonEntity toEntity(PersonDTO source);


    /**
     * Převede PersonEntity na PersonDTO.
     * Tato metoda se používá, když chceme z databáze získat data o osobě a poslat je do API,
     * což vyžaduje transformaci entitních dat na strukturu DTO.
     *
     * @param source PersonEntity, zdrojový objekt, který obsahuje data osoby z databáze.
     * @return PersonDTO, cílový DTO objekt připravený k odeslání skrze API.
     */
    PersonDTO toDTO(PersonEntity source);
}
