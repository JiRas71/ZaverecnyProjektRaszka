import React from "react";
import { Link } from "react-router-dom";

const PersonTable = ({ label, items, deletePerson }) => {
    const handleDeleteClick = (itemId) => {
        const confirmDelete = window.confirm("Opravdu si přejete odstranit záznam?");
        if (confirmDelete) {
            deletePerson(itemId);
        }
    };

    return (
        <div>
            <p>
                {label} {items.length}
            </p>

            <Link to={"/persons/create"} className="btn btn-success btn-sm mb-3">
                Nová osoba
            </Link>

            <table className="table table-bordered table-sm table-striped p-3">
                <thead>
                    <tr className="bg-dark text-light">
                        <th>#</th>
                        <th>Jméno</th>
                        <th>IČO</th>
                        <th>Město</th>
                        <th>Telefon</th>
                        <th colSpan={3} className="text-center">Možnosti</th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((item, index) => (
                        <tr key={index + 1}>
                            <td>{index + 1}</td>
                            <td>{item.name}</td>
                            <td>{item.identificationNumber}</td>
                            <td>{item.city}</td>
                            <td>{item.telephone}</td>
                            <td className="text-center">
                                <div className="btn-group">
                                    <Link
                                        to={"/persons/show/" + item._id}
                                        className="btn btn-sm btn-info"
                                    >
                                        Zobrazit
                                    </Link>
                                    <Link
                                        to={"/persons/edit/" + item._id}
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
        </div>
    );
};

export default PersonTable;
