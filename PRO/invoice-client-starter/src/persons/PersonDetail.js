import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

import { apiGet } from "../utils/api";
import Country from "./Country";

const PersonDetail = () => {
  const { id } = useParams();
  const [person, setPerson] = useState({});
  const [issuedInvoices, setIssuedInvoices] = useState([]);
  const [receivedInvoices, setReceivedInvoices] = useState([]);
  const [totalIncome, setTotalIncome] = useState(0);
  const [totalExpenses, setTotalExpenses] = useState(0);

  useEffect(() => {
    apiGet(`/api/persons/${id}`)
      .then((data) => {
        setPerson(data);
        // Načtení vystavených faktur
        apiGet(`/api/identification/${data.identificationNumber}/sales`)
          .then((issuedData) => {
            setIssuedInvoices(issuedData);
            // Výpočet celkových příjmů
            const total = issuedData.reduce((acc, item) => acc + item.price, 0);
            setTotalIncome(total);
          })
          .catch((error) =>
            console.error("Chyba při načítání vystavených faktur:", error)
          );
        // Načtení přijatých faktur
        apiGet(`/api/identification/${data.identificationNumber}/purchases`)
          .then((receivedData) => {
            setReceivedInvoices(receivedData);
            // Výpočet celkových výdajů
            const total = receivedData.reduce(
              (acc, item) => acc + item.price,
              0
            );
            setTotalExpenses(total);
          })
          .catch((error) =>
            console.error("Chyba při načítání přijatých faktur:", error)
          );
      })
      .catch((error) =>
        console.error("Chyba při načítání detailu osoby:", error)
      );
  }, [id]);

  const country =
    Country.CZECHIA === person.country ? "Česká republika" : "Slovensko";

  return (
    <div className="container">
      <div className="row">
        <div>
          <h1 className="mt-3 d-inline-block me-2">Detail osoby: </h1>
          <h1 className="d-inline-block text-primary text-uppercase">
            {person.name}
          </h1>
        </div>
        <hr />
        <div className="bg-light p-2 col">
          <p>
            <strong>IČO:</strong>
            <br />
            {person.identificationNumber}
          </p>

          <p>
            <strong>DIČ:</strong>
            <br />
            {person.taxNumber}
          </p>
          <p>
            <strong>Bankovní účet:</strong>
            <br />
            {person.accountNumber} / {person.bankCode}
          </p>

          <p>
            <strong>IBAN:</strong>
            <br />
            {person.iban}
          </p>

          <p>
            <strong>Tel.:</strong>
            <br />
            {person.telephone}
          </p>
          <p>
            <strong>Mail:</strong>
            <br />
            {person.mail}
          </p>
          <p>
            <strong>Sídlo:</strong>
            <br />
            {person.street}, {person.city},{person.zip}, {country}
          </p>

          <p>
            <strong>Země:</strong>
            <br />
            {country}
          </p>

          <p className="mb-0">
            <strong>Poznámka:</strong>
            <br />
            {person.note}
          </p>
        </div>
        <div className="col-sm-8">
          {/* Tabulka vystavených faktur */}
          {/* Příjmy celkem */}
          <h3 className="mt-3">Vystavené faktury</h3>
          <div>
            <table className="table table-bordered table-sm table-striped p-3">
              <thead>
                <tr className="bg-dark text-light">
                  <th>Číslo faktury</th>
                  <th>Odběratel</th>
                  <th>Datum vystavení</th>
                  <th>Datum splatnosti</th>
                  <th>Částka</th>
                </tr>
              </thead>
              <tbody>
                {issuedInvoices.map((item, index) => (
                  <tr key={index}>
                    <td>
                      <Link to={`/invoices/show/${item._id}`}>
                        {item.invoiceNumber}
                      </Link>
                    </td>
                    <td>
                      <Link to={`/persons/show/${item.buyer._id}`}>
                        {item.buyer.name}
                      </Link>
                    </td>
                    <td>{item.issued}</td>
                    <td>{item.dueDate}</td>
                    <td>{item.price}</td>
                  </tr>
                ))}
                <tr>
                  <td colSpan="4">
                    <strong>Příjmy celkem:</strong>
                  </td>
                  <td className="bg-light">
                    <strong>{totalIncome}</strong>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          {/* Tabulka přijatých faktur */}
          {/* Výdaje celkem */}
          <h3 className="mt-3">Přijaté faktury</h3>
          <div>
            <table className="table table-bordered table-sm table-striped p-3">
              <thead>
                <tr className="bg-dark text-light">
                  <th>Číslo faktury</th>
                  <th>Dodavatel</th>
                  <th>Datum vystavení</th>
                  <th>Datum splatnosti</th>
                  <th>Částka</th>
                </tr>
              </thead>
              <tbody>
                {receivedInvoices.map((item, index) => (
                  <tr key={index}>
                    <td>
                      <Link to={`/invoices/show/${item._id}`}>
                        {item.invoiceNumber}
                      </Link>
                    </td>
                    <td>
                      <Link to={`/persons/show/${item.seller._id}`}>
                        {item.seller.name}
                      </Link>
                    </td>
                    <td>{item.issued}</td>
                    <td>{item.dueDate}</td>
                    <td>{item.price}</td>
                  </tr>
                ))}
                <tr>
                  <td colSpan="4">
                    <strong>Výdaje celkem:</strong>
                  </td>
                  <td className="bg-light">
                    <strong>{totalExpenses}</strong>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <div className="container ms-0 ps-0 mt-3">
        <div className="row">
          <div className="mb-4">
            <Link
              to={"/"}
              className="btn btn-light btn-sm border-dark me-3"
            >
              Zpět na seznam osob
            </Link>

            <Link
              to={`/persons/edit/${id}`}
              className="btn btn-primary btn-sm"
            >
              Upravit detail osoby
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PersonDetail;
