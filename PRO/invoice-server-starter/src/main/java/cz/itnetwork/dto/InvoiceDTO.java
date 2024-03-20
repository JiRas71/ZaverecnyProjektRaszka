package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Třída InvoiceDTO reprezentuje Data Transfer Object pro faktury.
 * Třída je anotována pro kompatibilitu s knihovnou Jackson pro snadnou serializaci
 * a deserializaci JSON dat a také využívá Lombok pro automatizovanou generaci getterů,
 * setterů, konstruktorů a toString metody.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {


    @JsonProperty("_id")
    private Long id;

    private PersonDTO seller;

    private PersonDTO buyer;

    private String invoiceNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate issued;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private String product;

    private Long price;

    private Long vat;

    private String note;
}
