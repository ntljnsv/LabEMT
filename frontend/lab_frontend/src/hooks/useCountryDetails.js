import {useEffect, useState} from "react";
import countryRepository from "../repository/countryRepository.js";

const useCountryDetails = (id) => {

    const [country, setCountry] = useState();

    useEffect(() => {

        countryRepository
            .findById(id)
            .then((response) => {
                setCountry(response.data);
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, [id]);

    return country;
}

export default useCountryDetails;