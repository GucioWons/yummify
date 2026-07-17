import PageTitle from "../../common/PageTitle.tsx";
import Button from "../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import RoleList from "./RoleList.tsx";
import "./RoleList.css";
import {useState} from "react";
import RoleDisplayModal from "../display/RoleDisplayModal.tsx";
import RoleCreateModal from "../create/RoleCreateModal.tsx";

function RoleListPage() {
    const [isFormModalOpen, setIsFormModalOpen] = useState(false);

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
                        onClick={() => setIsFormModalOpen(true)}
                    />
                }
            />
            <RoleList onElementClick={(role) => setSelectedRoleId(role.id)}/>
            {isFormModalOpen && (
                <RoleCreateModal onClose={() => setIsFormModalOpen(false)}/>
            )}

            {selectedRoleId && (
                <RoleDisplayModal id={selectedRoleId} onClose={() => setSelectedRoleId(null)}/>
            )}
        </>
    )
}

export default RoleListPage;