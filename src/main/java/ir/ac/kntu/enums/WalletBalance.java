package ir.ac.kntu.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum WalletBalance {
    DEPOSIT,
    WITHDRAW;

    public static boolean contains (String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(WalletBalance.values()).forEach(option -> {
            if (input.equalsIgnoreCase(option.toString())) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static WalletBalance match (String input) {
        AtomicReference<WalletBalance> result = new AtomicReference<>(null);
        Arrays.stream(WalletBalance.values()).forEach(option -> {
            if (input.equalsIgnoreCase(option.toString())) {
                result.set(option);
            }
        });
        return result.get();
    }
}
