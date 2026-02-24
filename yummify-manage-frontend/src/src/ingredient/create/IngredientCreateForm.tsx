import IngredientForm from "../form/IngredientForm.tsx";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ingredientService} from "../service/ingredientService.ts";
import {Dtos} from "../../common/dtos.ts";
import IngredientManageDto = Dtos.IngredientManageDto;

export interface IngredientCreateFormProps {
    onCancel: () => void;
}

function IngredientCreateForm(props: IngredientCreateFormProps) {
    const {onCancel} = props;

    const queryClient = useQueryClient();

    const handleCreate = useMutation({
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
        <IngredientForm onCancel={onCancel} handleSubmit={handleCreate.mutate}/>
    )
}

export default IngredientCreateForm;