import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"; // Používá se pro navigaci a získání parametrů URL
import { Link } from "react-router-dom"; // Komponenta pro vytvoření odkazu bez nutnosti znovu načítat stránku
import { apiGet, apiPost, apiPut } from "../utils/api"; // Funkce pro komunikaci s API (GET, POST, PUT požadavky)
import InputField from "../components/InputField"; // Komponenta pro textové pole formuláře
import InputCheck from "../components/InputCheck"; // Komponenta pro zaškrtávací pole formuláře
import FlashMessage from "../components/FlashMessage"; // Komponenta pro zobrazení výstražných nebo informačních zpráv

import Country from "./Country"; // Enum pro definování dostupných zemí

const PersonForm = () => {
  const navigate = useNavigate(); // Hook pro programovou navigaci
  const { id } = useParams(); // Hook pro získání parametrů z URL
  const [person, setPerson] = useState({
    // Stavový hook pro uchování informací o osobě
    personName: "",
    identificationNumber: "",
    taxNumber: "",
    accountNumber: "",
    bankCode: "",
    iban: "",
    telephone: "",
    mail: "",
    street: "",
    zip: "",
    city: "",
    country: Country.CZECHIA,
    note: "",
  });
  const [sentState, setSent] = useState(false); // Stavový hook pro sledování, zda byl formulář odeslán
  const [successState, setSuccess] = useState(false); // Stavový hook pro sledování, zda bylo odeslání úspěšné
  const [errorState, setError] = useState(null); // Stavový hook pro uchování chybových zpráv

  useEffect(() => {
    if (id) {
      apiGet("/api/persons/" + id).then((data) => setPerson(data));
    }
  }, [id]);

  const handleSubmit = (e) => {
    e.preventDefault();
    (id
      ? apiPut("/api/persons/" + id, person)
      : apiPost("/api/persons", person)
    )
      .then((data) => {
        setSent(true); // Aktualizace sentState
        setSuccess(true);
        setTimeout(() => {
          navigate("/persons"); // Přesměrování po zobrazení potvrzení
        }, 2000);
      })
      .catch((error) => {
        console.log(error.message);
        setError(error.message);
        setSent(true); // Aktualizace sentState
        setSuccess(false);
      });
  };

  return (
    <div>
      {/* Obsah formuláře */}
      {sentState &&
        successState && (
          <FlashMessage
            theme="success"
            text="Uložení osobnosti proběhlo úspěšně."
          />
        )}

      <form onSubmit={handleSubmit}>
        <div className="container col bg-light p-2">
          <div className="row">
            <div className="col-6">
              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="personName"
                  min="3"
                  label="Jméno"
                  prompt="zadejte celé jméno"
                  value={person.name}
                  handleChange={(e) => {
                    setPerson({ ...person, name: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="identificationNumber"
                  min="3"
                  label="IČO"
                  prompt="zadejte IČO"
                  value={person.identificationNumber}
                  handleChange={(e) => {
                    setPerson({
                      ...person,
                      identificationNumber: e.target.value,
                    });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="taxNumber"
                  min="3"
                  label="DIČ"
                  prompt="zadejte DIČ"
                  value={person.taxNumber}
                  handleChange={(e) => {
                    setPerson({ ...person, taxNumber: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="accountNumber"
                  min="3"
                  label="Číslo bankovního účtu"
                  prompt="zadejte číslo bank. účtu"
                  value={person.accountNumber}
                  handleChange={(e) => {
                    setPerson({ ...person, accountNumber: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="bankCode"
                  min="3"
                  label="Kód banky"
                  prompt="zadejte kód banky"
                  value={person.bankCode}
                  handleChange={(e) => {
                    setPerson({ ...person, bankCode: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="IBAN"
                  min="3"
                  label="IBAN"
                  prompt="zadejte IBAN"
                  value={person.iban}
                  handleChange={(e) => {
                    setPerson({ ...person, iban: e.target.value });
                  }}
                />
              </div>

              <div className="">
                <h6>Země:</h6>

                <InputCheck
                  type="radio"
                  name="country"
                  label="Česká republika"
                  value={Country.CZECHIA}
                  handleChange={(e) => {
                    setPerson({ ...person, country: e.target.value });
                  }}
                  checked={Country.CZECHIA === person.country}
                />

                <InputCheck
                  type="radio"
                  name="country"
                  label="Slovensko"
                  value={Country.SLOVAKIA}
                  handleChange={(e) => {
                    setPerson({ ...person, country: e.target.value });
                  }}
                  checked={Country.SLOVAKIA === person.country}
                />
              </div>
            </div>

            <div className="col-6">
              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="telephone"
                  min="3"
                  label="Telefon"
                  prompt="zadejte telefon"
                  value={person.telephone}
                  handleChange={(e) => {
                    setPerson({ ...person, telephone: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="mail"
                  min="3"
                  label="Mail"
                  prompt="zadejte mail"
                  value={person.mail}
                  handleChange={(e) => {
                    setPerson({ ...person, mail: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="street"
                  min="3"
                  label="Ulice"
                  prompt="zadejte ulici"
                  value={person.street}
                  handleChange={(e) => {
                    setPerson({ ...person, street: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="ZIP"
                  min="3"
                  label="PSČ"
                  prompt="zadejte PSČ"
                  value={person.zip}
                  handleChange={(e) => {
                    setPerson({ ...person, zip: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="city"
                  min="3"
                  label="Město"
                  prompt="zadejte město"
                  value={person.city}
                  handleChange={(e) => {
                    setPerson({ ...person, city: e.target.value });
                  }}
                />
              </div>

              <div className="mb-3">
                <InputField
                  required={true}
                  type="text"
                  name="note"
                  label="Poznámka"
                  prompt="poznámka"
                  value={person.note}
                  handleChange={(e) => {
                    setPerson({ ...person, note: e.target.value });
                  }}
                />
              </div>
            </div>
          </div>
        </div>
        <input
          type="submit"
          className="btn btn-primary btn-sm mt-3 mb-3"
          value="Uložit"
        />
        <Link
          to="/persons"
          className="btn btn-light btn-sm border border-dark mt-3 ms-3 mb-3"
        >
          Zpět
        </Link>
      </form>
    </div>
  );
};

export default PersonForm;
