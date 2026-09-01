import {Dtos} from "../../common/dtos.ts";
import OrderClientDto = Dtos.OrderClientDto;
import {useCallback} from "react";
import {formatCurrency} from "../../common/useCurrencyFormatter.ts";
import CurrentOrderLabel from "./CurrentOrderLabel.tsx";
import {BanknoteArrowDown, BellRing} from "lucide-react";
import Label from "../../common/label/Label.tsx";

export interface CurrentOrderHeaderProps {
    order: OrderClientDto;
}

function CurrentOrderHeader(props: CurrentOrderHeaderProps) {
    const {order} = props;

    const getTotalPrice = useCallback(() => {
        return order.items.reduce((initialValue, current) => initialValue + current.price * current.quantity, 0);
    }, [order.items]);

    return (
        <div className="current-order-header">
            <div className="current-order-header-side">
                <h2 className="current-order-header-table">
                    T{order.id.charAt(0)}
                </h2>
                <CurrentOrderLabel status={order.status} />
            </div>

            <div className="current-order-header-side">
                {order.assistanceRequested && <Label text="Assistance" icon={BellRing} color='RED' />}
                {order.paymentRequested && <Label text="Payment" icon={BanknoteArrowDown} color='PURPLE' />}
                <div>
                    {formatCurrency(getTotalPrice(), 'EUR')}
                </div>
            </div>
        </div>
    );
}

export default CurrentOrderHeader;