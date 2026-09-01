import {Dtos} from "../../common/dtos.ts";
import CurrentOrderItem from "./CurrentOrderItem.tsx";
import OrderClientDto = Dtos.OrderClientDto;
import Divider from "../../common/divider/Divider.tsx";
import CurrentOrderButton from "./CurrentOrderButton.tsx";
import {CheckCircle} from "lucide-react";
import OrderStatus = Dtos.OrderStatus;

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
                        <CurrentOrderItem item={item} orderStatus={order.status}/>
                        {index !== order.items.length - 1 && <Divider/>}
                    </>
                ))}
            {order.status === OrderStatus.DELIVERED && order.paymentRequested &&
                <>
                    <Divider/>
                    <div style={{width: "100%", display: "flex", justifyContent: "center"}}>
                        <CurrentOrderButton text="Complete order" color='PURPLE' onClick={() => {}} icon={CheckCircle}/>
                    </div>
                </>
            }
        </div>
    )
}

export default CurrentOrderItemList;