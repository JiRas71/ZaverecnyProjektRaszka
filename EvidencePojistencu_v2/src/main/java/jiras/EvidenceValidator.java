package jiras;

import java.util.Scanner;

public class EvidenceValidator extends PojistenaOsoba {

    Scanner scanner = new Scanner(System.in);

    private boolean jakyTelefon = false;
    private boolean jakyVek = false;

    // Kontrola zadaného jména
    public String zadejPlatneJmeno() {
        String jmeno = "";
        boolean platneJmeno = false;  // Resetovat platnost jména

        do {
            System.out.print("Zadejte jméno: ");
            jmeno = scanner.nextLine().trim();

            if (jePlatneJmeno(jmeno)) {
                platneJmeno = true;
            } else {
                System.out.println("Neplatné jméno. Zadejte prosím platné jméno obsahující pouze písmena.");
            }
        } while (!platneJmeno);

        return Character.toUpperCase(jmeno.charAt(0)) + jmeno.substring(1);
    }
    private boolean jePlatneJmeno(String jmeno) {
        return !jmeno.isEmpty() && jmeno.matches("[a-zA-ZčČěĚšŠřŘžŽýÝáÁíÍéÉůŮúÚóÓ]+");
    }

    // Kontrola zadaného příjmení
    public String zadejPlatnePrijmeni() {
        String prijmeni = "";
        boolean platnePrijmeni = false;  // Resetovat platnost příjmení

        do {
            System.out.print("Zadejte příjmení: ");
            prijmeni = scanner.nextLine().trim();

            if (jePlatnePrijmeni(prijmeni)) {
                platnePrijmeni = true;
            } else {
                System.out.println("Neplatné příjmení. Zadejte prosím platné příjmení obsahující pouze písmena.");
            }
        } while (!platnePrijmeni);

        return Character.toUpperCase(prijmeni.charAt(0)) + prijmeni.substring(1);
    }


    private boolean jePlatnePrijmeni(String prijmeni) {
        return !prijmeni.isEmpty() && prijmeni.matches("[a-zA-ZčČěĚšŠřŘžŽýÝáÁíÍéÉůŮúÚóÓ]+");
    }

    // Kontrola zadaného věku
    public int zadejPlatnyVek() {
        int vek = 0;
        do {
            System.out.print("Zadejte věk: ");
            try {
                vek = scanner.nextInt();
                jakyVek = true;
            } catch (Exception e) {
                System.out.println("Neplatný vstup. Zadejte prosím pouze číslo.");
                scanner.nextLine();
            }
        } while (!jakyVek);
        scanner.nextLine();
        return vek;
    }

    // Kontrola zadaného telefonního čísla
    public int zadejPlatneTelefonniCislo() {
        int telefonniCislo = 0;
        do {
            System.out.print("Zadejte telefonní číslo (123456789): ");
            try {
                telefonniCislo = scanner.nextInt();
                jakyTelefon = true;
            } catch (Exception e) {
                System.out.println("Neplatný vstup. Zadejte prosím číslo.");
                scanner.nextLine(); // Vyprázdnění bufferu
            }
        } while (!jakyTelefon);
        scanner.nextLine();
        return telefonniCislo;
    }
}
