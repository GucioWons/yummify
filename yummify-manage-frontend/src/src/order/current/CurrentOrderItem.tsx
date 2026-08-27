import {Dtos} from "../../common/dtos.ts";
import OrderItemClientDto = Dtos.OrderItemClientDto;
import OrderStatus = Dtos.OrderStatus;
import {formatCurrency} from "../../common/useCurrencyFormatter.ts";
import CurrentOrderLabel from "./CurrentOrderLabel.tsx";
import {WebcamIcon} from "lucide-react";

export interface CurrentOrderItemProps {
    item: OrderItemClientDto;
    orderStatus: OrderStatus;
}

function CurrentOrderItem(props: CurrentOrderItemProps) {
    const {item, orderStatus} = props;

    return (
        <div className="current-order-item">
            <div className="current-order-item-left">
                <div key={item.id}>
                    {item.name} x {item.quantity}
                </div>
                <div>
                    {formatCurrency(item.price, 'EUR')}
                </div>
            </div>
            <div className="current-order-item-right">
                <div>
                    <CurrentOrderLabel text={item.status} icon={WebcamIcon} color="RED" />
                </div>
                <div>
                    Przycisk
                </div>
            </div>
        </div>
    );
}

export default CurrentOrderItem;