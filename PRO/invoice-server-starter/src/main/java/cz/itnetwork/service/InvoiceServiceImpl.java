package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;

/**
 * InvoiceServiceImpl poskytuje konkrétní implementaci metod definovaných v InvoiceService rozhraní.
 * Tato třída umožňuje přidání, odstranění, úpravu a získání faktur, stejně jako práci s nimi
 * na základě různých kritérií, jako jsou identifikační čísla a filtrační parametry.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{

    private static final Logger logger = Logger.getLogger(InvoiceServiceImpl.class.getName());


    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Odstraní fakturu na základě jejího ID.
     */
    @Override
    public void removeInvoice(Long id) {
    }

    /**
     * Upraví existující fakturu na základě poskytnutých dat v InvoiceDTO.
     */
    @Override
    public void edit(InvoiceDTO invoice) {
    }

    /**
     * Vrátí seznam všech faktur v systému.
     */
    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }


    /**
     * Přidá novou fakturu do systému na základě poskytnutých dat v InvoiceDTO.
     * Přiřadí kupujícího a prodávajícího podle ID uvedených v DTO.
     */
    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);

        PersonEntity buyer = personRepository.getReferenceById(invoiceDTO.getBuyer().getId());
        entity.setBuyer(buyer);

        PersonEntity seller = personRepository.getReferenceById(invoiceDTO.getSeller().getId());
        entity.setSeller(seller);

        InvoiceEntity saved = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(saved);
    }


    /**
     * Vrátí seznam prodejních faktur asociovaných s osobou identifikovanou daným identifikačním číslem.
     * Pro každou osobu, která odpovídá zadanému identifikačnímu číslu, metoda prohledává její prodejní faktury,
     * převede je na InvoiceDTO a přidává do výsledkového seznamu. Tato metoda umožňuje získat přehled
     * o všech prodejích, které osoba uskutečnila.
     *
     * @param identificationNumber Identifikační číslo osoby, pro kterou se mají najít prodejní faktury.
     * @return Seznam DTO prodejních faktur asociovaných s danou osobou.
     */
    @Override
    public List<InvoiceDTO> getSalesInvoicesByIdentificationNumber(String identificationNumber) {
        List<PersonEntity> persons = personRepository.findByIdentificationNumber(identificationNumber);

        List<InvoiceDTO> salesInvoices = new ArrayList<>();
        for (PersonEntity person : persons) {
            salesInvoices.addAll(person.getSales().stream()
                    .map(invoiceMapper::toDTO)
                    .toList());
        }
        return salesInvoices;
    }


    /**
     * Vrátí seznam nákupních faktur asociovaných s osobou identifikovanou daným identifikačním číslem.
     * Tato metoda nejprve zjistí, zda existuje osoba s daným identifikačním číslem, a pokud ano,
     * prohledá všechny faktury v databázi a vybere ty, kde daná osoba vystupuje jako kupující.
     * Každá nalezená faktura je převedena na InvoiceDTO a přidána do výsledkového seznamu.
     *
     * @param identificationNumber Identifikační číslo osoby, pro kterou se mají najít nákupní faktury.
     * @return Seznam DTO nákupních faktur asociovaných s danou osobou, nebo prázdný seznam, pokud osoba nebyla nalezena.
     */
    public List<InvoiceDTO> getPurchasesInvoicesByIdentificationNumber(String identificationNumber) {
        List<PersonEntity> persons = personRepository.findByIdentificationNumber(identificationNumber);
        if (!persons.isEmpty()) {
            PersonEntity person = persons.get(0);

            List<InvoiceDTO> purchases = new ArrayList<>();
            for (InvoiceEntity invoice : invoiceRepository.findAll()) { // Načtení všech faktur
                if (invoice.getBuyer().equals(person)) { // Zkontrolovat, zda kupující na faktuře odpovídá načtené osobě
                    purchases.add(invoiceMapper.toDTO(invoice));
                }
            }
            return purchases;
        }
        return Collections.emptyList();
    }


    /**
     * Vrátí fakturu na základě jejího ID.
     */
    @Override
    public InvoiceDTO getInvoice(Long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));

        return invoiceMapper.toDTO(invoiceEntity);
    }


    /**
     * Aktualizuje fakturu na základě jejího ID a poskytnutých aktualizovaných dat v InvoiceDTO.
     */
    @Override
    public InvoiceDTO updateInvoice(Long invoiceId, InvoiceDTO updatedInvoice) {
        InvoiceEntity existingInvoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new NotFoundException("Faktura " + invoiceId + " nebyla nalezena v databázi"));

        // Aktualizace informací o faktuře z updatedInvoice
        existingInvoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
        existingInvoice.setSeller(personRepository.findById(updatedInvoice.getSeller().getId())
                .orElseThrow(() -> new NotFoundException("Prodávající " + updatedInvoice.getSeller().getId() + " nebyl nalezen v databázi.")));
        existingInvoice.setBuyer(personRepository.findById(updatedInvoice.getBuyer().getId())
                .orElseThrow(() -> new NotFoundException("Kupující " + updatedInvoice.getBuyer().getId() + " nebyl nalezen v databázi.")));
        existingInvoice.setIssued(updatedInvoice.getIssued());
        existingInvoice.setDueDate(updatedInvoice.getDueDate());
        existingInvoice.setProduct(updatedInvoice.getProduct());
        existingInvoice.setPrice(updatedInvoice.getPrice());
        existingInvoice.setVat(updatedInvoice.getVat());
        existingInvoice.setNote(updatedInvoice.getNote());

        InvoiceEntity savedInvoice = invoiceRepository.save(existingInvoice);

        return invoiceMapper.toDTO(savedInvoice);
    }

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Vypočítá součet cen všech faktur vystavených v zadaném roce.
     */
    @Override
    public Long getCurrentYearSum(int year) {
        Query query = entityManager.createQuery("SELECT SUM(i.price) FROM invoice i WHERE YEAR(i.issued) = :year");
        query.setParameter("year", year);
        return (Long) query.getSingleResult();
    }


    /**
     * Vypočítá součet cen všech faktur v databázi.
     */
    @Override
    public Long getAllTimeSum() {
        Query query = entityManager.createQuery("SELECT SUM(i.price) FROM invoice i");
        return (Long) query.getSingleResult();
    }


    /**
     * Vrátí seznam faktur odpovídajících zadaným filtračním kritériím.
     */
    @Override
    public List<InvoiceDTO> getFilteredInvoices(Long buyerId, Long sellerId, String product, BigDecimal minPrice, BigDecimal maxPrice, Integer limit) {
        logger.info(String.format("getFilteredInvoices called with buyerID: %s, sellerID: %s, product: %s, minPrice: %s, maxPrice: %s, limit: %s",
                buyerId, sellerId, product, minPrice, maxPrice, limit));

        Specification<InvoiceEntity> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (buyerId != null) {
                logger.info("Applying buyerID filter: " + buyerId);
                predicates.add(cb.equal(root.join("buyer").get("id"), buyerId));
            }

            if (sellerId != null) {
                predicates.add(cb.equal(root.join("seller").get("id"), sellerId));
            }

            if (product != null && !product.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("product")), "%" + product.toLowerCase() + "%"));
            }

            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        List<InvoiceEntity> result;
        if (limit != null) {
            Pageable pageable = PageRequest.of(0, limit);
            Page<InvoiceEntity> page = invoiceRepository.findAll(spec, pageable);
            result = page.getContent();
        } else {
            result = invoiceRepository.findAll(spec);
        }

        return result.stream().map(invoiceMapper::toDTO).collect(Collectors.toList());
    }


    /**
     * Vrátí seznam všech unikátních produktů uvedených na fakturách.
     */
    @Override
    public List<String> findAllUniqueProducts() {
        return invoiceRepository.findAllUniqueProducts();
    }
}

