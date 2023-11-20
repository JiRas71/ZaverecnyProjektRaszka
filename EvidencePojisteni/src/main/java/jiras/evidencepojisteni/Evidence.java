
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
        
        do { // Zadání křestního jména nového klienta
        System.out.print("Zadej jmeno: ");
            jmeno = sc.nextLine().trim();
            if ((jmeno.length() < 3) || (jmeno.length() > 10) || (jmeno.contains(" "))) {
                System.out.println("Zadej 3 - 10 znaků");
                jmeno = "";
            }
        } while (jmeno.equals("")); // Validace zadaného jména klienta
        
        do { // Zadání příjmení nového klienta
        System.out.print("Zadej příjmení: ");
            prijmeni = sc.nextLine().trim();
            if ((prijmeni.length() < 3) || (prijmeni.length() > 15) || (prijmeni.contains(" "))) {
                System.out.println("Zadej 3 - 15 znaků");
                prijmeni = "";
            }
        } while (prijmeni.equals("")); // Validace zadaného příjmení klienta
        
        do { // Zadání větu nového klienta
        System.out.print("Zadej věk: ");
            vek = sc.nextLine().trim();
            if ((vek.length() < 1) || (vek.length() > 2) || (vek.contains(" "))) {
                System.out.println("Zadej 1-2 ciferné číslo");
                vek = "";
            }
        } while (vek.equals("")); // Validace zadaného věku klienta
        
        
        do { // Zadání telefonu nového klienta
        System.out.print("Zadej telefon: +420");
            telefon = sc.nextLine().trim();
            if ((telefon.length() < 9) || (telefon.length() > 9) || (telefon.contains(" "))) {
                System.out.println("Zadej 9 čísel bez mezer");
                telefon = "";
            }
        } while (telefon.equals("")); // Validace zadaného telefonu klienta
        
        System.out.println("--------------------------------------------");
        System.out.println("Data byla uložena.");

        // Uloží nového klienta
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
        
        do { // Zadání křestního jména hledaného klienta
        System.out.print("Zadejte jméno pojištěného: ");
            jmeno = sc.nextLine().trim();
            if ((jmeno.length() <= 3) || (jmeno.length() >= 10) || (jmeno.contains(" "))) {
                System.out.println("Zadej 3 - 10 znaků");
                jmeno = "";
            }
        } while (jmeno.equals("")); // Validace hledaného jména
        
        do { // Zadání příjmení hledaného klienta
        System.out.print("Zadejte příjmení pojištěného: ");
            prijmeni = sc.nextLine().trim();
            if ((prijmeni.length() <= 3) || (prijmeni.length() >= 15) || (prijmeni.contains(" "))) {
                System.out.println("Zadej 3 - 15 znaků");
                prijmeni = "";
            }
        } while (prijmeni.equals("")); // Validace hledaného příjmení
        
        System.out.println("\nNalezeno: ");
        
        for (Klient klient : klienti) { // Vyhledání klienta
            if ((jmeno.equals(klient.getJmeno())) && (prijmeni.equals(klient.getPrijmeni()))) {
                nalezeno.add(klient);
            }
        }
        return nalezeno; // Navrátí hledaného klienta
    }
}