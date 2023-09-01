package ir.ac.kntu.enums;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum AdminOptions {
    SSS, // show store supply
    SSL, // show supply list
    BS, // by supply
    PTS, // print total supply of all supermarkets
    EOI, // edit order state
    PCL, //print the couriers list
    CP, // change the password
    CU, // change the username
    GL, // get list
    TT, // time travel
    SCO, // see costumers order
    EXIT;

    public static AdminOptions match(String input) {
        AtomicReference<AdminOptions> result = new AtomicReference<>(null);
        Arrays.stream(AdminOptions.values()).forEach(adminOption -> {
            if (input.equalsIgnoreCase(adminOption.toString())) {
                result.set(adminOption);
            }
        });
        return result.get();
    }

    public static boolean contains(String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(AdminOptions.values()).forEach(adminOption -> {
            if (input.equalsIgnoreCase(adminOption.toString())) {
                result.set(true);
            }
        });
        return result.get();
    }
}
