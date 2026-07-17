import PageTitle from "../../common/PageTitle.tsx";
import Button from "../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import RoleList from "./RoleList.tsx";
import "./RoleList.css";
import {useState} from "react";
import RoleDisplayModal from "../display/RoleDisplayModal.tsx";

function RoleListPage() {
    const [selectedRoleId, setSelectedRoleId] = useState<string | null>();

    return (
        <>
            <PageTitle
                title='Roles'
                description='Monitor and manage restaurant roles'
                button={
                    <Button
                        text='Add New Role'
                        icon={Plus}
                        onClick={() => {}}
                    />
                }
            />
            <RoleList onElementClick={(role) => setSelectedRoleId(role.id)}/>
            {selectedRoleId && (
                <RoleDisplayModal id={selectedRoleId} onClose={() => setSelectedRoleId(null)}/>
            )}
        </>
    )
}

export default RoleListPage;