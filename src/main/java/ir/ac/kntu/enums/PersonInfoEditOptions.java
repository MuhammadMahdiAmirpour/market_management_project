package ir.ac.kntu.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum PersonInfoEditOptions {
    FN, //first name
    LN, //last name
    UN, // username
    PASSWORD, // password
    LOCATION, // location
    ID, // national ID
    PHONE, // phone number
    WB; // wallet balance

    public static boolean contains(String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(PersonInfoEditOptions.values()).forEach(personInfoEditOptions -> {
            if (input.equalsIgnoreCase(personInfoEditOptions.toString())) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static PersonInfoEditOptions match(String input) {
        AtomicReference<PersonInfoEditOptions> result = new AtomicReference<>(null);
        Arrays.stream(PersonInfoEditOptions.values()).forEach(personInfoEditOptions -> {
            if (input.equalsIgnoreCase(personInfoEditOptions.toString())) {
                result.set(personInfoEditOptions);
            }
        });
        return result.get();
    }

    public static void showOptions() {
        Arrays.stream(PersonInfoEditOptions.values()).forEach(System.out::println);
    }
}
