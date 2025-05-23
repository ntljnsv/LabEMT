import {useCallback, useEffect, useState} from "react";
import countryRepository from "../repository/countryRepository.js";

const initialState = {
    countries: [],
    loading: true,
}

const useCountries = () => {

    const [state, setState] = useState(initialState);

    const fetchCountries = useCallback(() => {

        countryRepository
            .findAll()
            .then((response) => {
                setState({
                    countries: response.data,
                    loading: false
                });
            })
            .catch((ex) => {
                console.log(ex);
            })

    }, []);

    const onAdd = useCallback((data) => {

        countryRepository
            .add(data)
            .then(() => {
                console.log("Successfully added new country");
                fetchCountries();
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [fetchCountries]);

    const onEdit = useCallback((id, data) => {

        countryRepository
            .edit(id, data)
            .then(() => {
                console.log("Successfully updated country");
                fetchCountries();
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [fetchCountries]);

    const onDelete = useCallback((id) => {

        countryRepository
            .delete(id)
            .then(() => {
                console.log("Successfully deleted country");
                fetchCountries();
            })
            .catch((ex) => {
                console.log(ex)
            })
    }, [fetchCountries]);

    useEffect(() => {

        fetchCountries();
    }, [fetchCountries]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};

}

export default useCountries;