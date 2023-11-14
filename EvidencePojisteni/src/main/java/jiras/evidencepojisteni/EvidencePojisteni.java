
package jiras.evidencepojisteni;
import java.util.Scanner;

/**
 *
 * @author jiras
 */
public class EvidencePojisteni {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "UTF-8");
        
        Evidence evidence = new Evidence();
        
        String volba = "";
        // hlavní cyklus
        while (!volba.equals("4")) {
            System.out.println("\n--------------------------------------------");
            System.out.println("Evidence pojištěných");
            System.out.println("--------------------------------------------");
            System.out.println("Vyberte si akci:");
            System.out.println("1 - Přidat nového pojištěného");
            System.out.println("2 - Vypsat všechny pojištěné");
            System.out.println("3 - vyhledat pojištěného");
            System.out.println("4 - Konec");
            volba = sc.nextLine().trim();
            System.out.println();
            // reakce na volbu
            switch (volba) {
                case "1":
                    evidence.pridejKlienta(volba, volba, volba, volba);
                    break;
                case "2":
                    System.out.println("Výpis všech pojištěných:");
                    System.out.println(evidence.klienti);
                    break;
                case "3":
                    System.out.println(evidence.najdiKlienty(volba, volba));
                    break;
                case "4":
                    System.out.println("Děkuji za použití programu.");
                    // vyskočí z cyklu while a tím ukončí program
                    break;
                default:
                    System.out.println("Neplatná volba, prosím opakujte výběr");
                    break;
            }
        }
    }
}
        
        
        
        
        
       
        
      