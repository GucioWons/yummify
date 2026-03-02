import {Dtos} from "../../common/dtos.ts";
import TableDto = Dtos.TableDto;

export interface TableListElementProps {
    table: TableDto;
}

function TableListElement(props: TableListElementProps) {
    const {table} = props;

    return (
        <div className="table-list-element">
            <div className="table-list-element-field">
                {table.name}
            </div>
            <div className="table-list-element-field">
                Capacity: {table.capacity}
            </div>
        </div>
    );
}

export default TableListElement;