import {ThemedView} from "@/components/themed-view";
import {ThemedText} from "@/components/themed-text";
import {ActivityIndicator, Alert, FlatList, Pressable, ScrollView, StyleSheet} from "react-native";
import {router, Stack} from "expo-router";
import {ShoppingCart} from "lucide-react-native";
import {useEffect, useState} from "react";
import {useMenuStore} from "@/store/menuStore";
import {getMenu} from "@/api/menuApi";
import {MenuSectionClientDTO} from "@/types/menu";
import {getDishes} from "@/api/dishApi";
import {useDishesStore} from "@/store/dishesStore";
import {useOrderStore} from "@/store/orderStore";
import {addItem, removeItem} from "@/api/orderApi";

export default function MenuScreen() {
    const {menu, setMenu} = useMenuStore();
    const orderStore = useOrderStore();
    const {dishes, setDishes} = useDishesStore();

    const [menuLoading, setMenuLoading] = useState(true)
    const [selectedSection, setSelectedSection] = useState<MenuSectionClientDTO>();

    const addOrderItem = async (dishId: string, quantity: number) => {
        const item = await addItem({dishId: dishId, quantity: quantity});
        orderStore.addOrUpdateItem(item);
    }

    const removeOrderItem = async (itemId: string) => {
        await removeItem(itemId);
        orderStore.removeItem(itemId)
    }

    useEffect(() => {
        const loadMenu = async () => {
            try {
                const [fetchedMenu, fetchedDishes] = await Promise.all([
                    getMenu(),
                    getDishes()
                ])

                setMenu(fetchedMenu);
                setDishes(fetchedDishes)

                if (fetchedMenu.sections.length !== 0) {
                    setSelectedSection(fetchedMenu.sections[0])
                }
            } catch (error) {
                console.error("Failed to get menu:", error,);
                Alert.alert("Error", "Could not get menu.",);
            } finally {
                setMenuLoading(false);
            }
        };

        loadMenu();
    }, []);

    if (menuLoading) {
        return (
            <ThemedView style={styles.loadingContainer}>
                <ActivityIndicator />
            </ThemedView>
        );
    }

    return (
        <>
            <Stack.Screen
                options={{
                    title: "Menu",
                    headerRight: () => (
                        <Pressable
                            onPress={() => router.push("/order")}
                            style={styles.cartButton}
                        >
                            <ShoppingCart size={24} />
                        </Pressable>
                    ),
                }}
            />

            <ThemedView style={styles.container}>

                <ThemedView style={styles.content}>
                    <FlatList
                        data={selectedSection?.entries ?? []}
                        keyExtractor={(entry) => entry.id}
                        contentContainerStyle={styles.dishesContainer}
                        renderItem={({ item: entry }) => {
                            const dish = dishes[entry.dishId];

                            const orderItem = orderStore.order?.items.find(
                                (item) => item.dishId === entry.dishId
                            );

                            const quantity = orderItem?.quantity ?? 0;

                            return (
                                <ThemedView style={styles.dishCard}>
                                    <ThemedView style={styles.dishImagePlaceholder}>
                                        <ThemedText style={styles.placeholderText}>
                                            Image
                                        </ThemedText>
                                    </ThemedView>

                                    <ThemedView style={styles.dishDetails}>
                                        <ThemedView style={styles.dishInfo}>
                                            <ThemedText style={styles.dishName}>
                                                {dish?.name ?? "Loading..."}
                                            </ThemedText>

                                            <ThemedText style={styles.dishPrice}>
                                                {entry.price.toFixed(2)} €
                                            </ThemedText>
                                        </ThemedView>

                                        <ThemedView style={styles.quantityContainer}>
                                            {quantity > 0 && (
                                                <>
                                                    <Pressable style={styles.quantityButton} onPress={() => removeOrderItem(orderItem!.id)}>
                                                        <ThemedText style={styles.quantityButtonText}>
                                                            -
                                                        </ThemedText>
                                                    </Pressable>

                                                    <ThemedText style={styles.quantity}>
                                                        {quantity}
                                                    </ThemedText>
                                                </>
                                            )}

                                            <Pressable style={styles.quantityButton} onPress={() => addOrderItem(dish.id, 1)}>
                                                <ThemedText style={styles.quantityButtonText}>
                                                    +
                                                </ThemedText>
                                            </Pressable>
                                        </ThemedView>
                                    </ThemedView>
                                </ThemedView>
                            );
                        }}
                    />
                </ThemedView>

                <ThemedView style={styles.navigationContainer}>
                <ScrollView
                    horizontal
                    showsHorizontalScrollIndicator={false}
                    contentContainerStyle={styles.sectionNavigation}
                >
                    {menu?.sections.map((section) => (
                        <Pressable
                            key={section.id}
                            onPress={() => setSelectedSection(section)}
                            style={[
                                styles.sectionButton,
                                selectedSection?.id === section.id && styles.selectedSectionButton,
                            ]}
                        >
                            <ThemedText
                                style={[
                                    styles.sectionButtonText,
                                    selectedSection?.id === section.id && styles.selectedSectionButtonText,
                                ]}
                            >
                                {section.name}
                            </ThemedText>
                        </Pressable>
                    ))}
                </ScrollView>
                </ThemedView>

            </ThemedView>
        </>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },

    loadingContainer: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },

    content: {
        flex: 1,
        paddingHorizontal: 16,
    },

    dishesContainer: {
        paddingVertical: 16,
        gap: 16,
    },

    dishCard: {
        width: "100%",
        marginBottom: 20,
    },

    dishImagePlaceholder: {
        width: "100%",
        height: 180,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "#E5E7EB",
        borderRadius: 12,
    },

    placeholderText: {
        fontSize: 16,
        color: "#6B7280",
    },

    dishDetails: {
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
        marginTop: 10,
    },

    dishInfo: {
        flex: 1,
        marginRight: 16,
    },

    dishName: {
        fontSize: 20,
        fontWeight: "600",
    },

    dishPrice: {
        marginTop: 4,
        fontSize: 17,
    },

    quantityContainer: {
        flexDirection: "row",
        alignItems: "center",
        gap: 10,
    },

    quantityButton: {
        width: 40,
        height: 40,
        borderRadius: 20,
        borderWidth: 1,
        alignItems: "center",
        justifyContent: "center",
    },

    quantityButtonText: {
        fontSize: 24,
        lineHeight: 28,
    },

    quantity: {
        minWidth: 24,
        textAlign: "center",
        fontSize: 19,
        fontWeight: "600",
    },

    navigationContainer: {
        borderTopWidth: 1,
        paddingVertical: 8,
    },

    sectionNavigation: {
        paddingHorizontal: 12,
        gap: 8,
    },

    sectionButton: {
        paddingHorizontal: 20,
        paddingVertical: 14,
        borderRadius: 24,
    },

    selectedSectionButton: {
        backgroundColor: "#E5E7EB",
    },

    sectionButtonText: {
        fontSize: 18,
    },

    selectedSectionButtonText: {
        fontWeight: "700",
    },

    cartButton: {
        padding: 8,
        marginRight: 4,
    },
});