import PageTitle from "../../common/PageTitle.tsx";
import Button from "../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import TableList from "./TableList.tsx";

function TableListPage() {
    return (
        <>
            <PageTitle
                title='Tables'
                description='Monitor and manage restaurant tables'
                button={
                    <Button
                        text='Add New Table'
                        icon={Plus}
                    />
                }
            />
            <TableList/>
        </>
    )
}

export default TableListPage;