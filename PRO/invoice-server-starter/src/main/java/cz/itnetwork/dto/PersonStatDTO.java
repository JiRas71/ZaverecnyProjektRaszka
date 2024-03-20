package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Třída PersonStatDTO slouží jako Data Transfer Object pro předávání statistických údajů
 * o osobě, zejména o jejím příjmu. Obsahuje identifikaci osoby, její název a celkový příjem.
 * Tato třída je užitečná pro agregaci a prezentaci statistických údajů o osobách,
 * jako je celkový příjem. Struktura a názvy proměnných jsou optimalizovány pro serializaci
 * a deserializaci JSON dat pomocí knihovny Jackson.
 */
public class PersonStatDTO {
    @JsonProperty("_id")
    private Long id;
    @JsonProperty("name")
    private String name;

    private Long revenue;

    public PersonStatDTO(long id, String name, int revenue) {
        this.id = id;
        this.name = name;
        this.revenue = (long) revenue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
}
