import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import { BrowserRouter as Router, Link, Route, Routes } from "react-router-dom";

import PersonIndex from "./persons/PersonIndex";
import PersonDetail from "./persons/PersonDetail";
import PersonForm from "./persons/PersonForm";
import InvoiceIndex from "./invoices/InvoiceIndex";
import InvoiceDetail from "./invoices/InvoiceDetail";
import InvoiceForm from "./invoices/InvoiceForm";

export function App() {
  return (
    <Router>
      <nav className="navbar navbar-expand-sm bg-dark text-light">
        <ul className="mt-2" style={{ listStyleType: "none" }}>
          <li style={{ display: "inline-block" }}>
            <Link to="/" className="navbar-brand fs-2 text-info me-4">
              Full stack projekt
            </Link>
          </li>
          <li style={{ display: "inline-block" }}>
            <Link to={"/persons"} className="nav-link fs-4 me-4">
              🙍🏼‍♂️Osoby
            </Link>
          </li>
          <li style={{ display: "inline-block" }}>
            <Link to={"/invoices"} className="nav-link active fs-4">
              ＄Faktury
            </Link>
          </li>
        </ul>
      </nav>

      <div className="container">
        <Routes>
          <Route index element={<PersonIndex />} />
          <Route path="/persons">
            <Route index element={<PersonIndex />} />
            <Route path="show/:id" element={<PersonDetail />} />
            <Route path="create" element={<PersonForm />} />
            <Route path="edit/:id" element={<PersonForm />} />
          </Route>

          <Route path="/invoices">
            <Route index element={<InvoiceIndex />} />
            <Route path="show/:id" element={<InvoiceDetail />} />
            <Route path="create" element={<InvoiceForm />} />
            <Route path="edit/:id" element={<InvoiceForm />} />
          </Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
