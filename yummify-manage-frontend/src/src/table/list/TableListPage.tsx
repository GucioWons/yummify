import PageTitle from "../../common/PageTitle.tsx";
import Button from "../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import TableList from "./TableList.tsx";
import {useState} from "react";
import TableFormModal from "../form/TableFormModal.tsx";

function TableListPage() {
    const [isFormModalOpen, setIsFormModalOpen] = useState(false);

    return (
        <>
            <PageTitle
                title='Tables'
                description='Monitor and manage restaurant tables'
                button={
                    <Button
                        text='Add New Table'
                        icon={Plus}
                        onClick={() => setIsFormModalOpen(prevState => !prevState)}
                    />
                }
            />
            <TableList/>
            {isFormModalOpen && (
                <TableFormModal onClose={() => setIsFormModalOpen(false)}/>
            )}
        </>
    )
}

export default TableListPage;