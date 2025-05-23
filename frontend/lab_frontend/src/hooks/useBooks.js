import React, {useCallback, useEffect, useState} from 'react';
import bookRepository from "../repository/bookRepository.js";

const initialState = {
    books: [],
    loading: true,
};

const useBooks = () => {

    const [state, setState] = useState(initialState)

    const fetchBooks = useCallback(() => {

        bookRepository
            .findAll()
            .then((response) => {
                setState({
                    books: response.data,
                    loading: false,
                });
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, []);

    const onAdd = useCallback((data) => {

        bookRepository
            .add(data)
            .then(() => {
                console.log("Successfully added new book");
                fetchBooks();
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [fetchBooks]);

    const onEdit = useCallback((id, data) => {

        bookRepository
            .edit(id, data)
            .then(() => {
                console.log("Successfully upated book");
                fetchBooks();
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [fetchBooks]);

    const onDelete = useCallback((id) => {

        bookRepository
            .delete(id)
            .then(() => {
                console.log("Successfully deleted book");
                fetchBooks();
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [fetchBooks]);

    useEffect(() => {

        fetchBooks();
    }, [fetchBooks]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
}

export default useBooks;