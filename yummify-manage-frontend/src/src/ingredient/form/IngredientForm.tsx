import {DTOs} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import IngredientManageDTO = DTOs.IngredientManageDTO;
import Language = DTOs.Language;
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ingredientService} from "../service/ingredientService.ts";

export interface IngredientFormProps {
    ingredient?: IngredientManageDTO;
    onCancel: () => void;
}

function IngredientForm(props: IngredientFormProps) {
    const {ingredient, onCancel} = props;

    const queryClient = useQueryClient();

    const handleSubmit = useMutation({
        mutationFn: (data: IngredientManageDTO) => {
            return ingredientService.createIngredient(data);
        },
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ["ingredients"]})
                .then(() => onCancel());
        },
        onError: (error) => {
            console.error("Unexpected error:", error);
        },
    });

    return (
        <AppForm
            <IngredientManageDTO>
            initialData={ingredient ?? {}}
            onSubmit={(data) => handleSubmit.mutate(data)}
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