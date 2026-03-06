import {Dtos} from "../../../common/dtos.ts";
import MenuSectionForm from "../MenuSectionForm.tsx";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {menuService} from "../../service/menuService.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import UpdateMenuSectionNameRequest = Dtos.UpdateMenuSectionNameRequest;

export interface MenuSectionUpdateFormProps {
    section: MenuSectionManageDto;
    onCancel: () => void;
}

function MenuSectionUpdateForm(props: MenuSectionUpdateFormProps) {
    const {section, onCancel} = props;

    const queryClient = useQueryClient();

    const handleUpdate = useMutation({
        mutationFn: (data: UpdateMenuSectionNameRequest) => {
            return menuService.updateMenuSectionName(section.id, data);
        },
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ["menu", "versions", "draft"]})
                .then(() => onCancel());
        },
        onError: (error) => {
            console.error("Unexpected error:", error);
        },
    });

    return (
        <MenuSectionForm
            section={section}
            onCancel={onCancel}
            handleSubmit={handleUpdate.mutate}
        />
    )
}

export default MenuSectionUpdateForm;