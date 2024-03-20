package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Třída InvoiceEntity reprezentuje entitu faktury v databázi.
 * Anotace @Entity označuje, že tato třída je JPA entita.
 * Také obsahuje vazby na entitu PersonEntity pro identifikaci kupujícího a prodávajícího
 * pomocí anotací @ManyToOne.
 * Lombok knihovna je použita pro generování getterů a setterů pro všechny atributy entity,
 * což zjednodušuje kód a zvyšuje jeho čitelnost.
 */
@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    /**
     * Jedinečný identifikátor faktury. Generován automaticky.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Číslo faktury.
     */
    private String invoiceNumber;

    /**
     * Datum vydání faktury.
     */
    private LocalDate issued;

    /**
     * Datum splatnosti faktury.
     */
    private LocalDate dueDate;

    /**
     * Název produktu nebo služby na faktuře.
     */
    private String product;

    /**
     * Cena produktu nebo služby.
     */
    private Long price;

    /**
     * Výše DPH na produktu nebo službě.
     */
    private Long vat;

    /**
     * Poznámka k faktuře, například speciální instrukce nebo podmínky.
     */
    private String note;

    /**
     * Entita reprezentující kupujícího. Asociovaná skrze vazbu ManyToOne.
     */
    @ManyToOne
    private PersonEntity buyer;

    /**
     * Entita reprezentující prodávajícího. Asociovaná skrze vazbu ManyToOne.
     */
    @ManyToOne
    private PersonEntity seller;
}
