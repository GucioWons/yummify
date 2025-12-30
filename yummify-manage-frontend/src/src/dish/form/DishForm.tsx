import {DTOs} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import DishManageDTO = DTOs.DishManageDTO;
import Language = DTOs.Language;
import ImagePicker from "../../common/image/ImagePicker.tsx";
import {useCallback, useState} from "react";
import IngredientListDTO = DTOs.IngredientListDTO;
import AppFormMultiselectField from "../../common/form/fields/AppFormMultiselectField.tsx";
import {useMutation, useQuery, useQueryClient} from "@tanstack/react-query";
import {ingredientService} from "../../ingredient/service/ingredientService.ts";
import {dishService} from "../service/dishService.ts";
import {AxiosResponse} from "axios";

export interface DishFormProps {
    dish?: DishManageDTO;
    onCancel: () => void;
}

function DishForm(props: DishFormProps) {
    const {dish, onCancel} = props;

    const {
        data: ingredients,
        isLoading: isIngredientsLoading,
        isError: isIngredientsError
    } = useQuery<IngredientListDTO[]>({
        queryKey: ["ingredients"],
        queryFn: () => ingredientService.getIngredients().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    const [imageFile, setImageFile] = useState<File | undefined>();

    const queryClient = useQueryClient();

    const handleSubmit = useMutation({
        mutationFn: (data: DishManageDTO) => dishService.createDish(data),
        onSuccess: (data: AxiosResponse<DishManageDTO>) => handleSubmitSuccess(data.data),
        onError: (error) => console.error("Unexpected error:", error),
    });

    const handleSubmitSuccess = useCallback((data: DishManageDTO) => {
        if (imageFile) {
            dishService.updateImage(data.id, imageFile)
                .catch((error) => console.error("Unexpected error:", error))
        }
        queryClient.invalidateQueries({queryKey: ["dishes"]})
            .then(() => onCancel());
    }, [imageFile, onCancel, queryClient]);

    if (isIngredientsLoading) return <div>Ładowanie...</div>;

    return (
        <AppForm
            <DishManageDTO>
            initialData={dish ?? {}}
            onSubmit={(data) => handleSubmit.mutate(data)}
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

            {!isIngredientsError &&
                <AppFormMultiselectField
                    name={"ingredients"}
                    label={"Ingredients"}
                    placeholder={"Select ingredients..."}
                    options={ingredients ?? []}
                    getOptionLabel={(option) => option.name}
                    getOptionKey={(option) => option.id}
                />
            }
        </AppForm>
    )
}

export default DishForm;