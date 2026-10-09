import {Dtos} from "../../common/dtos.ts";
import OrderDto = Dtos.OrderDto;
import OrderStatusLabel from "../OrderStatusLabel.tsx";
import {formatCurrency} from "../../common/useCurrencyFormatter.ts";

export interface OrderListElement {
    order: OrderDto;
}

function OldOrderListElement(props: OrderListElement) {
    const {order} = props;

    const details = order.items.map(item => `${item.name} x${item.quantity}`).join(', ');

    const totalPrice = order.items.reduce((sum, item) => sum + item.price * item.quantity, 0);

    return (
        <div className="old-order-list-element">
            <div className="old-order-list-element-left">
                <div className="old-order-list-element-left-table">
                    T{order.id.charAt(0)}
                </div>
                <OrderStatusLabel status={order.status}/>

                <div>
                    {details}
                </div>
            </div>

            <div className="old-order-list-element-right">
                <div>
                    {formatCurrency(totalPrice, "EUR")}
                </div>

                <div>
                    symbol
                </div>
            </div>
        </div>
    )
}

export default OldOrderListElement;