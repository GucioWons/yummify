import PageTitle from "../../common/PageTitle.tsx";
import Button from "../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import TableList from "./TableList.tsx";
import {useState} from "react";
import TableCreateModal from "../create/TableCreateModal.tsx";
import {Dtos} from "../../common/dtos.ts";
import TableDto = Dtos.TableDto;
import TableDisplayModal from "../display/TableDisplayModal.tsx";

function TableListPage() {
    const [isFormModalOpen, setIsFormModalOpen] = useState(false);

    const [selectedTable, setSelectedTable] = useState<TableDto | null>();

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
            <TableList onElementClick={(table) => setSelectedTable(table)}/>

            {isFormModalOpen &&
                <TableCreateModal onClose={() => setIsFormModalOpen(false)}/>
            }
            {selectedTable &&
                <TableDisplayModal table={selectedTable} onClose={() => setSelectedTable(null)} />
            }
        </>
    )
}

export default TableListPage;