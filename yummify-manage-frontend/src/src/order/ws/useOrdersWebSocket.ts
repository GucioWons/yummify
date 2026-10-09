import {useQueryClient} from "@tanstack/react-query";
import {useEffect} from "react";
import {Client} from "@stomp/stompjs";
import {Dtos} from "../../common/dtos.ts";
import {handleOrderMessage} from "./orderWebSocketUtils.ts";
import OrderWebSocketMessage = Dtos.OrderWebSocketMessage;

export function useRestaurantOrdersWebSocket(
    restaurantId: string | undefined,
    accessToken: string | undefined,
) {
    const queryClient = useQueryClient();

    useEffect(() => {
        if (!restaurantId || !accessToken) return;

        const client = new Client({
            brokerURL: 'ws://localhost:9090/api/ws',
            connectHeaders: {
                Authorization: `Bearer ${accessToken}`,
            },
            reconnectDelay: 5000,
            onConnect: () => {
                client.subscribe(
                    `/topic/restaurants/${restaurantId}/orders`,
                    message => {
                        const event: OrderWebSocketMessage = JSON.parse(message.body);
                        handleOrderMessage(event, queryClient);
                    },
                );
            },
        });

        client.activate();

        return () => {
            void client.deactivate();
        };
    }, [accessToken, queryClient, restaurantId]);
}