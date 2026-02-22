import {Dtos} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import Language = Dtos.Language;
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ingredientService} from "../service/ingredientService.ts";
import IngredientManageDto = Dtos.IngredientManageDto;

export interface IngredientFormProps {
    ingredient?: IngredientManageDto;
    onCancel: () => void;
}

function IngredientForm(props: IngredientFormProps) {
    const {ingredient, onCancel} = props;

    const queryClient = useQueryClient();

    const handleSubmit = useMutation({
        mutationFn: (data: IngredientManageDto) => {
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
            <IngredientManageDto>
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