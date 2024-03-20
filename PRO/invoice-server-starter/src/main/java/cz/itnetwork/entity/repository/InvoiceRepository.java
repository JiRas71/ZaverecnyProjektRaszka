package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

/**
 * InvoiceRepository je rozhraní, které rozšiřuje JpaRepository a JpaSpecificationExecutor
 * pro entitu InvoiceEntity. Toto rozhraní poskytuje sadu metod pro práci s fakturami
 * v databázi, včetně CRUD operací a dotazů pro specifické účely, jako je získávání
 * součtu cen faktur za aktuální rok, součtu cen za všechny roky a získání seznamu
 * všech unikátních produktů.
 *
 * JpaRepository poskytuje základní CRUD operace a další metody pro práci s entitami,
 * zatímco JpaSpecificationExecutor umožňuje vytváření komplexních dotazů s využitím
 * kritérií API.
 */
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {


    /**
     * Vypočítá součet cen všech faktur vystavených v zadaném roce.
     *
     * @param year Rok, pro který se má vypočítat součet.
     * @return Součet cen faktur za zadaný rok.
     */
    @Query("SELECT SUM(i.price) FROM invoice i WHERE YEAR(i.issued) = :year")
    Long getCurrentYearSum(int year);


    /**
     * Vypočítá součet cen všech faktur v databázi.
     *
     * @return Součet cen všech faktur.
     */
    @Query("SELECT SUM(i.price) FROM invoice i")
    Long getAllTimeSum();


    /**
     * Vrátí seznam všech unikátních produktů uvedených na fakturách.
     *
     * @return Seznam unikátních produktů.
     */
    @Query("SELECT DISTINCT i.product FROM invoice i")
    List<String> findAllUniqueProducts();


    // Metoda pro získání všech faktur, kde daná osoba je prodávající
    List<InvoiceEntity> findBySellerId(Long sellerId);

    // Přidání metody pro vyhledání faktur podle prodávajícího
    List<InvoiceEntity> findBySeller(PersonEntity seller);


    /**
     * Vytvoří specifikaci pro filtraci faktur podle zadaných kritérií,
     * včetně ID nákupčího, ID prodejce, názvu produktu a rozsahu cen.
     *
     * @param buyerId ID nákupčího pro filtraci faktur.
     * @param sellerId ID prodejce pro filtraci faktur.
     * @param product Název produktu pro filtraci faktur.
     * @param minPrice Minimální cena pro filtraci faktur.
     * @param maxPrice Maximální cena pro filtraci faktur.
     * @return Specifikace pro použití v dotazu.
     */
    static Specification<InvoiceEntity> invoiceSpecifications(Long buyerId, Long sellerId, String product, BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (buyerId != null) {
                predicates.add(criteriaBuilder.equal(root.get("buyer").get("id"), buyerId));
            }

            if (sellerId != null) {
                predicates.add(criteriaBuilder.equal(root.get("seller").get("id"), sellerId));
            }

            if (product != null) {
                predicates.add(criteriaBuilder.like(root.get("product"), "%" + product + "%"));
            }

            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
