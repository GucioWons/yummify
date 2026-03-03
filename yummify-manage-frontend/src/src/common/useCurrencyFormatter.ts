const currencySymbols: Record<string, string> = {
    USD: "$",
    EUR: "€",
    PLN: "zł",
};

export const formatCurrency = (
    amount: number,
    currency: keyof typeof currencySymbols
): string => {
    return `${amount.toFixed(2)} ${currencySymbols[currency]}`;
};