package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    /**
     * Přidává novou fakturu do systému.
     * @param invoiceDTO DTO obsahující informace o faktuře.
     * @return Vytvořená faktura.
     */
    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return  invoiceService.addInvoice(invoiceDTO);
    }


    /**
     * Vrací seznam všech faktur v systému.
     * @return Seznam faktur.
     */
    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices() {
        return invoiceService.getAll();
    }


    /**
     * Maže fakturu zadaného ID z systému.
     * Pokud faktura neexistuje, vrací 404 Not Found.
     * @param id ID faktury k smazání.
     * @return ResponseEntity bez obsahu pokud byla faktura smazána.
     */
    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Získává fakturu podle jejího ID.
     * @param invoiceId ID faktury.
     * @return Faktura s daným ID.
     */
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }


    /**
     * Vrací seznam vystavených faktur podle IČO.
     * @param identificationNumber IČO.
     * @return Seznam vystavených faktur.
     */
    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getSalesInvoicesByIdentificationNumber(@PathVariable String identificationNumber) {
        return invoiceService.getSalesInvoicesByIdentificationNumber(identificationNumber);
    }


    /**
     * Vrací seznam přijatých faktur podle IČO.
     * @param identificationNumber IČO.
     * @return ResponseEntity s seznamem přijatých faktur.
     */
    @GetMapping("/identification/{identificationNumber}/purchases")
    public ResponseEntity<List<InvoiceDTO>> getPurchasesInvoicesByIdentificationNumber(@PathVariable String identificationNumber) {
        List<InvoiceDTO> invoices = invoiceService.getPurchasesInvoicesByIdentificationNumber(identificationNumber);
        if (invoices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }


    /**
     * Aktualizuje fakturu s daným ID.
     * @param invoiceId ID faktury k aktualizaci.
     * @param updatedInvoice DTO s aktualizovanými údaji faktury.
     * @return Aktualizovaná faktura.
     */
    @PutMapping("/invoices/{invoiceId}")
    public InvoiceDTO updateInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceDTO updatedInvoice) {
        return invoiceService.updateInvoice(invoiceId, updatedInvoice);
    }


    /**
     * Vrací statistiky související s fakturami, jako jsou celkové sumy a počty.
     * @return ResponseEntity s mapou statistik.
     */
    @GetMapping("/invoices/statistics")
    public ResponseEntity<Map<String, Long>> getInvoiceStatistics() {
        Long currentYearSum = invoiceRepository.getCurrentYearSum(Year.now().getValue());
        Long allTimeSum = invoiceRepository.getAllTimeSum();
        Long invoicesCount = invoiceRepository.count();

        Map<String, Long> statistics = new HashMap<>();
        statistics.put("currentYearSum", currentYearSum);
        statistics.put("allTimeSum", allTimeSum);
        statistics.put("invoicesCount", invoicesCount);

        return ResponseEntity.ok(statistics);
    }


    /**
     * Metoda getFilteredInvoices slouží k získání seznamu faktur, které odpovídají zadaným kritériím.
     * Tato metoda umožňuje filtrovat faktury podle nákupčího, prodejce, produktu, minimální a maximální ceny,
     * a také limitovat počet výsledků. Všechny parametry jsou volitelné a pokud nejsou zadány,
     * filtr se na danou vlastnost nevztahuje. Metoda vrací seznam faktur odpovídajících zadaným kritériím
     * ve formě ResponseEntity, což umožňuje flexibilní správu HTTP stavových kódů.
     *
     * @param buyer ID kupujícího pro filtraci faktur (volitelné).
     * @param seller ID prodávajícího pro filtraci faktur (volitelné).
     * @param product Název produktu pro filtraci faktur (volitelné).
     * @param minPrice Minimální cena pro filtraci faktur (volitelné).
     * @param maxPrice Maximální cena pro filtraci faktur (volitelné).
     * @param limit Maximální počet výsledků, které mají být vráceny (volitelné).
     * @return ResponseEntity obsahující seznam filtrovaných faktur a HTTP status OK.
     */
    @GetMapping("/invoices/filter")
    public ResponseEntity<List<InvoiceDTO>> getFilteredInvoices(
            @RequestParam(required = false) Long buyer,
            @RequestParam(required = false) Long seller,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer limit
    ) {
        List<InvoiceDTO> invoices = invoiceService.getFilteredInvoices(buyer, seller, product, minPrice, maxPrice, limit);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }


    /**
     * "Našeptávač"
     *
     * @return Seznam všech unikátních produktů jako List<String>.
     */
    @GetMapping("/products")
    public List<String> getAllUniqueProducts() {
        return invoiceService.findAllUniqueProducts();
    }
}
