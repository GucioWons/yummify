import {Dtos} from "../../common/dtos.ts";
import {formatCurrency} from "../../common/useCurrencyFormatter.ts";
import CurrentOrderItemLabel from "./CurrentOrderItemLabel.tsx";
import {useCallback} from "react";
import CurrentOrderButton from "./CurrentOrderButton.tsx";
import {Check, Play, Truck} from "lucide-react";
import OrderItemClientDto = Dtos.OrderItemClientDto;
import OrderStatus = Dtos.OrderStatus;
import OrderItemStatus = Dtos.OrderItemStatus;

export interface CurrentOrderItemProps {
    item: OrderItemClientDto;
    orderStatus: OrderStatus;
}

function CurrentOrderItem(props: CurrentOrderItemProps) {
    const {item, orderStatus} = props;

    const getButton = useCallback(() => {
        switch (item.status) {
            case OrderItemStatus.NEW: {
                if (orderStatus !== OrderStatus.NEW) {
                    return <CurrentOrderButton text="Start" color='ORANGE' icon={Play} onClick={() => {}}/>;
                }
                return undefined;
            }

            case OrderItemStatus.IN_PREPARATION:
                return <CurrentOrderButton text="Ready" color='GREEN' icon={Check} onClick={() => {}}/>;

            case OrderItemStatus.READY:
                return <CurrentOrderButton text="Serve" color='BLUE' icon={Truck} onClick={() => {}}/>;

            default:
                return undefined;
        }
    }, [item.status, orderStatus]);

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
                    <CurrentOrderItemLabel status={item.status} />
                </div>
                {getButton()}
            </div>
        </div>
    );
}

export default CurrentOrderItem;