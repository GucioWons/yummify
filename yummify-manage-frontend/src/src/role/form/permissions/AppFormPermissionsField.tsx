import {Dtos} from "../../../common/dtos.ts";
import Permission = Dtos.Permission;
import {Controller, FieldPath, FieldValues, useFormContext} from "react-hook-form";
import {useMemo} from "react";
import {PermissionMetadata} from "../../../auth/model/permissionMetadata.ts";
import AppPermissionsInput from "./AppPermissionsInput.tsx";

interface AppFormPermissionsFieldProps<T extends FieldValues> {
    name: FieldPath<T>;
}

function AppFormPermissionsField<T extends FieldValues>(props: AppFormPermissionsFieldProps<T>) {
    const {name} = props;

    const permissions = Object.values(Permission)
        .filter(permission => !PermissionMetadata[permission].isHidden);

    const { control } = useFormContext<T>();

    const groupedPermissions = useMemo(() => {
        const result = new Map<Permission, Permission[]>();

        permissions.forEach(permission => {
            const parent = PermissionMetadata[permission].parent;

            if (!parent) {
                return;
            }

            const children = result.get(parent) ?? [];

            children.push(permission);
            result.set(parent, children);
        });

        return result;
    }, [permissions]);

    return (
        <Controller
            name={name}
            control={control}
            render={({ field }) => (
                <AppPermissionsInput
                    groupedPermissions={groupedPermissions}
                    value={new Set(field.value ?? [])}
                    onChange={field.onChange}
                />
            )}
        />
    );
}

export default AppFormPermissionsField;