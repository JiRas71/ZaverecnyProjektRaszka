package cz.itnetwork.service;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatDTO;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Služba PersonServiceImpl implementuje logiku pro operace definované v rozhraní PersonService.
 * Poskytuje metody pro přidávání, odstraňování, získávání a úpravu osob, a také pro získávání statistických údajů o osobách.
 * Třída využívá PersonMapper pro převod mezi PersonDTO a PersonEntity, a PersonRepository pro přístup k datům osob v databázi.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.save(entity);

        return personMapper.toDTO(entity);
    }

    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);

            personRepository.save(person);
        } catch (NotFoundException ignored) {
        }
    }

    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPerson(long id) {
        PersonEntity personEntity = fetchPersonById(id);

        return personMapper.toDTO(personEntity);
    }

    @Override
    public PersonDTO editPerson(Long personId, PersonDTO personDTO) {
        PersonEntity person = fetchPersonById(personId);
        person.setHidden(true);

        PersonEntity entity = personMapper.toEntity(personDTO);
        entity.setId(null);
        PersonEntity save = personRepository.save(entity);

        return  personMapper.toDTO(save);
    }

    @Override
    public Long getRevenue(long personId) {
        List<InvoiceEntity> salesInvoices = invoiceRepository.findBySellerId(personId); // Předpokládá se, že existuje metoda findBySellerId v InvoiceRepository

        return salesInvoices.stream()
                .mapToLong(InvoiceEntity::getPrice)
                .sum();
    }

    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }
    // endregion

    @Override
    public List<PersonStatDTO> getPersonStatistics() {
        List<PersonEntity> persons = personRepository.findAll();
        List<PersonStatDTO> response = new ArrayList<>();

        for (PersonEntity person : persons) {
            // Získání seznamu všech faktur, kde osoba je prodávající
            List<InvoiceEntity> salesInvoices = invoiceRepository.findBySeller(person);

            // Výpočet celkového příjmu z těchto faktur
            Long revenue = salesInvoices.stream()
                    .mapToLong(InvoiceEntity::getPrice)
                    .sum();

            // Přidání statistik do odpovědi
            response.add(new PersonStatDTO(person.getId(), person.getName(), Math.toIntExact(revenue)));
        }

        return response;
    }
}
