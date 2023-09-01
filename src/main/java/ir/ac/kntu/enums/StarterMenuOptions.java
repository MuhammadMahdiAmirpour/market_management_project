package ir.ac.kntu.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum StarterMenuOptions {
    ADMIN,
    COURIER,
    COSTUMER,
    SUPERMARKET,
    NEW,
    SAW, // seat and watch
    EXIT;

    public static boolean contains(String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(StarterMenuOptions.values()).forEach(starterMenuOptions -> {
            if (input.equalsIgnoreCase(starterMenuOptions.toString())){
                result.set(true);
            }
        });
        return result.get();
    }

    public static StarterMenuOptions match(String input) {
        AtomicReference<StarterMenuOptions> result = new AtomicReference<>(null);
        Arrays.stream(StarterMenuOptions.values()).forEach(starterMenuOptions -> {
            if (input.equalsIgnoreCase(starterMenuOptions.toString())) {
                result.set(starterMenuOptions);
            }
        });
        return result.get();
    }
}
