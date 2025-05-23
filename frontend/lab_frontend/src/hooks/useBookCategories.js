import {useEffect, useState} from "react";
import bookRepository from "../repository/bookRepository.js";

const useBookCategories = () => {

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        bookRepository
            .findCategories()
            .then((response) => {
                setCategories(response.data);
            })
            .catch((ex) => {
                console.log(ex);
            })
    }, []);

    return categories;
}

export default useBookCategories;