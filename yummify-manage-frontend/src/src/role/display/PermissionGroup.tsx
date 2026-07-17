import {Dtos} from "../../common/dtos.ts";
import Permission = Dtos.Permission;
import {PermissionMetadata} from "../../auth/model/permissionMetadata.ts";
import {useTranslation} from "react-i18next";

export interface PermissionGroupProps {
    parent: Permission;
    permissions: Permission[]
}

function PermissionGroup(props: PermissionGroupProps) {
    const {parent, permissions} = props;

    const { t } = useTranslation();

    return (
        <div className="permission-group">
            <div className="permission-group-title">
                {t(PermissionMetadata[parent].translation)}
            </div>

            <div className="permission-group-items">
                {permissions.map(permission => (
                    <span
                        key={permission}
                        className="permission-badge"
                    >
                    {t(PermissionMetadata[permission].translation)}
                </span>
                ))}
            </div>
        </div>
    );
}

export default PermissionGroup;