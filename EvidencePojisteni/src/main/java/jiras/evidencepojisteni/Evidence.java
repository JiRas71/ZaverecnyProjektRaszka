
package jiras.evidencepojisteni;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author jiras
 */
public class Evidence {
    Scanner sc = new Scanner(System.in, "utf-8");
    
    /**
     * Deklarace kolekce ArrayLis
     */
    public ArrayList<Klient> klienti = new ArrayList<>();
    
    /**
     * Přidá nového pojištěného klienta
     * 
     * @param jmeno
     * @param prijmeni
     * @param vek
     * @param telefon 
     */
    public void pridejKlienta(String jmeno, String prijmeni, String vek, String telefon) {
        System.out.println("Registrace klienta");
        System.out.println("--------------------------------------------");
        
        do {
        System.out.print("Zadej jmeno: ");
            jmeno = sc.nextLine().trim();
        } while (jmeno.equals(""));

        do {
        System.out.print("Zadej příjmení: ");
            prijmeni = sc.nextLine().trim();
        } while (prijmeni.equals(""));
        
        do {
        System.out.print("Zadej věk: ");
            vek = sc.nextLine().trim();
        } while (vek.equals(""));
        
        do {
        System.out.print("Zadej telefon: +420");
            telefon = sc.nextLine().trim();
        } while (telefon.equals(""));
        
        System.out.println("--------------------------------------------");
        System.out.println("Data byla uložena.");

        klienti.add(new Klient(jmeno, prijmeni, vek, telefon));
    }
    
    /**
     * Vrátí hledaného pojištěného klienta
     * 
     * @param jmeno
     * @param prijmeni
     * @return nalezene
     */
    public ArrayList<Klient> najdiKlienty(String jmeno, String prijmeni) {
        ArrayList<Klient> nalezeno = new ArrayList<>();

        System.out.println("Vyhledání pojištěného");
        System.out.println("--------------------------------------------");
        
        do {
        System.out.print("Zadejte jméno pojištěného: ");
            jmeno = sc.nextLine().trim();
        } while (jmeno.equals(""));
        
        do {
        System.out.print("Zadejte příjmení pojištěného: ");
            prijmeni = sc.nextLine().trim();
        } while (prijmeni.equals(""));
        
        System.out.println("\nNalezeno: ");
        
        for (Klient klient : klienti) {
            if ((jmeno.equals(klient.getJmeno())) && (prijmeni.equals(klient.getPrijmeni()))) {
                nalezeno.add(klient);
            }
        }
        return nalezeno;
    }
}