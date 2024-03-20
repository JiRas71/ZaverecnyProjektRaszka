package cz.itnetwork.entity;

import cz.itnetwork.constant.Countries;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Třída PersonEntity reprezentuje entitu osoby v databázi.
 * Vztahy s entitou InvoiceEntity umožňují sledovat faktury, kde osoba vystupuje
 * jako prodávající nebo kupující.
 */
@Entity(name = "person")
@Getter
@Setter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String identificationNumber;

    private String taxNumber;

    private String accountNumber;

    private String bankCode;

    private String iban;

    private String telephone;

    private String mail;

    private String street;

    private String zip;


    private String city;

    @Enumerated(EnumType.STRING)
    private Countries country;

    private String note;

    private boolean hidden = false;

    private Long getRenevue;

    /**
     * Seznam faktur, na kterých osoba vystupuje jako prodávající.
     */
    @OneToMany(mappedBy = "seller")
    private List<InvoiceEntity> sales;

    /**
     * Seznam faktur, na kterých osoba vystupuje jako kupující.
     */
    @OneToMany(mappedBy = "buyer")
    private List<InvoiceEntity> purchases;
}
