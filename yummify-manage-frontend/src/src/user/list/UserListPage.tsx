import PageTitle from "../../common/PageTitle.tsx";
import Button from "../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import UserList from "./UserList.tsx";
import "./UserList.css";
import {useState} from "react";
import {Dtos} from "../../common/dtos.ts";
import UserManageDto = Dtos.UserManageDto;
import UserDisplayModal from "../display/UserDisplayModal.tsx";

function UserListPage() {
    const [selectedUser, setSelectedUser] = useState<UserManageDto | null>(null);


    return (
        <>
            <PageTitle
                title='Users'
                description='Monitor and manage restaurant users'
                button={
                    <Button
                        text='Add New User'
                        icon={Plus}
                        onClick={() => {}}
                    />
                }
            />
            <UserList onElementClick={setSelectedUser}/>
            {selectedUser && (
                <UserDisplayModal user={selectedUser} onClose={() => setSelectedUser(null)}/>
            )}
        </>
    )
}

export default UserListPage;