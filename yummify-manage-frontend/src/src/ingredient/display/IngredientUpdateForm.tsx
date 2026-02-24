import {Dtos} from "../../common/dtos.ts";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import IngredientForm from "../form/IngredientForm.tsx";
import {ingredientService} from "../service/ingredientService.ts";
import IngredientManageDto = Dtos.IngredientManageDto;

export interface IngredientUpdateFormProps {
    ingredient: IngredientManageDto;
    onCancel: () => void;
}

function IngredientUpdateForm(props: IngredientUpdateFormProps) {
    const {ingredient, onCancel} = props;

    const queryClient = useQueryClient();

    const handleUpdate = useMutation({
        mutationFn: (data: IngredientManageDto) => {
            return ingredientService.updateIngredient(data);
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
        <IngredientForm
            ingredient={ingredient}
            onCancel={onCancel}
            handleSubmit={handleUpdate.mutate}
        />
    );
}

export default IngredientUpdateForm;