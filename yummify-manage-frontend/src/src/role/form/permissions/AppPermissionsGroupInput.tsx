import Checkbox from "../../../common/input/Checkbox.tsx";
import {Dtos} from "../../../common/dtos.ts";
import Permission = Dtos.Permission;
import {useCallback} from "react";
import {PermissionMetadata} from "../../../auth/model/permissionMetadata.ts";
import {useTranslation} from "react-i18next";

export interface AppPermissionsGroupInputProps {
    parentPermission: Permission;
    childPermissions: Permission[];
    value: Set<Permission>;
    onChange: (permissions: Permission[]) => void;
}

function AppPermissionsGroupInput(props: AppPermissionsGroupInputProps) {
    const {parentPermission, childPermissions, value, onChange} = props;

    const { t } = useTranslation();

    const isParentPermissionChecked = value.has(parentPermission);
    
    const isChildPermissionChecked = (permission: Permission) => {
        if (value.has(permission)) {
            return true;
        }
        
        return isParentPermissionChecked;
    };
    
    const toggleParentPermission = useCallback(() => {
        const next = new Set(value);
        
        if (value.has(parentPermission)) {
            next.delete(parentPermission);
        } else {
            next.add(parentPermission);
            childPermissions.forEach(child => next.delete(child));
        }

        onChange([...next]);
    }, [childPermissions, onChange, parentPermission, value]);

    const toggleChildPermission = useCallback((permission: Permission) => {
        const next = new Set(value);

        if (next.has(permission) || next.has(parentPermission)) {
            if (next.has(parentPermission)) {
                next.delete(parentPermission);

                childPermissions.forEach(child => next.add(child));
            }

            next.delete(permission);
        } else {
            next.add(permission);
        }

        const allChildrenSelected = childPermissions.every(child => next.has(child));

        if (allChildrenSelected) {
            next.add(parentPermission);
            childPermissions.forEach(child => next.delete(child));
        } else {
            next.delete(parentPermission);
        }

        onChange([...next]);
    }, [childPermissions, onChange, parentPermission, value]);
    
    return (
        <div>
            <Checkbox
                label={t(PermissionMetadata[parentPermission].translation)}
                checked={isParentPermissionChecked}
                onChange={() => toggleParentPermission()}
            />

            <div style={{marginLeft: 24}}>
                {childPermissions.map(child => (
                    <Checkbox
                        key={child}
                        label={t(PermissionMetadata[child].translation)}
                        checked={isChildPermissionChecked(child)}
                        onChange={() => toggleChildPermission(child)}
                    />
                ))}
            </div>
        </div>
    );
}

export default AppPermissionsGroupInput;