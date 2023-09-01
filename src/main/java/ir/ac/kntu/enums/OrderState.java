package ir.ac.kntu.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum OrderState {
    DELIVERED,
    PROCESSING,
    CANCELED,
    RETURNED,
    DBC, // delivery by courier,
    AP; // awaiting payment

    public static boolean contains(String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(OrderState.values()).forEach(orderState -> {
            if (input.equalsIgnoreCase(orderState.toString())) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static OrderState match (String input) {
        AtomicReference<OrderState> result = new AtomicReference<>();
        Arrays.stream(OrderState.values()).forEach(orderState -> {
            if (input.equalsIgnoreCase(orderState.toString())) {
                result.set(orderState);
            }
        });
        return result.get();
    }
}
