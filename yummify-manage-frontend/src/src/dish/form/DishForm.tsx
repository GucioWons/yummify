import {Dtos} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTranslatedTextField from "../../common/form/fields/AppFormTranslatedTextField.tsx";
import ImagePicker from "../../common/image/ImagePicker.tsx";
import AppFormMultiselectField from "../../common/form/fields/AppFormMultiselectField.tsx";
import {useQuery} from "@tanstack/react-query";
import {ingredientService} from "../../ingredient/service/ingredientService.ts";
import DishManageDto = Dtos.DishManageDto;
import Language = Dtos.Language;
import IngredientClientDto = Dtos.IngredientClientDto;

export interface DishFormProps {
    dish?: DishManageDto;
    imageFile?: File;
    setImageFile: (file?: File) => void;
    onCancel: () => void;
    handleSubmit: (data: DishManageDto) => void;
}

function DishForm(props: DishFormProps) {
    const {dish, imageFile, setImageFile, onCancel, handleSubmit} = props;

    const {
        data: ingredients,
        isLoading: isIngredientsLoading,
        isError: isIngredientsError
    } = useQuery<IngredientClientDto[]>({
        queryKey: ["ingredients"],
        queryFn: () => ingredientService.getIngredients().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isIngredientsLoading) return <div>Ładowanie...</div>;

    return (
        <AppForm
            <DishManageDto>
            initialData={dish ?? {}}
            onSubmit={(data) => handleSubmit(data)}
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
                    name={"ingredientIds"}
                    label={"Ingredients"}
                    placeholder={"Select ingredients..."}
                    options={ingredients ?? []}
                    getOptionLabel={(option) => option.name}
                    getOptionKey={(option) => option.id}
                    getOptionValue={(option) => option.id}
                />
            }
        </AppForm>
    )
}

export default DishForm;