package ir.ac.kntu.CLI;

import ir.ac.kntu.enums.AdminOptions;
import ir.ac.kntu.logic.ObjectBuilder;
import ir.ac.kntu.objects.Admin;
import ir.ac.kntu.objects.SuperMarket;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static ir.ac.kntu.logic.AdminOperator.*;

public class AdminPanel {

    public static void showSupermarketsList() {
        Panel.getSuperMarketsInstance().forEach(superMarket -> {
            System.out.println("supermarket name : " + superMarket.getName());
        });
        System.out.println("exit");
    }

    public static void showAdminOptions() {
        System.out.println("""
                SSS: show store supply
                SSL: show supply list
                BS: buy supply
                PTS: print total supply
                EOI: edit order information
                GL: get list
                TT: timeTravel
                SCO: see costumers order
                EXIT
                """);
    }

    public static SuperMarket chooseSupermarket(Scanner scanner) {
        AtomicReference<SuperMarket> result = new AtomicReference<>(null);
        String choice;
        do {
            System.out.println("choose supermarket");
            showSupermarketsList();
            choice = scanner.nextLine();
            String finalChoice = choice;
            Panel.getSuperMarketsInstance().forEach(superMarket -> {
                if (superMarket.getName().equals(finalChoice)) {
                    result.set(superMarket);
                }
            });
        } while (!(containsSuperMarket(choice) || !choice.equalsIgnoreCase("exit")));
        return result.get();
    }

    private static AdminOptions chooseWhatHappens(Scanner scanner) {
        AtomicReference<AdminOptions> result = new AtomicReference<>();
        String input;
        do {
            System.out.println("choose: ");
            showAdminOptions();
            input = scanner.nextLine();
            if (!AdminOptions.contains(input)) {
                System.out.println("""
                        wrong input
                        try again
                        """);
                continue;
            }
            result.set(AdminOptions.match(input));
        } while (!AdminOptions.contains(input));
        return result.get();
    }

    public static void runAdminPanel(Scanner scanner, Admin admin) throws InterruptedException {
        System.out.println("welcome " + admin.getUserName());
        SuperMarket superMarket = chooseSupermarket(scanner);
        if (superMarket == null) {
            return;
        }
        AdminOptions choice = chooseWhatHappens(scanner);
        switch (choice) {
            case PTS -> showTotalSupply();
            case SSS -> showStoreSupply(superMarket);
            case SSL -> showSupplyList(superMarket);
            case BS -> buySupplyForTheSuperMarket(superMarket);
            case EOI -> editOrderState(scanner);
            case PCL -> printCouriersList();
            case CP -> changePassword(scanner);
            case CU -> changeUsername(scanner);
            case GL -> getList(scanner);
            case TT -> timeTravel(scanner);
            case SCO -> seeCostumersOrder(scanner);
        }
    }

    private static void changeUsername(Scanner scanner) {
        Admin.getInstance().setUsername(ObjectBuilder.getTheUserName(scanner, Panel.getNodes()));
    }

    private static void changePassword(Scanner scanner) {
        Admin.getInstance().setPassword(ObjectBuilder.getThePassword(scanner, Panel.getNodes()));
    }

    private static void printCouriersList() {
        Panel.getCouriersInstance().forEach(System.out::println);
    }
}
