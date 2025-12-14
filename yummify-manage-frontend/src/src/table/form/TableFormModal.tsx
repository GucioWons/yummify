import {DTOs} from "../../common/dtos.ts";
import TableDTO = DTOs.TableDTO;
import TableForm from "./TableForm.tsx";
import Modal from "../../common/modal/Modal.tsx";

export interface TableFormModalProps {
    table?: TableDTO
    onClose: () => void;
}

function TableFormModal(props: TableFormModalProps) {
    const {table, onClose} = props;

    return (
        <Modal title="Add New Table" subtitle="Add a new table to your restaurant" onClose={onClose}>
            <TableForm table={table} onCancel={onClose} />
        </Modal>
    )
}

export default TableFormModal;