import {DTOs} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import DishManageDTO = DTOs.DishManageDTO;
import Language = DTOs.Language;
import ImagePicker from "../../common/image/ImagePicker.tsx";
import {useState} from "react";
import IngredientListDTO = DTOs.IngredientListDTO;
import AppFormMultiselectField from "../../common/form/fields/AppFormMultiselectField.tsx";

export interface DishFormProps {
    dish?: DishManageDTO;
    onCancel: () => void;
}

function DishForm(props: DishFormProps) {
    const {dish, onCancel} = props;

    const [imageFile, setImageFile] = useState<File | undefined>();

    const ingredients: IngredientListDTO[] = [
        {id: "1", name: "name1"},
        {id: "2", name: "name2"},
        {id: "4", name: "name4"},
        {id: "5", name: "name4"},
        {id: "6", name: "name4"},
        {id: "7", name: "name4"},
        {id: "8", name: "name4"},
        {id: "9", name: "name4"},
        {id: "0", name: "name4"},
        {id: "01", name: "name4"},
        {id: "02", name: "name4"},
        {id: "03", name: "name4"},
        {id: "3", name: "name3"}
    ]

    return (
        <AppForm
            <DishManageDTO>
            initialData={dish ?? {}}
            onSubmit={(data) => console.log(data)}
            onCancel={onCancel}
        >
            <ImagePicker
                value={imageFile}
                onChange={setImageFile}
            />

            <AppFormTranslatedTextField
                name="name"
                label="Dish name"
                placeholder="Eg. Spaghetti carbonara"
                requiredLanguages={[Language.EN]}
                optionalLanguages={[Language.PL, Language.DE]}
            />

            <AppFormTranslatedTextField
                name="description"
                label="Description"
                placeholder="Pasta with eggs, guanciale, pepper and pecorino"
                requiredLanguages={[Language.EN]}
                optionalLanguages={[Language.PL, Language.DE]}
            />

            <AppFormMultiselectField
                name={"ingredients"}
                label={"Ingredients"}
                placeholder={"Select ingredients..."}
                options={ingredients}
                getOptionLabel={(option) => option.name}
                getOptionKey={(option) => option.id}
            />
        </AppForm>
    )
}

export default DishForm;