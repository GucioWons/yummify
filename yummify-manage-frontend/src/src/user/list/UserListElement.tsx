import {Dtos} from "../../common/dtos.ts";
import UserManageDto = Dtos.UserManageDto;
import {User} from "lucide-react";

export interface UserListElementProps {
    user: UserManageDto;
}

function UserListElement(props: UserListElementProps) {
    const {user} = props;

    return (
        <div className="user-list-element">
            <div
                style={{
                    width: 40,
                    height: 40,
                    borderRadius: '50%',
                    backgroundColor: '#a7f3d0',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    color: "#059669"
                }}
            >
                <User width={32} height={32} strokeWidth={2}/>
            </div>
            <div className="user-list-element-field">
                <div className="user-list-element-field-names">
                    {user.firstName} {user.lastName}
                </div>
                <div className="user-list-element-field-email">
                    {user.email}
                </div>
            </div>
        </div>
    );
}

export default UserListElement;