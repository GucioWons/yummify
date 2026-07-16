import {useCallback} from "react";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {Dtos} from "../../common/dtos.ts";
import UserManageDto = Dtos.UserManageDto;
import {userService} from "../service/userService.ts";
import UserForm from "../form/UserForm.tsx";

export interface UserCreateFormProps {
    onCancel: () => void;
}

function UserCreateForm(props: UserCreateFormProps) {
    const {onCancel} = props;

    const queryClient = useQueryClient();

    const handleCreate = useMutation({
        mutationFn: (data: UserManageDto) => userService.createUser(data),
        onSuccess: () => handleCreateSuccess(),
        onError: (error) => console.error("Unexpected error:", error),
    });

    const handleCreateSuccess = useCallback(() => {
        queryClient.invalidateQueries({queryKey: ["users"]})
            .then(() => onCancel());
    }, [onCancel, queryClient]);

    return (
        <UserForm
            onCancel={onCancel}
            handleSubmit={handleCreate.mutate}
        />
    );
}

export default UserCreateForm;