import {DTOs} from "../../common/dtos.ts";
import IngredientManageDTO = DTOs.IngredientManageDTO;
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTextField from "../../common/form/fields/AppFormTextField.tsx";

export interface IngredientFormProps {
    ingredient?: IngredientManageDTO;
    onCancel: () => void;
}

function IngredientForm(props: IngredientFormProps) {
    const {ingredient, onCancel} = props;

    return (
        <AppForm
            <IngredientManageDTO>
            initialData={ingredient ?? {}}
            onSubmit={(data) => console.log(data)}
            onCancel={onCancel}
        >
            <AppFormTextField
                name="name"
                label="Name"
                placeholder="Eg. Tomatoes"
            />
        </AppForm>
    );
}

export default IngredientForm;