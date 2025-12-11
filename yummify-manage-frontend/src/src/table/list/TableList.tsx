import {useQuery} from "@tanstack/react-query";
import {tableService} from "../service/tableService.ts";
import {DTOs} from "../../common/dtos.ts";
import TableDTO = DTOs.TableDTO;
import TableListElement from "./TableListElement.tsx";

function TableList() {
    const {data, isLoading, isError} = useQuery<TableDTO[]>({
        queryKey: ["tables"],
        queryFn: () => tableService.getTables().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania składników.</div>;

    return (
        <div>
            {data?.map(
                table => <TableListElement key={table.id} table={table}/>
            )}
        </div>
    );
}

export default TableList;