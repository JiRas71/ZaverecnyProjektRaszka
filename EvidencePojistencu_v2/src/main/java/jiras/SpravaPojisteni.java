package jiras;

import java.util.ArrayList;

public class SpravaPojisteni {

    private final ArrayList<PojistenaOsoba> seznamPojistenych;

    // Konstruktor pro inicializaci kolekce
    public SpravaPojisteni() {
        seznamPojistenych = new ArrayList<>();
    }

    // Metoda pro vytvoření nového pojištěného
    public void vytvorPojisteneho(String jmeno, String prijmeni, int vek, int telefonniCislo) {
        // Validace a vytvoření objektu PojistenyOsoba
        PojistenaOsoba novyPojisteny = new PojistenaOsoba(jmeno, prijmeni, vek, telefonniCislo);
        seznamPojistenych.add(novyPojisteny);
    }

    // Metoda pro zobrazení seznamu všech pojištěných
    public void zobrazSeznamPojistenych() {
        for (PojistenaOsoba poisteny : seznamPojistenych) {
            System.out.println(poisteny.toString());
        }
    }

    // Metoda pro vyhledání pojištěného podle jména a příjmení
    public PojistenaOsoba najdiPojisteneho(String jmeno, String prijmeni) {
        for (PojistenaOsoba poisteny : seznamPojistenych) {
            if (poisteny.getJmeno().equalsIgnoreCase(jmeno) && poisteny.getPrijmeni().equalsIgnoreCase(prijmeni)) {
                return poisteny;
            }
        }
        return null; // Pokud pojištěný není nalezen
    }
}
