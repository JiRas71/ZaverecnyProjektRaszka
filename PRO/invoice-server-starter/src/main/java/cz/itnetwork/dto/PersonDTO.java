package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.itnetwork.constant.Countries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Třída PersonDTO slouží jako Data Transfer Object pro osoby. Tato třída je navržena tak, aby podporovala snadnou
 * serializaci a deserializaci JSON objektů pomocí Jackson knihovny, díky čemuž je možné
 * tuto DTO třídu efektivně využívat v REST API komunikaci.
 * Třída využívá Lombok knihovnu pro automatickou generaci konstruktorů, getterů, setterů
 * a metody toString, což zjednodušuje kód a zvyšuje jeho čitelnost.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @JsonProperty("_id")
    private Long id;

    @JsonProperty("name")
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

    private Countries country;

    private String note;

    private Long revenue;
}
