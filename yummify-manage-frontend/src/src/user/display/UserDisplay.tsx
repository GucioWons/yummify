import {Dtos} from "../../common/dtos.ts";
import UserManageDto = Dtos.UserManageDto;
import Display from "../../common/display/Display.tsx";
import TextFieldDisplay from "../../common/display/fields/TextFieldDisplay.tsx";

export interface UserDisplayProps {
    user: UserManageDto;
    onCloseClick: () => void;
}

function UserDisplay(props: UserDisplayProps) {
    const {user, onCloseClick} = props;

    return (
        <Display onCloseClick={onCloseClick}>
            <TextFieldDisplay label="First name" text={user.firstName}/>
            <TextFieldDisplay label="Last name" text={user.lastName}/>
            <TextFieldDisplay label="Email" text={user.email}/>
            <TextFieldDisplay label="Role" text={user.roleId}/>
        </Display>
    );
}

export default UserDisplay;