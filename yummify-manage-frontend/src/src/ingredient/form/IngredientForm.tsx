import {DTOs} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import IngredientManageDTO = DTOs.IngredientManageDTO;
import Language = DTOs.Language;

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
            <AppFormTranslatedTextField
                name="name"
                label="Name"
                placeholder="Eg. Tomatoes"
                requiredLanguages={[Language.EN]}
                optionalLanguages={[Language.PL, Language.DE]}
            />
        </AppForm>
    );
}

export default IngredientForm;