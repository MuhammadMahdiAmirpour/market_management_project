package ir.ac.kntu.CLI;

import ir.ac.kntu.enums.CostumerOptions;
import ir.ac.kntu.objects.Costumer;

import java.util.Objects;
import java.util.Scanner;

import static ir.ac.kntu.logic.CostumerOperator.*;


public class CostumerPanel {
    public static void showCostumerOptions() {
        System.out.println("""
                BG : buy goods
                SOH : show order history
                EI : edit info
                EXIT
                """);
    }

    public static void runCostumerPanel(Scanner scanner, Costumer costumer) {
        System.out.println("welcome" + costumer.getUsername());
        String choice;
        CostumerOptions costumerOptions = null;
        do {
            showCostumerOptions();
            choice = scanner.nextLine();
            if (CostumerOptions.contains(choice)) {
                costumerOptions = CostumerOptions.match(choice);
            } else {
                System.out.println("""
                        wrong input
                        try again ...
                        """);
            }
        } while (!CostumerOptions.contains(choice));
        switch (Objects.requireNonNull(costumerOptions)) {
            case BG -> costumerBuysGoods(scanner, costumer);
            case EI -> editCostumerInfo(scanner, costumer);
            case SOH -> showCostumerOrderHistory(costumer);
        }
    }
}
