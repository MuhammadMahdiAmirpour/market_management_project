package ir.ac.kntu.CLI;

import ir.ac.kntu.enums.CourierOptions;
import ir.ac.kntu.objects.Courier;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static ir.ac.kntu.logic.CourierOperator.editCourierInfo;
import static ir.ac.kntu.logic.ObjectBuilder.createCourier;

public class CourierPanel {
    public static void showCourierOptions() {
        System.out.println("""
                EI : edit info
                EXIT
                """);
    }

    public static void addNewCourier(Scanner scanner, ArrayList<Courier> couriers) {
        Panel.getCouriersInstance().add(createCourier(scanner, couriers));
    }

    public static void runCourierPanel(Scanner scanner, Courier courier) {
        System.out.println("welcome" + courier.getUsername());
        if (chooseWhatHappens(scanner) == CourierOptions.EI) {
            editCourierInfo(scanner, courier);
        }
   }

    private static CourierOptions chooseWhatHappens(Scanner scanner) {
        String input;
        AtomicReference<CourierOptions> result = new AtomicReference<>(null);
        do {
            showCourierOptions();
            input = scanner.nextLine();
            if (!CourierOptions.contains(input)){
                System.out.println("""
                        wrong input!
                        try again ...
                        """);
                continue;
            }
            result.set(CourierOptions.match(input));
        } while (!CourierOptions.contains(input));
        return result.get();
    }
}
