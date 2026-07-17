import Display from "../../common/display/Display.tsx";
import {Dtos} from "../../common/dtos.ts";
import RoleManageDto = Dtos.RoleManageDto;
import TranslatedTextFieldDisplay from "../../common/display/fields/TranslatedTextFieldDisplay.tsx";
import PermissionsFieldDisplay from "./PermissionsFieldDisplay.tsx";
import Permission = Dtos.Permission;
import "./RoleDisplay.css";

export interface RoleDisplayProps {
    role: RoleManageDto;
    onCloseClick: () => void;
}

function RoleDisplay(props: RoleDisplayProps) {
    const {role, onCloseClick} = props;

    const permissions = role.permissions
        .map(permissionText => Permission[permissionText as keyof typeof Permission]);

    return (
        <Display onCloseClick={onCloseClick}>
            <TranslatedTextFieldDisplay label="Name" value={role.name}/>
            <PermissionsFieldDisplay label="Permissions" permissions={permissions}/>
        </Display>
    );
}

export default RoleDisplay;