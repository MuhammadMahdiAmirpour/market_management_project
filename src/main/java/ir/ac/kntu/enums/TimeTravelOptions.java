package ir.ac.kntu.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum TimeTravelOptions {
    MONTH,
    DAY,
    MINUET,
    HOUR,
    WEEK;

    public static TimeTravelOptions match(String input) {
        AtomicReference<TimeTravelOptions> result = new AtomicReference<>(null);
        Arrays.stream(TimeTravelOptions.values()).forEach(timeTravelOptions -> {
            if (input.equalsIgnoreCase(timeTravelOptions.toString())) {
                result.set(timeTravelOptions);
            }
        });
        return result.get();
    }

    public static boolean contains (String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(TimeTravelOptions.values()).forEach(timeTravelOptions -> {
            if (input.equalsIgnoreCase(timeTravelOptions.toString())) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static void showOptions(){
        Arrays.stream(TimeTravelOptions.values()).forEach(System.out::println);
    }
}
