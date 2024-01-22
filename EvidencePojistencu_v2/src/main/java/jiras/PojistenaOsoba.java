package jiras;

public class PojistenaOsoba {

    public PojistenaOsoba() {}

    private String jmeno;
    private String prijmeni;
    private int vek;
    private int telefonniCislo;

    // Konstruktor pro inicializaci objektu
    public PojistenaOsoba(String jmeno, String prijmeni, int vek, int telefonniCislo) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.telefonniCislo = telefonniCislo;
    }

    // Metoda pro výpis informací o pojištěné osobě
    @Override
    public String toString() {
        return getJmeno() + " " + getPrijmeni() + ", Věk: " + vek + ", Telefonní číslo: +420" + telefonniCislo;
    }

    /**
     * @return jméno
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * @return přijmeni
     */
    public String getPrijmeni() {
        return prijmeni;
    }
}
