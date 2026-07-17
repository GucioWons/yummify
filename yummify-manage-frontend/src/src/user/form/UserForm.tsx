import {Dtos} from "../../common/dtos.ts";
import UserManageDto = Dtos.UserManageDto;
import {useQuery} from "@tanstack/react-query";
import AppForm from "../../common/form/AppForm.tsx";
import RoleListDto = Dtos.RoleListDto;
import {roleService} from "../../role/service/roleService.ts";
import AppFormTextField from "../../common/form/fields/AppFormTextField.tsx";
import AppFormSelectField from "../../common/form/fields/AppFormSelectField.tsx";

export interface UserFormProps {
    user?: UserManageDto;
    onCancel: () => void;
    handleSubmit: (data: UserManageDto) => void;
}

function UserForm(props: UserFormProps) {
    const {user, onCancel, handleSubmit} = props;

    const {
        data: roles,
        isLoading: isRolesLoading,
        isError: isRolesError
    } = useQuery<RoleListDto[]>({
        queryKey: ["roles"],
        queryFn: () => roleService.getRoles().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isRolesLoading) return <div>Ładowanie...</div>;

    return (
        <AppForm
            <UserManageDto>
            initialData={user ?? {}}
            onSubmit={(data) => handleSubmit(data)}
            onCancel={onCancel}
        >
            <AppFormTextField
                name="firstName"
                label="First name"
                placeholder="First name"
            />
            <AppFormTextField
                name="lastName"
                label="Last name"
                placeholder="Last name"
            />
            <AppFormTextField
                name="username"
                label="Username"
                placeholder="Username"
            />
            <AppFormTextField
                name="email"
                label="Email"
                placeholder="email@restaurant.com"
            />

            {!isRolesError &&
                <AppFormSelectField
                    name="roleId"
                    label="Role"
                    placeholder={"Select role..."}
                    options={roles ?? []}
                    getOptionLabel={(option) => option.name}
                    getOptionValue={(option) => option.id}
                />
            }
        </AppForm>
    );
}

export default UserForm;