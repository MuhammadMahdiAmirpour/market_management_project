package ir.ac.kntu.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum Choice {
    PST,
    SBP,
    SBE,
    SBQ,
    SEC,
    PCR,
    NEW,
    exit;
    public static boolean contains(String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(Choice.values()).forEach(choice -> {
            if (input.equalsIgnoreCase(choice.toString())) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static Choice match(String input) {
        AtomicReference<Choice> result = new AtomicReference<>(null);
        Arrays.stream(Choice.values()).forEach(choice -> {
            if (input.equalsIgnoreCase(choice.toString())) {
                result.set(choice);
            }
        });
        return result.get();
    }
}
