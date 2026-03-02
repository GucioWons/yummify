import {Dtos} from "../../common/dtos.ts";
import Modal from "../../common/modal/Modal.tsx";
import {useCallback, useState} from "react";
import TableDisplay from "./TableDisplay.tsx";
import TableDto = Dtos.TableDto;
import TableUpdateForm from "./TableUpdateForm.tsx";

export interface TableDisplayModalProps {
    table: TableDto;
    onClose: () => void;
}

function TableDisplayModal(props: TableDisplayModalProps) {
    const {table, onClose} = props;

    const [isInEditState, setIsInEditState] = useState(false);

    const getTitle = useCallback(() => {
        return isInEditState ? "Edit Table" : "Table Details";
    }, [isInEditState]);
    
    return (
        <Modal title={getTitle()} onClose={onClose}>
            {isInEditState
                ? <TableUpdateForm table={table} onCancel={() => setIsInEditState(false)}/>
                : <TableDisplay table={table} onEditClick={() => setIsInEditState(true)} onCloseClick={onClose}/>
            }
        </Modal>
    )
}

export default TableDisplayModal;