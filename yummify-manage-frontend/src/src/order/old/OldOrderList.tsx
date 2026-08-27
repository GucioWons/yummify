import {orderService} from "../service/orderService.ts";
import {useQuery} from "@tanstack/react-query";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";

function OldOrderList() {
    const {data: orders, isLoading, isError} = useQuery<OrderClientDto[]>({
        queryKey: ["orders", "old"],
        queryFn: () => orderService.getOld().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <LoadingSpinner />;
    if (isError) return <div>Błąd podczas pobierania zamówień.</div>;

    return(
        <div>{orders.length}</div>
    )
}

export default OldOrderList;