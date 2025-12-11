import {DTOs} from "../../common/dtos.ts";
import TableDTO = DTOs.TableDTO;
import ListElement from "../../common/ListElement.tsx";

export interface TableListElementProps {
    table: TableDTO;
}

function TableListElement(props: TableListElementProps) {
    const {table} = props;

    return (
        <ListElement key={table.id}>
            <div className="table-list-element">
                <div className="table-list-element-field">
                    {table.name}
                </div>
                <div className="table-list-element-field">
                    occupied
                </div>
                <div className="table-list-element-field">
                    capacity
                </div>
                <div className="table-list-element-field">
                    info
                </div>
            </div>
        </ListElement>
    );
}

export default TableListElement;