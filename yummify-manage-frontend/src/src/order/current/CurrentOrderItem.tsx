import {Dtos} from "../../common/dtos.ts";
import {formatCurrency} from "../../common/useCurrencyFormatter.ts";
import CurrentOrderItemLabel from "./CurrentOrderItemLabel.tsx";
import CurrentOrderButton, {CurrentOrderButtonProps} from "./CurrentOrderButton.tsx";
import {Check, LucideIcon, Play, Truck} from "lucide-react";
import OrderItemClientDto = Dtos.OrderItemClientDto;
import OrderStatus = Dtos.OrderStatus;
import OrderItemStatus = Dtos.OrderItemStatus;
import {orderService} from "../service/orderService.ts";
import {useMutation, useQueryClient} from "@tanstack/react-query";

interface OrderItemAction {
    text: string;
    color: CurrentOrderButtonProps['color'];
    icon: LucideIcon;
    mutation: (item: OrderItemClientDto, orderId: string) => Promise<unknown>;
    shouldShow?: (orderStatus: OrderStatus) => boolean;
}

const ORDER_ITEM_ACTIONS: Partial<Record<OrderItemStatus, OrderItemAction>> = {
    [OrderItemStatus.NEW]: {
        text: 'Start',
        color: 'ORANGE',
        icon: Play,
        mutation: (item, orderId) => orderService.startPreparation(orderId, item.id),
        shouldShow: orderStatus => orderStatus !== OrderStatus.NEW
    },

    [OrderItemStatus.IN_PREPARATION]: {
        text: 'Ready',
        color: 'GREEN',
        icon: Check,
        mutation: (item, orderId) => orderService.finishPreparation(orderId, item.id),
    },

    [OrderItemStatus.READY]: {
        text: 'Serve',
        color: 'BLUE',
        icon: Truck,
        mutation: (item, orderId) => orderService.serve(orderId, item.id),
    },
};

export interface CurrentOrderItemProps {
    item: OrderItemClientDto;
    orderStatus: OrderStatus;
    orderId: string;
}

function CurrentOrderItem(props: CurrentOrderItemProps) {
    const {item, orderStatus, orderId} = props;

    const action = ORDER_ITEM_ACTIONS[item.status];

    const queryClient = useQueryClient();

    const handleAction = useMutation({
        mutationFn: () => {
            if (!action) {
                throw new Error('No action defined for item status');
            }

            return action.mutation(item, orderId);
        },
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["orders", "current"]}),
        onError: () => {}
    });

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

                {action && (!action.shouldShow || action.shouldShow(orderStatus)) &&
                    <CurrentOrderButton
                        text={action.text}
                        color={action.color}
                        icon={action.icon}
                        onClick={handleAction.mutate}
                    />
                }
            </div>
        </div>
    );
}

export default CurrentOrderItem;