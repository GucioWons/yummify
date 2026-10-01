import {ActivityIndicator, Alert, StyleSheet, TouchableOpacity} from "react-native";
import {useAuthStore} from "@/store/authStore";
import {ThemedView} from "@/components/themed-view";
import {ThemedText} from "@/components/themed-text";
import {useEffect, useState} from "react";
import {createOrder, getOrder} from "@/api/orderApi";
import {router} from "expo-router";
import {useOrderStore} from "@/store/orderStore";
import axios from "axios";

export default function HomeScreen() {
    const restaurant = useAuthStore((state) => state.restaurant);
    const setOrder = useOrderStore((state) => state.setOrder);

    const [checkingOrder, setCheckingOrder] = useState(true);
    const [creatingOrder, setCreatingOrder] = useState(false);

    useEffect(() => {
        const checkActiveOrder = async () => {
            try {
                const order = await getOrder();

                setOrder(order);

                router.replace("/menu");
            } catch (error) {
                if (axios.isAxiosError(error) && error.response?.status === 404) {
                    console.log("No active order");
                    return;
                }

                console.error("Failed to get active order:", error,);
                Alert.alert("Error", "Could not check active order.",);
            } finally {
                setCheckingOrder(false);
            }
        };

        checkActiveOrder();
    }, []);

    const handleNewOrder = async () => {
        try {
            setCreatingOrder(true);

            const order = await createOrder();

            setOrder(order);

            router.replace("/menu");
        } catch (error) {
            console.error("Creating order failed:", error);
            Alert.alert("Error", "Could not create a new order.",);
        } finally {
            setCreatingOrder(false);
        }
    };

    if (checkingOrder) {
        return (
            <ThemedView style={styles.loadingContainer}>
                <ActivityIndicator />
            </ThemedView>
        );
    }

    return (
        <ThemedView style={styles.container}>
            <ThemedText style={styles.title}>
                Welcome in {restaurant?.name}
            </ThemedText>

            <TouchableOpacity
                style={styles.button}
                onPress={handleNewOrder}
                disabled={creatingOrder}
            >
                <ThemedText style={styles.buttonText}>
                    {creatingOrder ? "Creating..." : "New Order"}
                </ThemedText>
            </TouchableOpacity>
        </ThemedView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        padding: 24,
    },
    loadingContainer: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
    title: {
        fontSize: 28,
        fontWeight: "600",
        marginBottom: 32,
        textAlign: "center",
    },
    button: {
        width: "100%",
        maxWidth: 400,
        height: 50,
        borderRadius: 8,
        backgroundColor: "#222",
        alignItems: "center",
        justifyContent: "center",
    },
    buttonText: {
        color: "#fff",
        fontSize: 16,
        fontWeight: "500",
    },
});