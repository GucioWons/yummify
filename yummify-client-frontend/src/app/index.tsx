import {StyleSheet, Text, TouchableOpacity} from 'react-native';
import {SafeAreaView} from 'react-native-safe-area-context';

import {AnimatedIcon} from '@/components/animated-icon';
import {ThemedText} from '@/components/themed-text';
import {ThemedView} from '@/components/themed-view';
import {BottomTabInset, MaxContentWidth, Spacing} from '@/constants/theme';
import {router} from "expo-router";

export default function HomeScreen() {
    return (
        <ThemedView style={styles.container}>
            <SafeAreaView style={styles.safeArea}>
                <ThemedView style={styles.heroSection}>
                    <AnimatedIcon/>
                    <ThemedText type="title" style={styles.title}>
                        Welcome to&nbsp;Yummify
                    </ThemedText>
                </ThemedView>

                <ThemedText type="code" style={styles.code}>
                    Register device
                </ThemedText>

                <TouchableOpacity style={styles.button}>
                    <Text style={styles.buttonText}>Scan QR Code</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.button} onPress={() => router.push("/register-manually")}>
                    <Text style={styles.buttonText}>Register manually</Text>
                </TouchableOpacity>
            </SafeAreaView>
        </ThemedView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        flexDirection: 'row',
    },
    safeArea: {
        flex: 1,
        paddingHorizontal: Spacing.four,
        alignItems: 'center',
        gap: Spacing.three,
        paddingBottom: BottomTabInset + Spacing.three,
        maxWidth: MaxContentWidth,
    },
    heroSection: {
        alignItems: 'center',
        justifyContent: 'center',
        flex: 1,
        paddingHorizontal: Spacing.four,
        gap: Spacing.four,
    },
    title: {
        textAlign: 'center',
    },
    code: {
        textTransform: 'uppercase',
    },
    stepContainer: {
        gap: Spacing.three,
        alignSelf: 'stretch',
        paddingHorizontal: Spacing.three,
        paddingVertical: Spacing.four,
        borderRadius: Spacing.four,
    },
    button: {
        paddingVertical: 16,
        borderRadius: 8,
        backgroundColor: "#222",
        alignItems: "center",
    },
    buttonText: {
        color: "#fff",
        fontSize: 16,
        fontWeight: "500",
    },
});
