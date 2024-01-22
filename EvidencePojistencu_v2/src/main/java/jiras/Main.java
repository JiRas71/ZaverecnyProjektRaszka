package jiras;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        SpravaPojisteni spravaPojisteni = new SpravaPojisteni();
        EvidenceValidator validator = new EvidenceValidator();

        while (true) {
            System.out.println("1. Vytvořit nového pojištěného");
            System.out.println("2. Zobrazit seznam všech pojištěných");
            System.out.println("3. Vyhledat pojištěného podle jména a příjmení");
            System.out.println("0. Konec");
            System.out.print("Vyberte akci: ");

            String volbaStr = scanner.nextLine().trim();

            System.out.println();

            if (volbaStr.isEmpty()) {
                System.out.println("Neplatná volba. Zadejte prosím číslo nebo vyberte znovu.");
                continue; // Pokračovat v cyklu od začátku
            }

            try {
                int volba = Integer.parseInt(volbaStr);

                switch (volba) {
                    case 1:
                        // Vytvoření nového pojištěného
                        spravaPojisteni.vytvorPojisteneho (
                                validator.zadejPlatneJmeno(),
                                validator.zadejPlatnePrijmeni(),
                                validator.zadejPlatnyVek(),
                                validator.zadejPlatneTelefonniCislo());
                        System.out.println("Pojištěný vytvořen.\n");
                        break;

                    case 2:
                        // Zobrazení seznamu všech pojištěných
                        System.out.println("Seznam všech pojištěných:");
                        spravaPojisteni.zobrazSeznamPojistenych();
                        System.out.println();
                        break;

                    case 3:
                        // Vyhledání pojištěného podle jména a příjmení
                        System.out.print("Zadejte jméno pojištěného: ");
                        String hledaneJmeno = scanner.nextLine().trim();
                        System.out.print("Zadejte příjmení pojištěného: ");
                        String hledanePrijmeni = scanner.nextLine().trim();

                        PojistenaOsoba nalezenyPojisteny = spravaPojisteni.najdiPojisteneho(hledaneJmeno, hledanePrijmeni);

                        if (nalezenyPojisteny != null) {
                            System.out.println("Nalezený pojištěný: " + nalezenyPojisteny);
                        } else {
                            System.out.println("Pojištěný nenalezen.");
                        }
                        System.out.println();
                        break;

                    case 0:
                        System.out.println("Konec programu.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Neplatná volba. Zvolte znovu.\n");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Neplatná volba. Zadejte prosím číslo.\n");
            }
        }
    }
}