import {ChefHat, CircleCheck, CircleX, Clock, Flame, Truck} from "lucide-react";
import Label from "../../common/label/Label.tsx";
import {Dtos} from "../../common/dtos.ts";
import OrderStatus = Dtos.OrderStatus;

export interface CurrentOrderLabel {
    status: OrderStatus;
}

function CurrentOrderLabel(props: CurrentOrderLabel) {
    const {status} = props;

    const text = status.charAt(0).toUpperCase() + status.slice(1).toLowerCase();

    switch (status) {
        case OrderStatus.NEW:
            return (
                <Label
                    text={text}
                    color="BLUE"
                    icon={Clock}
                />
            );

        case OrderStatus.SUBMITTED:
            return (
                <Label
                    text={text}
                    color="YELLOW"
                    icon={Flame}
                />
            );

        case OrderStatus.IN_PREPARATION:
            return (
                <Label
                    text={text}
                    color="ORANGE"
                    icon={ChefHat}
                />
            );

        case OrderStatus.DELIVERED:
            return (
                <Label
                    text={text}
                    color="GREEN"
                    icon={Truck}
                />
            );

        case OrderStatus.COMPLETED:
            return (
                <Label
                    text={text}
                    color="GREY"
                    icon={CircleCheck}
                />
            );

        case OrderStatus.CANCELLED:
            return (
                <Label
                    text={text}
                    color="RED"
                    icon={CircleX}
                />
            );
    }
}

export default CurrentOrderLabel;