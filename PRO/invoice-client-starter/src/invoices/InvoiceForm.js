import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import Select from "react-select";
import { apiGet, apiPost, apiPut } from "../utils/api";
import InputField from "../components/InputField";
import FlashMessage from "../components/FlashMessage";

const InvoiceForm = () => {
  const navigate = useNavigate(); // Navigace pro přesměrování uživatele
  const { id } = useParams(); // Parametr z URL pro identifikaci faktury
  const [invoice, setInvoice] = useState({
    // Stav pro uchování dat faktury
    invoiceNumber: "",
    product: "",
    note: "",
    issued: "",
    dueDate: "",
    vat: "",
    price: "",
    buyer: { _id: "", name: "" },
    seller: { _id: "", name: "" },
  });

  const [sentState, setSent] = useState(false); // Stav odeslání formuláře
  const [successState, setSuccess] = useState(false); // Stav úspěchu operace
  const [errorState, setError] = useState(null); // Stav chyby
  const [persons, setPersons] = useState([]); // Seznam osob pro výběr kupujícího/prodávajícího
  const [buyers, setBuyers] = useState([]); // Filtr pro kupující
  const [sellers, setSellers] = useState([]); // Filtr pro prodávající

  const handleSubmit = async (e) => {
    e.preventDefault(); // Zamezení výchozího chování formuláře
    try {
      if (id) {
        await apiPut("/api/invoices/" + id, invoice); // Aktualizace faktury pokud existuje ID
      } else {
        await apiPost("/api/invoices", invoice); // Vytvoření nové faktury pokud ID neexistuje
      }
      setSent(true); // Nastavení odeslaného stavu
      setSuccess(true); // Nastavení úspěšného stavu
      setTimeout(() => {
        navigate("/invoices"); // Přesměrování na seznam faktur po úspěšném uložení
      }, 2000);
    } catch (error) {
      setError("Nastala chyba při ukládání faktury."); // Nastavení chyby při neúspěchu
    }
  };

  useEffect(() => {
    const fetchInvoiceData = async () => {
      try {
        if (id) {
          const data = await apiGet("/api/invoices/" + id);
          setInvoice(data);
        }
      } catch (error) {
        setError("Nastala chyba při načítání detailů faktury.");
      }
    };

    const fetchData = async () => {
      try {
        const data = await apiGet("/api/persons");
        const uniqueBuyers = Array.from(new Set(data.map((person) => person)));
        const uniqueSellers = Array.from(new Set(data.map((person) => person)));

        setPersons(data);
        setBuyers(uniqueBuyers);
        setSellers(uniqueSellers);
      } catch (error) {
        setError("Nastala chyba při načítání dat.");
      }
    };

    fetchInvoiceData();
    fetchData();
  }, [id]); // Závislost na id zajišťuje, že když se id změní, hook se znovu spustí

  const formattedBuyers = buyers.map((buyer) => ({
    // Formátování kupujících pro Select komponentu
    value: buyer._id,
    label: `${buyer.name} (IČO: ${buyer.identificationNumber})`,
  }));

  const formattedSellers = sellers.map((seller) => ({
    // Formátování prodávajících pro Select komponentu
    value: seller._id,
    label: `${seller.name} (IČO: ${seller.identificationNumber})`,
  }));

  return (
    <div>
      <h1 className="mt-3">{id ? "Upravit" : "Vytvořit"} fakturu</h1>
      <hr />
      {errorState ? (
        <div className="alert alert-danger">{errorState}</div>
      ) : null}
      {sentState && (
        <FlashMessage
          theme={successState ? "success" : ""}
          text={successState ? "Uložení faktury proběhlo úspěšně." : ""}
        />
      )}

      <form onSubmit={handleSubmit}>
        <div className="container col bg-light p-2">
          <div className="row">
            <div className="col-6">
              {/* Číslo faktury */}
              <div className="mb-3">
                <InputField
                  required={true}
                  type="number"
                  name="invoiceNumber"
                  label="Číslo faktury"
                  value={invoice.invoiceNumber}
                  handleChange={(e) => {
                    setInvoice({ ...invoice, invoiceNumber: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3" v>
                Vyberte kupujícího
                {/* Select pro prodávajícího */}
                <Select
                  id="buyers"
                  value={{
                    value: invoice.buyer._id || "",
                    label: invoice.buyer.name
                      ? `${invoice.buyer.name}`
                      : "Vyberte",
                  }}
                  options={[{ value: "" }, ...formattedBuyers]}
                  onChange={(selectedOption) =>
                    setInvoice({
                      ...invoice,
                      buyer: {
                        _id: selectedOption.value,
                        name: selectedOption.label,
                      },
                    })
                  }
                />
              </div>

              <div className="mb-3">
                Vyberte prodávajícího
                {/* Select pro prodávajícího */}
                <Select
                  id="seller"
                  value={{
                    value: invoice.seller._id || "",
                    label: invoice.seller.name
                      ? `${invoice.seller.name}`
                      : "Vyberte",
                  }}
                  options={[{ value: "" }, ...formattedSellers]}
                  onChange={(selectedOption) =>
                    setInvoice({
                      ...invoice,
                      seller: {
                        _id: selectedOption.value,
                        name: selectedOption.label,
                      },
                    })
                  }
                />
              </div>

              {/* Produkt */}
              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="product"
                  label="Produkt / Služba"
                  value={invoice.product}
                  handleChange={(e) => {
                    setInvoice({ ...invoice, product: e.target.value });
                  }}
                />
              </div>

              {/* Poznámka */}
              <div>
                <InputField
                  required={true}
                  type="text"
                  name="note"
                  label="Poznámka"
                  value={invoice.note}
                  handleChange={(e) => {
                    setInvoice({ ...invoice, note: e.target.value });
                  }}
                />
              </div>
            </div>

            {/* Datum vystavení faktury */}
            <div className="col-6">
              <div className="mb-3">
                <InputField
                  required={true}
                  type="date"
                  name="issued"
                  label="Datum vystavení faktury"
                  value={invoice.issued}
                  handleChange={(e) => {
                    setInvoice({ ...invoice, issued: e.target.value });
                  }}
                />
              </div>

              {/* Datum splatnosti faktury */}
              <div className="mb-3">
                <InputField
                  required={true}
                  type="date"
                  name="dueDate"
                  label="Datum splatnosti faktury"
                  value={invoice.dueDate}
                  handleChange={(e) => {
                    setInvoice({ ...invoice, dueDate: e.target.value });
                  }}
                />
              </div>

              {/* DPH */}
              <div className="mb-3">
                <InputField
                  required={true}
                  type="number"
                  name="vat"
                  label="DPH"
                  value={invoice.vat}
                  handleChange={(e) => {
                    setInvoice({ ...invoice, vat: e.target.value });
                  }}
                />
              </div>

              {/* Cena */}
              <div className="mb-3">
                <InputField
                  required={true}
                  type="number"
                  name="price"
                  label="Cena Kč vč.DPH"
                  value={invoice.price}
                  handleChange={(e) => {
                    setInvoice({ ...invoice, price: e.target.value });
                  }}
                />
              </div>
            </div>
          </div>
        </div>
        <input
          type="submit"
          className="btn btn-primary btn-sm mt-3 mb-4"
          value="Uložit"
        />
        <Link
          to="/invoices"
          className="btn btn-light btn-sm border border-dark mt-3 ms-3 mb-4"
        >
          Zpět na seznam faktur
        </Link>
      </form>
    </div>
  );
};

export default InvoiceForm;
