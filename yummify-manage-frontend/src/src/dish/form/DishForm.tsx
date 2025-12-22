import {DTOs} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import DishManageDTO = DTOs.DishManageDTO;
import Language = DTOs.Language;

export interface DishFormProps {
    dish?: DishManageDTO;
    onCancel: () => void;
}

function DishForm(props: DishFormProps) {
    const {dish, onCancel} = props;

    return (
        <AppForm
            <DishManageDTO>
            initialData={dish ?? {}}
            onSubmit={(data) => console.log(data)}
            onCancel={onCancel}
        >
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
        </AppForm>
    )
}

export default DishForm;