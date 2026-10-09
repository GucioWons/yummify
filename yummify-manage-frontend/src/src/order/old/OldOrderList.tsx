import {orderService} from "../service/orderService.ts";
import {useQuery} from "@tanstack/react-query";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";
import {Dtos} from "../../common/dtos.ts";
import OrderDto = Dtos.OrderDto;
import List from "../../common/list/List.tsx";
import OldOrderListElement from "./OldOrderListElement.tsx";

function OldOrderList() {
    const {data: orders = [], isLoading, isError} = useQuery<OrderDto[]>({
        queryKey: ["orders", "old"],
        queryFn: () => orderService.getOld().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <LoadingSpinner />;
    if (isError) return <div>Błąd podczas pobierania zamówień.</div>;

    return (
        <List
            items={orders}
            renderItem={(order) => <OldOrderListElement order={order}/>}
        />
    );
}

export default OldOrderList;