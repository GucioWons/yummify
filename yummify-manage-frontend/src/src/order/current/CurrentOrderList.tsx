import {Dtos} from "../../common/dtos.ts";
import OrderClientDto = Dtos.OrderClientDto;
import {orderService} from "../service/orderService.ts";
import {useQuery} from "@tanstack/react-query";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";
import CurrentOrder from "./CurrentOrder.tsx";

function CurrentOrderList() {
    const {data: orders, isLoading, isError} = useQuery<OrderClientDto[]>({
        queryKey: ["orders", "current"],
        queryFn: () => orderService.getCurrent().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <LoadingSpinner />;
    if (isError) return <div>Błąd podczas pobierania zamówień.</div>;

    return(
        <div className="current-order-list">
            {orders!.map((order) => <CurrentOrder order={order} />)}
        </div>
    )
}

export default CurrentOrderList;