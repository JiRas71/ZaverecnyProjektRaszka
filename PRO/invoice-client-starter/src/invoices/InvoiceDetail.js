import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

import { apiGet } from "../utils/api";
import Country from "../persons/Country";

const InvoiceDetail = () => {
  const { id } = useParams();
  const [invoice, setInvoice] = useState({});
  const [person, setPerson] = useState({});

  useEffect(() => {
    apiGet(`/api/invoices/${id}`)
      .then((data) => {
        setInvoice(data);
      })
      .catch((error) =>
        console.error("Chyba při načítání detailu faktury:", error)
      );
  }, [id]);

  return (
    <div>
      <div>
        <div>
          <h1 className="mt-3 d-inline-block me-1">Detail faktury č.</h1>
          <h1 className="d-inline-block">{invoice.invoiceNumber}</h1>
        </div>
      </div>
      <div className="">
        <hr />

        <div className="bg-light p-3">
          <p>
            <strong>Kupující:</strong>
            <br />
            <Link to={`/persons/show/${invoice.buyer && invoice.buyer._id}`}>
              {invoice.buyer && invoice.buyer.name}
            </Link>
          </p>

          <p>
            <strong>Prodávající:</strong>
            <br />
            <Link to={`/persons/show/${invoice.seller && invoice.seller._id}`} className="text-success">
              {invoice.seller && invoice.seller.name}
            </Link>
          </p>

          <p>
            <strong>Produkt-služba:</strong>
            <br />
            {invoice.product}
          </p>

          <p>
            <strong>Cena Kč vč.DPH:</strong>
            <br />
            {invoice.price}
          </p>

          <p>
            <strong>DPH:</strong>
            <br />
            {invoice.vat}
          </p>

          <p>
            <strong>Datum vystavení:</strong>
            <br />
            {invoice.issued}
          </p>

          <p>
            <strong>Datum splatnosti:</strong>
            <br />
            {invoice.dueDate}
          </p>

          <p className="mb-0">
            <strong>Poznámka:</strong>
            <br />
            {invoice.note}
          </p>
        </div>
      </div>

      <Link to={"/invoices"}
        className="btn btn-light btn-sm border-dark mt-3 mb-3 me-3">
        Zpět na seznam faktur
      </Link>

      <Link to={"/"} className="btn btn-light btn-sm border-dark mt-3 mb-3">
        Zpět na seznam osob
      </Link>

      <Link to={`/invoices/edit/${id}`} className="btn btn-primary btn-sm mt-3 mb-3 ms-3">
        Upravit fakturu
      </Link>

    </div>
  );
};

export default InvoiceDetail;
