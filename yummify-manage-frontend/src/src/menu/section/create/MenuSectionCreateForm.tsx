import {useMutation, useQueryClient} from "@tanstack/react-query";
import {menuService} from "../../service/menuService.ts";
import MenuSectionForm from "../form/MenuSectionForm.tsx";
import {Dtos} from "../../../common/dtos.ts";
import CreateMenuSectionRequest = Dtos.CreateMenuSectionRequest;

export interface MenuSectionCreateFormProps {
    onCancel: () => void;
}

function MenuSectionCreateForm(props: MenuSectionCreateFormProps) {
    const {onCancel} = props;

    const queryClient = useQueryClient();

    const handleUpdate = useMutation({
        mutationFn: (data: CreateMenuSectionRequest) => {
            return menuService.createMenuSection(data);
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
        <MenuSectionForm
            onCancel={onCancel}
            handleSubmit={handleUpdate.mutate}
        />
    )
}

export default MenuSectionCreateForm;