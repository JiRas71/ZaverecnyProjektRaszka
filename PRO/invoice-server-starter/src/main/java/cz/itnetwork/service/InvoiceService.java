package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 * InvoiceService definuje sadu operací pro práci s fakturami.
 * Nabízí metody pro přidání, odstranění, získání a úpravu faktur,
 * jakož i pro získání seznamů prodejních a nákupních faktur podle identifikačního čísla,
 * výpočet součtu cen faktur za aktuální rok a všechny roky,
 * a filtraci faktur podle různých kritérií.
 */
public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    void removeInvoice(Long id);

    List<InvoiceDTO> getAll();

    InvoiceDTO getInvoice(Long id);

    void edit(InvoiceDTO invoice);

    List<InvoiceDTO> getSalesInvoicesByIdentificationNumber(String identificationNumber);

    List<InvoiceDTO> getPurchasesInvoicesByIdentificationNumber(String identificationNumber);

    InvoiceDTO updateInvoice(Long invoiceId, InvoiceDTO updatedInvoice);

    Long getCurrentYearSum(int year);

    Long getAllTimeSum();

    /**
     * Filtruje faktury podle zadaných kritérií, včetně ID kupujícího, ID prodávajícího,
     * názvu produktu, minimální a maximální ceny a limitu počtu výsledků.
     *
     * @param buyerId ID kupujícího pro filtraci faktur.
     * @param sellerId ID prodávajícího pro filtraci faktur.
     * @param product Název produktu pro filtraci faktur.
     * @param minPrice Minimální cena pro filtraci faktur.
     * @param maxPrice Maximální cena pro filtraci faktur.
     * @param limit Maximální počet faktur, které mají být vráceny.
     * @return Seznam filtrovaných InvoiceDTO.
     */
    List<InvoiceDTO> getFilteredInvoices(Long buyerId, Long sellerId, String product, BigDecimal minPrice, BigDecimal maxPrice, Integer limit);


    /**
     * Vrátí seznam všech unikátních produktů z faktur.
     *
     * @return Seznam názvů unikátních produktů.
     */
    List<String> findAllUniqueProducts();
}
