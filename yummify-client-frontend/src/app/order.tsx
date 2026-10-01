import {ThemedView} from "@/components/themed-view";
import {ThemedText} from "@/components/themed-text";
import {FlatList, StyleSheet, TouchableOpacity} from "react-native";
import {Stack} from "expo-router";
import {
    CheckCircle,
    ChefHat,
    CircleCheck,
    CircleX,
    Clock,
    CreditCard,
    HelpCircle,
    Truck,
    XCircle
} from "lucide-react-native";

import {OrderItemStatus, OrderStatus} from "@/types/order";
import {useOrderStore} from "@/store/orderStore";
import {addItem, removeItem, requestAssistance, requestPayment, submitOrder} from "@/api/orderApi";

const ORDER_STATUS_CONFIG: Record<OrderStatus, {text: string, icon: typeof Clock}> = {
    [OrderStatus.NEW]: {
        text: "New",
        icon: Clock,
    },
    [OrderStatus.SUBMITTED]: {
        text: "Submitted",
        icon: CheckCircle,
    },
    [OrderStatus.IN_PREPARATION]: {
        text: "In preparation",
        icon: ChefHat,
    },
    [OrderStatus.DELIVERED]: {
        text: "Delivered",
        icon: Truck,
    },
    [OrderStatus.COMPLETED]: {
        text: "Completed",
        icon: CircleCheck,
    },
    [OrderStatus.CANCELLED]: {
        text: "Cancelled",
        icon: CircleX,
    },
};

const ORDER_ITEM_STATUS_CONFIG: Record<OrderItemStatus, {text: string, icon: typeof Clock}> = {
    [OrderItemStatus.NEW]: {
        text: "New",
        icon: Clock,
    },
    [OrderItemStatus.IN_PREPARATION]: {
        text: "In preparation",
        icon: ChefHat,
    },
    [OrderItemStatus.READY]: {
        text: "Ready",
        icon: CircleCheck,
    },
    [OrderItemStatus.DELIVERED]: {
        text: "Delivered",
        icon: Truck,
    },
    [OrderItemStatus.CANCELLED]: {
        text: "Cancelled",
        icon: CircleX,
    },
};

export default function OrderScreen() {
    const orderStore = useOrderStore();

    const addOrderItem = async (dishId: string, quantity: number) => {
        const item = await addItem({dishId: dishId, quantity: quantity});
        orderStore.addOrUpdateItem(item);
    }

    const removeOrderItem = async (itemId: string) => {
        await removeItem(itemId);
        orderStore.removeItem(itemId)
    }

    const submit = async () => {
        const updatedOrder = await submitOrder();
        orderStore.setOrder(updatedOrder);
    }

    const assistance = async () => {
        const updatedOrder = await requestAssistance();
        orderStore.setOrder(updatedOrder);
    }

    const payment = async () => {
        const updatedOrder = await requestPayment();
        orderStore.setOrder(updatedOrder);
    }

    const order = orderStore.order;

    if (!order) {
        return (
            <>
                <Stack.Screen options={{title: "Order"}}/>

                <ThemedView style={styles.emptyContainer}>
                    <ThemedText style={styles.emptyText}>
                        Your order is empty
                    </ThemedText>
                </ThemedView>
            </>
        );
    }

    const statusConfig = ORDER_STATUS_CONFIG[order.status];
    const StatusIcon = statusConfig.icon;

    const total = order.items.reduce((sum, item) => sum + item.price * item.quantity, 0);

    return (
        <>
            <Stack.Screen options={{title: "Order"}}/>

            <ThemedView style={styles.container}>
                <ThemedView style={styles.content}>

                    <ThemedView style={styles.statusContainer}>
                        <StatusIcon
                            size={24}
                            color="#007AFF"
                        />

                        <ThemedView style={styles.statusTextContainer}>
                            <ThemedText style={styles.statusLabel}>
                                Order status
                            </ThemedText>

                            <ThemedText style={styles.statusText}>
                                {statusConfig.text}
                            </ThemedText>
                        </ThemedView>
                    </ThemedView>

                    <ThemedText style={styles.sectionTitle}>
                        Your order
                    </ThemedText>

                    <FlatList
                        data={order.items}
                        keyExtractor={item => item.id}
                        showsVerticalScrollIndicator={false}
                        contentContainerStyle={styles.itemsContainer}
                        renderItem={({item}) => {
                            const itemStatusConfig =
                                ORDER_ITEM_STATUS_CONFIG[item.status];

                            const ItemStatusIcon =
                                itemStatusConfig.icon;

                            const itemTotal =
                                item.price * item.quantity;

                            return (
                                <ThemedView style={styles.item}>
                                    <ThemedView style={styles.itemInfo}>
                                        <ThemedText style={styles.itemName}>
                                            {item.name}
                                        </ThemedText>

                                        <ThemedView
                                            style={styles.itemStatus}
                                        >
                                            <ItemStatusIcon
                                                size={15}
                                                color="#6B7280"
                                            />

                                            <ThemedText
                                                style={styles.itemStatusText}
                                            >
                                                {itemStatusConfig.text}
                                            </ThemedText>
                                        </ThemedView>

                                        <ThemedText style={styles.itemPrice}>
                                            {item.price.toFixed(2)} zł
                                        </ThemedText>
                                    </ThemedView>

                                    <ThemedView style={styles.itemRight}>
                                        <ThemedView style={styles.quantityContainer}>
                                            <TouchableOpacity style={styles.quantityButton} onPress={() => removeOrderItem(item.id)}>
                                                <ThemedText style={styles.quantityButtonText}>
                                                    −
                                                </ThemedText>
                                            </TouchableOpacity>

                                            <ThemedText style={styles.quantity}>
                                                {item.quantity}
                                            </ThemedText>

                                            <TouchableOpacity style={styles.quantityButton} onPress={() => addOrderItem(item.dishId, 1)}>
                                                <ThemedText style={styles.quantityButtonText}>
                                                    +
                                                </ThemedText>
                                            </TouchableOpacity>
                                        </ThemedView>

                                        <ThemedText style={styles.itemTotal}>
                                            {itemTotal.toFixed(2)} zł
                                        </ThemedText>
                                    </ThemedView>
                                </ThemedView>
                            );
                        }}
                    />

                    <ThemedView style={styles.totalContainer}>
                        <ThemedText style={styles.totalLabel}>
                            Total
                        </ThemedText>

                        <ThemedText style={styles.totalValue}>
                            {total.toFixed(2)} zł
                        </ThemedText>
                    </ThemedView>
                </ThemedView>

                <ThemedView style={styles.buttonsContainer}>
                    {order.assistanceRequested ? (
                        <ThemedView style={styles.requestedContainer}>
                            <HelpCircle size={20} color="#D97706"/>

                            <ThemedText style={styles.requestedText}>
                                Assistance requested
                            </ThemedText>
                        </ThemedView>
                    ) : (
                        <TouchableOpacity
                            style={styles.assistanceButton}
                            onPress={assistance}
                        >
                            <HelpCircle size={20} color="#D97706"/>

                            <ThemedText style={styles.assistanceButtonText}>
                                Request assistance
                            </ThemedText>
                        </TouchableOpacity>
                    )}

                    {order.status === OrderStatus.NEW && (
                        <TouchableOpacity style={styles.confirmButton} onPress={submit}>
                            <CircleCheck size={20} color="#FFFFFF"/>

                            <ThemedText style={styles.buttonText}>
                                Confirm order
                            </ThemedText>
                        </TouchableOpacity>
                    )}

                    {(order.status === OrderStatus.NEW ||
                        order.status === OrderStatus.SUBMITTED) && (
                        <TouchableOpacity style={styles.cancelButton}>
                            <XCircle size={20} color="#DC2626"/>

                            <ThemedText style={styles.cancelButtonText}>
                                Cancel order
                            </ThemedText>
                        </TouchableOpacity>
                    )}

                    {order.status === OrderStatus.DELIVERED &&
                        order.paymentRequested ? (
                                <ThemedView style={styles.paymentRequestedContainer}>
                                    <CreditCard size={20} color="#FFFFFF"/>

                                    <ThemedText style={styles.buttonText}>
                                        Payment requested
                                    </ThemedText>
                                </ThemedView>
                            ) : (
                                <TouchableOpacity style={styles.paymentButton} onPress={payment}>
                                    <CreditCard size={20} color="#FFFFFF"/>

                                    <ThemedText style={styles.buttonText}>
                                        Request payment
                                    </ThemedText>
                                </TouchableOpacity>
                    )}
                </ThemedView>
            </ThemedView>
        </>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
    },

    content: {
        flex: 1,
    },

    statusContainer: {
        flexDirection: "row",
        alignItems: "center",
        padding: 16,
        borderRadius: 12,
        backgroundColor: "#F3F4F6",
        marginBottom: 24,
    },

    statusTextContainer: {
        marginLeft: 12,
    },

    statusLabel: {
        fontSize: 12,
        color: "#6B7280",
        marginBottom: 2,
    },

    statusText: {
        fontSize: 17,
        fontWeight: "600",
    },

    sectionTitle: {
        fontSize: 20,
        fontWeight: "700",
        marginBottom: 12,
    },

    itemsContainer: {
        gap: 10,
    },

    item: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
        padding: 14,
        borderRadius: 10,
        backgroundColor: "#F9FAFB",
    },

    itemInfo: {
        flex: 1,
    },

    itemName: {
        fontSize: 16,
        fontWeight: "600",
    },

    itemStatus: {
        flexDirection: "row",
        alignItems: "center",
        marginTop: 5,
        gap: 5,
    },

    itemStatusText: {
        fontSize: 13,
        color: "#6B7280",
    },

    itemPrice: {
        marginTop: 5,
        fontSize: 14,
        color: "#6B7280",
    },

    itemRight: {
        alignItems: "flex-end",
        marginLeft: 12,
    },

    quantityContainer: {
        flexDirection: "row",
        alignItems: "center",
        gap: 8,
    },

    quantityButton: {
        width: 32,
        height: 32,
        borderRadius: 16,
        alignItems: "center",
        justifyContent: "center",
        backgroundColor: "#E5E7EB",
    },

    quantityButtonText: {
        fontSize: 20,
        fontWeight: "600",
    },

    quantity: {
        minWidth: 20,
        textAlign: "center",
        fontSize: 16,
        fontWeight: "600",
    },

    itemTotal: {
        marginTop: 6,
        fontSize: 16,
        fontWeight: "600",
    },

    totalContainer: {
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "center",
        paddingTop: 16,
        marginTop: 16,
        borderTopWidth: 1,
        borderTopColor: "#E5E7EB",
    },

    totalLabel: {
        fontSize: 18,
        fontWeight: "700",
    },

    totalValue: {
        fontSize: 20,
        fontWeight: "700",
    },

    buttonsContainer: {
        gap: 12,
        marginTop: 16,
    },

    confirmButton: {
        width: "100%",
        minHeight: 52,
        borderRadius: 10,
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        gap: 8,
        backgroundColor: "#007AFF",
    },

    buttonText: {
        color: "#FFFFFF",
        fontSize: 16,
        fontWeight: "600",
    },

    requestedContainer: {
        width: "100%",
        minHeight: 52,
        borderRadius: 10,
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        gap: 8,
        backgroundColor: "#FEF3C7",
        borderWidth: 1,
        borderColor: "#F59E0B",
    },

    requestedText: {
        color: "#B45309",
        fontSize: 16,
        fontWeight: "600",
    },

    assistanceButton: {
        width: "100%",
        minHeight: 52,
        borderRadius: 10,
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        gap: 8,
        backgroundColor: "#FEF3C7",
        borderWidth: 1,
        borderColor: "#F59E0B",
    },

    assistanceButtonText: {
        color: "#B45309",
        fontSize: 16,
        fontWeight: "600",
    },

    cancelButton: {
        width: "100%",
        minHeight: 52,
        borderRadius: 10,
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        gap: 8,
        backgroundColor: "#FEF2F2",
        borderWidth: 1,
        borderColor: "#FECACA",
    },

    cancelButtonText: {
        color: "#DC2626",
        fontSize: 16,
        fontWeight: "600",
    },

    paymentButton: {
        width: "100%",
        minHeight: 52,
        borderRadius: 10,
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        gap: 8,
        backgroundColor: "#16A34A",
    },

    paymentRequestedContainer: {
        width: "100%",
        minHeight: 52,
        borderRadius: 10,
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",
        gap: 8,
        backgroundColor: "#16A34A",
        borderWidth: 1,
        borderColor: "#F59E0B",
    },

    emptyContainer: {
        flex: 1,
        alignItems: "center",
        justifyContent: "center",
        padding: 16,
    },

    emptyText: {
        fontSize: 18,
        color: "#6B7280",
    },
});