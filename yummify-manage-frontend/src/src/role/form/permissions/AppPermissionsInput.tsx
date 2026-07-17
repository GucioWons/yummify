import {Dtos} from "../../../common/dtos.ts";
import Permission = Dtos.Permission;
import {PermissionMetadata} from "../../../auth/model/permissionMetadata.ts";
import AppPermissionsGroupInput from "./AppPermissionsGroupInput.tsx";

export interface AppPermissionsInputProps {
    groupedPermissions: Map<Permission, Permission[]>;
    value: Set<Permission>;
    onChange: (permissions: Permission[]) => void;
}

function AppPermissionsInput(props: AppPermissionsInputProps) {
    const {groupedPermissions, value, onChange} = props;

    return (
        <label className="input label-top">
            <span className="input-label">Permissions</span>
            {Array.from(groupedPermissions.entries())
                .filter(([parent]) => !PermissionMetadata[parent].isHidden)
                .map(([parent, children]) =>
                    <AppPermissionsGroupInput
                        parentPermission={parent}
                        childPermissions={children}
                        value={value}
                        onChange={onChange}
                    />
                )}
        </label>
    );
}

export default AppPermissionsInput;