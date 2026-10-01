import {useState} from "react";
import {ThemedView} from "@/components/themed-view";
import {ThemedText} from "@/components/themed-text";
import {Alert, StyleSheet, TextInput, TouchableOpacity} from "react-native";
import {login} from "@/api/authApi";
import axios from "axios";
import {getRestaurant} from "@/api/restaurantApi";
import {useAuthStore} from "@/store/authStore";
import {router} from "expo-router";
import * as ExpoSecureStore from "expo-secure-store";

export default function RegisterManuallyScreen() {
    const [tableId, setTableId] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    const handleRegister = async () => {
        console.log("Table ID:", tableId);
        console.log("Password:", password);

        if (!tableId || !password) {
            Alert.alert("Missing data", "Please enter Table ID and Password.");
            return;
        }

        try {
            setLoading(true);
            const response = await login({username: tableId, otp: password,});
            await ExpoSecureStore.setItemAsync("access_token", response.access_token);
            const restaurant = await getRestaurant();
            useAuthStore.getState().setSession(response.access_token, restaurant);
            router.dismissAll();
            router.replace("/home");
        } catch (error) {
            if (axios.isAxiosError(error)) {
                console.log("Axios error:", error.message);
                console.log("Error code:", error.code);
                console.log("Error URL:", error.config?.url);
                console.log("Error method:", error.config?.method);
                console.log("Error baseURL:", error.config?.baseURL);
                console.log("Response status:", error.response?.status);
                console.log("Response data:", error.response?.data);
                console.log("Response headers:", error.response?.headers);
                console.log("Request:", error.request);
            } else {
                console.log("Unknown error:", error);
            }
            Alert.alert("Registration failed", "Could not register the device.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <ThemedView style={styles.container}>
            <ThemedText style={styles.title}>Register device</ThemedText>
            <ThemedView style={styles.form}>
                <ThemedText style={styles.label}>Table ID</ThemedText>
                <TextInput style={styles.input} value={tableId} onChangeText={setTableId} placeholder="Enter table ID" autoCapitalize="none" />
                <ThemedText style={styles.label}>Password</ThemedText>
                <TextInput style={styles.input} value={password} onChangeText={setPassword} placeholder="Enter password" secureTextEntry autoCapitalize="none" />
                <TouchableOpacity style={styles.button} onPress={handleRegister} disabled={loading}>
                    <ThemedText style={styles.buttonText}>
                        {loading ? "Registering..." : "Register"}
                    </ThemedText>
                </TouchableOpacity>
            </ThemedView>
        </ThemedView>
    );
}

const styles = StyleSheet.create({
    container: {flex: 1, padding: 24, justifyContent: "center",},
    title: {fontSize: 32, fontWeight: "600", textAlign: "center", marginBottom: 40,},
    form: {width: "100%", maxWidth: 400, alignSelf: "center",},
    label: {fontSize: 16, fontWeight: "500", marginBottom: 8,},
    input: {
        height: 50,
        borderWidth: 1,
        borderColor: "#ccc",
        borderRadius: 8,
        paddingHorizontal: 16,
        fontSize: 16,
        marginBottom: 20,
    },
    button: {
        height: 50,
        borderRadius: 8,
        backgroundColor: "#222",
        alignItems: "center",
        justifyContent: "center",
        marginTop: 10,
    },
    buttonText: {color: "#fff", fontSize: 16, fontWeight: "500",},
});
