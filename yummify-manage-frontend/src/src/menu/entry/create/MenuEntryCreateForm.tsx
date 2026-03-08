import MenuEntryForm from "../form/MenuEntryForm.tsx";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {Dtos} from "../../../common/dtos.ts";
import MenuEntryDto = Dtos.MenuEntryDto;
import {menuService} from "../../service/menuService.ts";

export interface MenuEntryCreateFormProps {
    sectionId: string;
    onCancel: () => void;
}

function MenuEntryCreateForm(props: MenuEntryCreateFormProps) {
    const {sectionId, onCancel} = props;

    const queryClient = useQueryClient();

    const handleCreate = useMutation({
        mutationFn: (data: MenuEntryDto) => {
            return menuService.createMenuEntry(sectionId, data);
        },
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ["menu-versions", "draft"]})
                .then(() => onCancel());
        },
        onError: (error) => {
            console.error("Unexpected error:", error);
        },
    });

    return (
        <MenuEntryForm onCancel={onCancel} handleSubmit={handleCreate.mutate} />
    );
}

export default MenuEntryCreateForm;