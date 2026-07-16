import {Dtos} from "../../common/dtos.ts";
import RoleListDto = Dtos.RoleListDto;
import {Shield} from "lucide-react";

export interface RoleListElementProps {
    role: RoleListDto;
}

function RoleListElement(props: RoleListElementProps) {
    const {role} = props;

    return (
        <div className="role-list-element">
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
                <Shield width={32} height={32} strokeWidth={2}/>
            </div>
            <div className="role-list-element-field">
                {role.name}
            </div>
        </div>
    )
}

export default RoleListElement;