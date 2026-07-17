import {Dtos} from "../../common/dtos.ts";
import Permission = Dtos.Permission;
import {PermissionMetadata} from "../../auth/model/permissionMetadata.ts";
import FieldDisplay from "../../common/display/fields/FieldDisplay.tsx";
import PermissionGroup from "./PermissionGroup.tsx";
import {useMemo} from "react";

export interface PermissionsFieldDisplayProps {
    label: string;
    permissions: Permission[]
}

function PermissionFieldDisplay(props: PermissionsFieldDisplayProps) {
    const {label, permissions} = props;

    const expandedPermissions = useMemo(() => {
        const expanded = new Set<Permission>();

        const expand = (permission: Permission): void => {
            expanded.add(permission);

            Object.entries(PermissionMetadata)
                .filter(([, metadata]) => metadata.parent === permission)
                .forEach(([child]) => expand(child as Permission));
        };

        permissions.forEach(permission => expand(permission));

        return Array.from(expanded);
    }, [permissions]);

    const groupedPermissions = useMemo(() => {
        return expandedPermissions
            .filter(permission => !PermissionMetadata[permission].isHidden)
            .reduce((acc, permission) => {
                const parent = PermissionMetadata[permission].parent;

                if (!parent || PermissionMetadata[parent].isHidden) {
                    return acc;
                }

                (acc[parent] ??= []).push(permission);

                return acc;
            }, {} as Record<Permission, Permission[]>);
    }, [expandedPermissions]);

    return (
        <FieldDisplay label={label}>
            {Object.entries(groupedPermissions)
                .filter(([parent]) => !PermissionMetadata[parent as Permission].isHidden)
                .map(([parent, children]) => <PermissionGroup parent={parent as Permission} permissions={children} />)}
        </FieldDisplay>
    );
}

export default PermissionFieldDisplay;