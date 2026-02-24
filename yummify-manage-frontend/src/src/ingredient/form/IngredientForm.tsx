import {Dtos} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import Language = Dtos.Language;
import IngredientManageDto = Dtos.IngredientManageDto;

export interface IngredientFormProps {
    ingredient?: IngredientManageDto;
    onCancel: () => void;
    handleSubmit: (data: IngredientManageDto) => void;
}

function IngredientForm(props: IngredientFormProps) {
    const {ingredient, onCancel, handleSubmit} = props;

    return (
        <AppForm
            <IngredientManageDto>
            initialData={ingredient ?? {}}
            onSubmit={handleSubmit}
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