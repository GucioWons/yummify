import {useQuery} from "@tanstack/react-query";
import {tableService} from "../service/tableService.ts";
import {Dtos} from "../../common/dtos.ts";
import TableListElement from "./TableListElement.tsx";
import List from "../../common/list/List.tsx";
import TableDto = Dtos.TableDto;

function TableList() {
    const {data, isLoading, isError} = useQuery<TableDto[]>({
        queryKey: ["tables"],
        queryFn: () => tableService.getTables().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania składników.</div>;

    return (
        <List
            items={data!}
            columns={4}
            renderItem={(table) => <TableListElement table={table}/>}
            onItemClick={() => {}}
        />
    );
}

export default TableList;