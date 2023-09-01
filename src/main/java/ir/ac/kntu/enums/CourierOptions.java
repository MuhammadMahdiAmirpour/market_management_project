package ir.ac.kntu.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum CourierOptions {
    EI,
    EXIT; // edit info

    public static CourierOptions match(String input) {
        AtomicReference<CourierOptions> result = new AtomicReference<>(null);
        Arrays.stream(CourierOptions.values()).forEach(courierOptions -> {
            if (input.equalsIgnoreCase(courierOptions.toString())) {
                result.set(courierOptions);
            }
        });
        return result.get();
    }

    public static boolean contains(String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(CourierOptions.values()).forEach(courierOptions -> {
            if (input.equalsIgnoreCase(courierOptions.toString())) {
                result.set(true);
            }
        });
        return result.get();
    }
}
