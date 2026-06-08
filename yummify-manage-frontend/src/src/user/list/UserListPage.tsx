import PageTitle from "../../common/PageTitle.tsx";
import Button from "../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import UserList from "./UserList.tsx";
import "./UserList.css";

function UserListPage() {
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
            <UserList onElementClick={() => {}}/>
        </>
    )
}

export default UserListPage;