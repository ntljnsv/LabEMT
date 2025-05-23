import {useCallback, useEffect, useState} from "react";
import authorRepository from "../repository/authorRepository.js";

const initialState = {
    authors: [],
    loading: true,
};

const useAuthors = () => {

    const [state, setState] = useState(initialState);

    const fetchAuthors = useCallback(() => {

        authorRepository
            .findAll()
            .then((response) => {
                setState({
                    authors: response.data,
                    loading: false,
                });
            })
            .catch((ex) => {
                console.log(ex);
            })
    },[]);

    const onAdd = useCallback((data) => {

        authorRepository
            .add(data)
            .then(() => {
                console.log("Successfully added new author");
                fetchAuthors();
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [fetchAuthors]);

    const onEdit = useCallback((id, data) => {

        authorRepository
            .edit(id, data)
            .then(() => {
                console.log("Successfully updated author");
                fetchAuthors();
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [fetchAuthors]);

    const onDelete = useCallback((id) => {

        authorRepository
            .delete(id)
            .then(() => {
                console.log("Successfully deleted author");
                fetchAuthors();
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [fetchAuthors]);

    useEffect(() => {

        fetchAuthors();
    }, [fetchAuthors]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
}

export default useAuthors;