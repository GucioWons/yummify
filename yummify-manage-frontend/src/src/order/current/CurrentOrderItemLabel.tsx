import {Dtos} from "../../common/dtos.ts";
import OrderItemStatus = Dtos.OrderItemStatus;
import Label from "../../common/label/Label.tsx";

export interface CurrentOrderItemLabel {
    status: OrderItemStatus
}

function CurrentOrderItemLabel(props: CurrentOrderItemLabel) {
    const {status} = props;

    const text = status.charAt(0).toUpperCase() + status.slice(1).toLowerCase();

    switch (status) {
        case OrderItemStatus.NEW:
            return (
                <Label
                    text={text}
                    color="BLUE"
                />
            );

        case OrderItemStatus.IN_PREPARATION:
            return (
                <Label
                    text={text}
                    color="ORANGE"
                />
            );

        case OrderItemStatus.READY:
            return (
                <Label
                    text={text}
                    color="GREEN"
                />
            );

        case OrderItemStatus.DELIVERED:
            return (
                <Label
                    text={text}
                    color="GREY"
                />
            );

        case OrderItemStatus.CANCELLED:
            return (
                <Label
                    text={text}
                    color="RED"
                />
            );
    }
}

export default CurrentOrderItemLabel;