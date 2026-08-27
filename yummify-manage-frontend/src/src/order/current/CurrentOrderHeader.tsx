import {Dtos} from "../../common/dtos.ts";
import OrderClientDto = Dtos.OrderClientDto;
import {useCallback} from "react";
import {formatCurrency} from "../../common/useCurrencyFormatter.ts";
import CurrentOrderLabel from "./CurrentOrderLabel.tsx";
import {ChefHat} from "lucide-react";

export interface CurrentOrderHeaderProps {
    order: OrderClientDto;
}

function CurrentOrderHeader(props: CurrentOrderHeaderProps) {
    const {order} = props;

    const getTotalPrice = useCallback(() => {
        return order.items.reduce((initialValue, current) => initialValue + current.price, 0);
    }, [order.items]);

    return (
        <div className="current-order-header">
            <div className="current-order-header-side">
                <h2 className="current-order-header-table">
                    T1
                </h2>
                <CurrentOrderLabel text={order.status} icon={ChefHat} color={'RED'} />
            </div>

            <div className="current-order-header-side">
                {order.assistanceRequested && <CurrentOrderLabel text="Assistance" icon={ChefHat} color={'RED'} />}
                {order.paymentRequested && <CurrentOrderLabel text="Payment" icon={ChefHat} color={'RED'} />}
                <div>
                    {formatCurrency(getTotalPrice(), 'EUR')}
                </div>
            </div>
        </div>
    );
}

export default CurrentOrderHeader;