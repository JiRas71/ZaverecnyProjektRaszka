
package jiras.evidencepojisteni;
import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author jiras
 */
public class Klient {
    
    Scanner sc = new Scanner(System.in, "utf-8");
    
    private String jmeno;
    private String prijmeni;
    private String vek;
    private String telefon;
    
    /**
     * Konstruktor
     * @param jmeno
     * @param prijmeni
     * @param vek
     * @param telefon
     */
    public Klient(String jmeno, String prijmeni, String vek, String telefon) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.telefon = telefon;
    }
    
    /**
     * Vrátí jméno
     * 
     * @return
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Nastaví jméno
     * 
     * @param jmeno
     */
    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    /**
     * Vrátí příjmení
     * 
     * @return
     */
    public String getPrijmeni() {
        return prijmeni;
    }

    /**
     * Nastaví příjmení
     * 
     * @param prijmeni
     */
    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    /**
     * Vrátí věk
     * 
     * @return
     */
    public String getVek() {
        return vek;
    }

    /**
     * Nastaví věk
     * 
     * @param vek
     */
    public void setVek(String vek) {
        this.vek = vek;
    }

    /**
     * Vrátí telefon
     * 
     * @return
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * nastaví telefon
     * 
     * @param telefon
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    /**
     * Vrátí jméno příjmení věk telefon
     * 
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s %s   věk: %s let   tel: +420%s", getJmeno(), getPrijmeni(), getVek(), getTelefon() + "\n");
    }
}
