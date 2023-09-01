package ir.ac.kntu.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public enum GetListOptions {
    COSTUMERS, //get costumers list
    ORDERS, // get orders list
    COMMODITIES, // get commodity list
    COURIERS; //get couriers list options

    public static boolean contains (String input) {
        AtomicBoolean result = new AtomicBoolean(false);
        Arrays.stream(GetListOptions.values()).forEach(GetListOptions -> {
            if (input.equalsIgnoreCase(GetListOptions.toString())) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static GetListOptions match (String input) {
        AtomicReference<GetListOptions> result = new AtomicReference<>(null);
        Arrays.stream(GetListOptions.values()).forEach(GetListOptions -> {
            if (input.equalsIgnoreCase(GetListOptions.toString())) {
                result.set(GetListOptions);
            }
        });
        return result.get();
    }
}
