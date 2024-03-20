package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;

/**
 * InvoiceMapper je rozhraní definující mapování mezi InvoiceEntity a InvoiceDTO.
 * Pomocí MapStruct frameworku, tento mapper automaticky generuje implementaci
 * pro převod dat mezi entitními třídami a DTOs.
 * Anotace @Mapper označuje, že rozhraní je mapovací rozhraní a umožňuje
 * MapStruct frameworku generovat kód v době kompilace.
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    /**
     * Převede InvoiceDTO na InvoiceEntity.
     *
     * @param source InvoiceDTO zdrojový objekt.
     * @return InvoiceEntity cílový entitní objekt.
     */
    InvoiceEntity toEntity(InvoiceDTO source);


    /**
     * Převede InvoiceEntity na InvoiceDTO.
     *
     * @param source InvoiceEntity zdrojový objekt.
     * @return InvoiceDTO cílový DTO objekt.
     */
    InvoiceDTO toDTO(InvoiceEntity source);
}
