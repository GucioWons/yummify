import MenuEntryForm from "./MenuEntryForm.tsx";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {Dtos} from "../../../common/dtos.ts";
import MenuEntryDto = Dtos.MenuEntryDto;
import {menuService} from "../../service/menuService.ts";

export interface MenuEntryUpdateFormProps {
    sectionId: string;
    entry: MenuEntryDto;
    onCancel: () => void;
}

function MenuEntryUpdateForm(props: MenuEntryUpdateFormProps) {
    const {sectionId, entry, onCancel} = props;

    const queryClient = useQueryClient();

    const handleCreate = useMutation({
        mutationFn: (data: MenuEntryDto) => {
            return menuService.updateMenuEntry(entry.id, sectionId, data);
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
        <MenuEntryForm entry={entry} onCancel={onCancel} handleSubmit={handleCreate.mutate} />
    );
}

export default MenuEntryUpdateForm;