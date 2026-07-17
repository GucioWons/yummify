import {useMutation, useQueryClient} from "@tanstack/react-query";
import {useCallback} from "react";
import {Dtos} from "../../common/dtos.ts";
import RoleManageDto = Dtos.RoleManageDto;
import {roleService} from "../service/roleService.ts";
import RoleForm from "../form/RoleForm.tsx";

export interface RoleCreateFormProps {
    onCancel: () => void;
}

function UserCreateForm(props: RoleCreateFormProps) {
    const {onCancel} = props;

    const queryClient = useQueryClient();

    const handleCreate = useMutation({
        mutationFn: (data: RoleManageDto) => roleService.createRole(data),
        onSuccess: () => handleCreateSuccess(),
        onError: (error) => console.error("Unexpected error:", error),
    });

    const handleCreateSuccess = useCallback(() => {
        queryClient.invalidateQueries({queryKey: ["roles"]})
            .then(() => onCancel());
    }, [onCancel, queryClient]);

    return (
        <RoleForm
            onCancel={onCancel}
            handleSubmit={handleCreate.mutate}
        />
    );
}

export default UserCreateForm;