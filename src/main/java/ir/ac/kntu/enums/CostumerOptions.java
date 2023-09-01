package ir.ac.kntu.enums;

public enum CostumerOptions {
    BG, // buy goods
    SOH, // show order history
    EI, // edit info
    EXIT;

    public static CostumerOptions match(String input) {
        if (input.equalsIgnoreCase("bg")) {
            return BG;
        }
        if (input.equalsIgnoreCase("soh")) {
            return SOH;
        }
        if (input.equalsIgnoreCase("ei")) {
            return EI;
        }
        if (input.equalsIgnoreCase("exit")) {
            return EXIT;
        }
        return null;
    }

    public static boolean contains(String input) {
        if (input.equalsIgnoreCase("bg")) {
            return true;
        }
        if (input.equalsIgnoreCase("soh")) {
            return true;
        }
        if (input.equalsIgnoreCase("ei")) {
            return true;
        }
        return input.equalsIgnoreCase("exit");
    }
}
