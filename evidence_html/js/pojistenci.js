// Kolekce pro ukládání pojištěnců
let pojistenci = [];

// Při načítání stránky zkontrolujeme, zda localStorage obsahuje uložené pojištěnce
window.onload = function() {
    if (localStorage.getItem('pojistenci')) {
        pojistenci = JSON.parse(localStorage.getItem('pojistenci'));
        aktualizujTabulku();
    }
};

// Funkce pro přidání pojištěnce do kolekce
function pridejPojistence() {
    // Získání hodnot z formuláře
    let jmeno = document.getElementById('jmeno').value;
    let prijmeni = document.getElementById('prijmeni').value;
    let vek = document.getElementById('vek').value;
    let telefon = document.getElementById('poj-telefon').value;

    // Kontrola vyplnění polí Jméno a Příjmení
    if (!jmeno || !prijmeni) {
        alert("Vyplňte všechna pole formuláře!");
        return;
    }

    // Kontrola, zda pole Věk a Telefon obsahují čísla
    if (isNaN(vek) || isNaN(telefon) || !vek || !telefon) {
        alert("Věk a Telefon musí být čísla!");
        return;
    }

    // Vytvoření objektu pro pojištěnce
    let pojisteni = {
        jmeno: jmeno,
        prijmeni: prijmeni,
        vek: vek,
        telefon: telefon,
        formatovanyTelefon: formatujTelefon(telefon)
    };

    // Přidání pojištěnce do kolekce
    pojistenci.push(pojisteni);

    // Vytvoření a aktualizace tabulky
    aktualizujTabulku();

    // Vyčištění formuláře
    vyprazdniFormular();

    // Uložení kolekce pojištěnců do localStorage
    ulozPojistanceDoLocalStorage();
}

// Funkce pro vyčištění formuláře
function vyprazdniFormular() {
    // Vyčištění hodnot vstupních polí
    document.getElementById('jmeno').value = '';
    document.getElementById('prijmeni').value = '';
    document.getElementById('vek').value = '';
    document.getElementById('poj-telefon').value = '';
}

// Funkce pro odstranění pojištěnce z kolekce
function odstranPojistence(index) {
    pojistenci.splice(index, 1);
    aktualizujTabulku();

    // Aktualizace localStorage po odstranění
    ulozPojistanceDoLocalStorage();
}

// Přidání mezery za každým třetím číslem telefonu
function formatujTelefon(telefon) {
    if (telefon.length <= 9) {
        return telefon.replace(/(\d{3})(\d{3})(\d{3})/, "$1 $2 $3");
    } else if (telefon.length >= 10){
        return telefon.replace(/(\d{3})(\d{3})(\d{3})(\d{3})/, "$1 $2 $3 $4");
    } else {
        return;
    }
}

// Funkce pro vytvoření a aktualizaci tabulky
function aktualizujTabulku() {
    // Získání odkazu na div, do kterého se vloží tabulka
    let tabDiv = document.getElementById('poj-tab-vse');

    // Vytvoření tabulky
    let table = document.createElement('table');
    
    table.className = 'table table-striped'; 

    // Vytvoření záhlaví tabulky
    let thead = document.createElement('thead');
    let radekZahlavi = document.createElement('tr');
    radekZahlavi.innerHTML = '<th>Jméno a Příjmení</th><th>Věk</th><th>Telefon</th><th>Odstraň</th>';
    thead.appendChild(radekZahlavi);
    table.appendChild(thead);

    // Vytvoření těla tabulky
    let tbody = document.createElement('tbody');

    if (pojistenci.length > 0) {
        for (let i = 0; i < pojistenci.length; i++) {
            let poji = pojistenci[i];
            let pojistenec = poji.jmeno + ' ' + poji.prijmeni;
            let radek = document.createElement('tr');
            radek.innerHTML = '<td>' + pojistenec + '</td><td>' + poji.vek + '</td><td>' + poji.formatovanyTelefon + '</td><td><button onclick="odstranPojistence(' + i + ')" class="btn btn-danger btn-odstranit mx-auto">Odstraň</button></td>';

            tbody.appendChild(radek);
        }
    } else {
        // Vytvoření řádku s prázdným textem, pokud kolekce není obsazena
        let prazdnyRadek = document.createElement('tr');
        prazdnyRadek.innerHTML = '<td colspan="4">Žádný záznam nenalezen.</td>';
        tbody.appendChild(prazdnyRadek);
    }

    table.appendChild(tbody);

    // Odstranění stávajícího obsahu v divu a vložení nové tabulky
    tabDiv.innerHTML = '';
    tabDiv.appendChild(table);
}

// Funkce pro uložení kolekce pojištěnců do localStorage
function ulozPojistanceDoLocalStorage() {
    localStorage.setItem('pojistenci', JSON.stringify(pojistenci));
}
