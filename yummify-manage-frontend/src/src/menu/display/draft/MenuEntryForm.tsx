import {Dtos} from "../../../common/dtos.ts";
import MenuEntryDto = Dtos.MenuEntryDto;
import AppForm from "../../../common/form/AppForm.tsx";
import {useQuery} from "@tanstack/react-query";
import DishListDto = Dtos.DishListDto;
import {dishService} from "../../../dish/service/dishService.ts";
import AppFormSelectField from "../../../common/form/fields/AppFormSelectField.tsx";
import AppFormNumberField from "../../../common/form/fields/AppFormNumberField.tsx";

export interface MenuEntryFormProps {
    entry?: MenuEntryDto;
    onCancel: () => void;
    handleSubmit: (data: MenuEntryDto) => void;
}

function MenuEntryForm(props: MenuEntryFormProps) {
    const { entry, onCancel, handleSubmit } = props;

    const {
        data: dishes,
        isLoading: isDishesLoading,
        isError: isDishesError
    } = useQuery<DishListDto[]>({
        queryKey: ["dishes"],
        queryFn: () => dishService.getDishes().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isDishesLoading) return <div>Ładowanie...</div>;

    return (
        <AppForm
            <MenuEntryDto>
            initialData={entry ?? {}}
            onSubmit={handleSubmit}
            onCancel={onCancel}
        >
            {!isDishesError &&
                <AppFormSelectField
                    name="dishId"
                    label="Dish"
                    placeholder="Select dish..."
                    options={dishes ?? []}
                    getOptionLabel={(option) => option.name}
                    getOptionKey={(option) => option.id}
                    getOptionValue={(option) => option.id}
                />
            }

            <AppFormNumberField
                name="price"
                label="Price"
                placeholder="0.00"
            />
        </AppForm>
    )
}

export default MenuEntryForm;