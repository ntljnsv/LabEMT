import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl, FormHelperText,
    InputLabel, MenuItem, Select,
    TextField, Typography
} from "@mui/material";
import useAuthors from "../../../../hooks/useAuthors.js";
import useBookCategories from "../../../../hooks/useBookCategories.js";

const initialFormData = {
    "name": "",
    "category": "",
    "authorId": "",
    "availableCopies": "",
};

const AddBookDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);
    const [error, setError] = useState({});
    const {authors} = useAuthors();
    const categories = useBookCategories();
    const [hasSubmitted, setHasSubmitted] = useState(false);
    const errorMessage = "This is a required field";


    const handleChange = (event) => {

        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const validateForm = () => {

        const newError = {};

        Object.keys(formData).forEach(k => {
            if(!formData[k].toString()) {
                newError[k] = errorMessage
            }
        })

        setError(newError);
        return  Object.keys(newError).length === 0;
    };

    const handleClose = () => {

        setError({});
        onClose();
    }

    const handleSubmit = () => {

        setHasSubmitted(true)
        if(validateForm()) {
            onAdd(formData);
            setFormData(initialFormData);
            onClose();
            setHasSubmitted(false);
            setError({});
        }
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Book</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                    error={hasSubmitted && !!error.name}
                    helperText={hasSubmitted ? error.name : ""}
                />
                <TextField
                    margin="dense"
                    label="Available Copies"
                    name="availableCopies"
                    type="number"
                    value={formData.availableCopies}
                    onChange={handleChange}
                    fullWidth
                    error={hasSubmitted && !!error.availableCopies}
                    helperText={hasSubmitted ? error.availableCopies : ""}
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                        variant="outlined"
                        error={hasSubmitted && !!error.category}
                    >
                        {categories.map((category) => (
                            <MenuItem key={category} value={category}>{category}</MenuItem>
                        ))}
                    </Select>
                    {hasSubmitted && error.category && <FormHelperText style={{color: "red"}}>{error.category}</FormHelperText>}

                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Author</InputLabel>
                    <Select
                        name="authorId"
                        value={formData.authorId}
                        onChange={handleChange}
                        label="Author"
                        variant="outlined"
                        error={hasSubmitted && !!error.authorId}
                    >
                        {authors.map((author) => (
                            <MenuItem key={author.id} value={author.id}>{author.name} {author.surname}</MenuItem>
                        ))}
                    </Select>
                    {hasSubmitted && error.authorId && <FormHelperText style={{color: "red"}}>{error.authorId}</FormHelperText>}
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddBookDialog;
