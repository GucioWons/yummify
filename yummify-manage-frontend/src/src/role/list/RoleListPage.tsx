import PageTitle from "../../common/PageTitle.tsx";
import Button from "../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import RoleList from "./RoleList.tsx";
import "./RoleList.css";

function RoleListPage() {
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
            <RoleList onElementClick={() => {}}/>
        </>
    )
}

export default RoleListPage;