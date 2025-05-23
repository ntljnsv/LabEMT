import {useEffect, useState} from "react";
import authorRepository from "../repository/authorRepository.js";
import countryRepository from "../repository/countryRepository.js";

const useAuthorDetails = (id) => {

    const [state, setState] = useState({
        author: null,
        country: null,
    });

    useEffect(() => {

        authorRepository
            .findById(id)
            .then((response) => {

                setState(prevState => ({...prevState, author: response.data}));
                countryRepository

                    .findById(response.data.countryId)
                    .then((response) => {
                        setState(prevState => ({...prevState, country: response.data}));
                    })
                    .catch((ex) => {
                        console.log(ex);
                    })
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [id]);

    return state;
}

export default useAuthorDetails;