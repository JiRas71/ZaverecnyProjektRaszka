import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const InvoiceTable = ({ label, items, deleteInvoice, invoiceStats }) => {
  const handleDeleteClick = (itemId) => {
    const confirmation = window.confirm("Opravdu si přejete záznam odstranit?");
    if (confirmation) {
      deleteInvoice(itemId);
    }
  };

  // Získání aktuálního roku
  const currentYear = new Date().getFullYear();

  return (
    <div>
      <Link to={"/invoices/create"} className="btn btn-success btn-sm mb-3">
        Nová faktura
      </Link>

      <div className="d-flex align-items-center mb-3">
        <p className="mb-0">
          {label} <strong>{items.length}</strong> | Součet všech faktur celkem:{" "}
          <strong>{invoiceStats.allTimeSum}</strong> Kč | Součet faktur letos (
          {currentYear}): <strong>{invoiceStats.currentYearSum}</strong> Kč
        </p>
      </div>

      <table className="table table-bordered table-sm table-striped p-3">
        <thead>
          <tr className="bg-dark text-light">
            <th>#</th>
            <th>Číslo faktury</th>
            <th>Dodavatel</th>
            <th>Odběratel</th>
            <th>Datum vystavení</th>
            <th>Datum splatnosti</th>
            <th>Cena (CZK)</th>
            <th colSpan={3} className="text-center">
              Možnosti
            </th>
          </tr>
        </thead>
        <tbody>
          {items.map((item, index) => (
            <tr key={index + 1}>
              <td>{index + 1}</td>
              <td>{item.invoiceNumber}</td>
              <td>{item.buyer.name}</td>
              <td>{item.seller.name}</td>
              <td>{item.issued}</td>
              <td>{item.dueDate}</td>
              <td>{item.price}</td>
              <td className="text-center">
                <div className="btn-group">
                  <Link
                    to={"/invoices/show/" + item._id}
                    className="btn btn-sm btn-info"
                  >
                    Zobrazit
                  </Link>
                  <Link
                    to={"/invoices/edit/" + item._id}
                    className="btn btn-sm btn-warning"
                  >
                    Upravit
                  </Link>
                  <button
                    onClick={() => handleDeleteClick(item._id)}
                    className="btn btn-sm btn-danger"
                  >
                    Odstranit
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to={"/"} className="btn btn-light btn-sm border-dark mb-3">
        Zpět na seznam osob
      </Link>
    </div>
  );
};

export default InvoiceTable;
