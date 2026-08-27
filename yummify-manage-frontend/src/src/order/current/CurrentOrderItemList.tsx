import {Dtos} from "../../common/dtos.ts";
import CurrentOrderItem from "./CurrentOrderItem.tsx";
import OrderClientDto = Dtos.OrderClientDto;
import Divider from "../../common/divider/Divider.tsx";

export interface CurrentOrderItemProps {
    order: OrderClientDto;
}

function CurrentOrderItemList(props: CurrentOrderItemProps) {
    const {order} = props;

    return (
        <div className="current-order-items">
            {order.items
                .map((item, index) => (
                    <>
                        <CurrentOrderItem item={item} orderStatus={order.status} />
                        {index !== order.items.length - 1 && <Divider />}
                    </>
                ))}
        </div>
    )
}

export default CurrentOrderItemList;