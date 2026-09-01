import {Dtos} from "../../common/dtos.ts";
import CurrentOrderHeader from "./CurrentOrderHeader.tsx";
import CurrentOrderItemList from "./CurrentOrderItemList.tsx";
import OrderClientDto = Dtos.OrderClientDto;

export interface CurrentOrderProps {
    order: OrderClientDto;
}

function CurrentOrder(props: CurrentOrderProps) {
    const {order} = props;

    return (
        <div className="current-order">
            <CurrentOrderHeader order={order} />
            <CurrentOrderItemList order={order} />

        </div>
    )
}

export default CurrentOrder;