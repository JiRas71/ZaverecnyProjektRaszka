import React, { useEffect, useState } from "react";
import { apiDelete, apiGet } from "../utils/api";
import InvoiceTable from "./InvoiceTable";
import InputSelect from "../components/InputSelect";
import InputField from "../components/InputField";
import { Button } from "bootstrap";

const InvoiceIndex = () => {
  const [persons, setPersons] = useState([]);
  const [invoices, setInvoices] = useState([]);
  const [invoiceStats, setInvoiceStats] = useState({
    allTimeSum: 0,
    currentYearSum: 0,
  });

  const [filters, setFilters] = useState({
    sellerID: undefined,
    buyerID: undefined,
    product: undefined,
    minPrice: undefined,
    maxPrice: undefined,
    limit: undefined,
  });

  const resetFilters = () => {
    // Znovunačtení stránky místo resetování stavů filtrů
    window.location.reload();
  };

  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchInvoices();
    fetchPersons();
    fetchInvoiceStats();
    fetchProducts();
  }, []);

  const fetchInvoiceStats = async () => {
    try {
      const response = await apiGet("/api/invoices/statistics");
      setInvoiceStats(response);
    } catch (error) {
      console.error("Error fetching invoice statistics:", error);
    }
  };

  const fetchInvoices = async () => {
    try {
      const response = await apiGet("/api/invoices");
      setInvoices(response);
    } catch (error) {
      console.error("Error fetching invoices:", error);
    }
  };

  const fetchPersons = async () => {
    try {
      const response = await apiGet("/api/persons");
      setPersons(response); // Uložení osob do stavu
      console.log(response)
  
    } catch (error) {
      console.error("Error fetching persons:", error);
    }
  };

  const confirmButtonText = "Filtrovat";

  const handleChange = (e) => {
    setFilters({ ...filters, [e.target.name]: e.target.value });
  
    if (e.target.name === "seller") {
      const sellerID = e.target.value;

    } else if (e.target.name === "buyer") {
      const buyerID = e.target.value;
  }};

  const fetchProducts = async () => {
    try {
      const response = await apiGet("/api/products");
      setProducts(response);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    const query = Object.entries(filters)
      .filter(([_, value]) => value)
      .map(([key, value]) => `${key}=${encodeURIComponent(value)}`)
      .join("&");
  
    if (query.length > 0) {
      try {
        const filteredInvoices = await apiGet(`/api/invoices/filter?${query}`);
        setInvoices(filteredInvoices);
      } catch (error) {
        console.error("Error filtering invoices:", error);
      }
    } else {
      fetchInvoices();
    }
  };

  const deleteInvoice = async (id) => {
    try {
      await apiDelete(`/api/invoices/${id}`);
      fetchInvoices();
    } catch (error) {
      console.error("Error deleting invoice:", error);
    }
  };

  return (
    <div>
      <h1 className="mt-3">Seznam faktur</h1>

      {/* Formulář pro filtraci  */}
      <div className="bg-light p-3 mb-3 border border-dark rounded">
        <form onSubmit={handleSubmit}>
          <div className="row">
            <div className="col mb-3">
              <InputSelect
                name="buyer"
                items={persons}
                handleChange={handleChange}
                label="Odběratel"
                prompt="nevybrán"
                value={filters.buyerID}
              />
            </div>

            <div className="col mb-3">
              <InputSelect
                name="seller"
                items={persons}
                handleChange={handleChange}
                label="Dodavatel"
                prompt="nevybrán"
                value={filters.sellerID}
              />
            </div>

            <div className="col mb-3">
              <label htmlFor="product">Produkt/Služba</label>
              <input
                type="text"
                className="form-control"
                id="product"
                name="product"
                list="productList"
                onChange={handleChange}
                placeholder="neuveden"
                value={filters.product}
              />
              <datalist id="productList">
                {products.map((product, index) => (
                  <option key={index} value={product} />
                ))}
              </datalist>
            </div>
          </div>

          <div className="row">
            <div className="col">
              <InputField
                type="number"
                min="0"
                name="minPrice"
                handleChange={handleChange}
                label="Minimální cena"
                prompt="neuveden"
                value={filters.minPrice}
              />
            </div>

            <div className="col">
              <InputField
                type="number"
                min="0"
                name="maxPrice"
                handleChange={handleChange}
                label="Maximální cena"
                prompt="neuveden"
                value={filters.maxPrice}
              />
            </div>

            <div className="col">
              <InputField
                type="number"
                min="1"
                name="limit"
                handleChange={handleChange}
                label="Limit počtu faktur"
                prompt="neuveden"
                value={filters.limit}
              />
            </div>
          </div>

          <div className="row">
            <div className="col">
              <input
                type="submit"
                className="btn btn-sm btn-light border border-dark float-right mt-2"
                value={confirmButtonText}
              />
              
              <button
                type="button"
                className="btn btn-sm btn-light border border-dark float-right mt-2 ms-3"
                onClick={resetFilters}
              >
                Reset filtru
              </button>
               
            </div>
          </div>
        </form>
      </div>

      <InvoiceTable
        deleteInvoice={deleteInvoice}
        items={invoices}
        label="Počet faktur:"
        invoiceStats={invoiceStats}
      />
    </div>
  );
};

export default InvoiceIndex;
