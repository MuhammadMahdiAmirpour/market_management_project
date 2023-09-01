package ir.ac.kntu.logic;

import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.enums.StarterMenuOptions;
import ir.ac.kntu.objects.Costumer;
import ir.ac.kntu.objects.SuperMarket;

import java.util.ArrayList;
import java.util.Scanner;

import static ir.ac.kntu.CLI.CourierPanel.addNewCourier;
import static ir.ac.kntu.CLI.Panel.clearConsole;
import static ir.ac.kntu.CLI.Panel.getCostumersInstance;
import static ir.ac.kntu.logic.ObjectBuilder.createCostumer;
import static ir.ac.kntu.logic.ObjectBuilder.createSuperMarket;

public class NodeAdder {

    public static void addNewNode(Scanner scanner) {
        String input;
        do {
            System.out.println("""
                    enter as :
                    SUPERMARKET
                    COSTUMER
                    COURIER
                    exit
                    """);
            input = scanner.nextLine();
            if (!(input.equalsIgnoreCase("SUPERMARKET") || input.equalsIgnoreCase("COSTUMER") ||
                    input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("COURIER"))) {
                clearConsole();
                System.out.println("""
                        wrong input
                        enter your choice again:
                        """);
            }
        } while (!(input.equalsIgnoreCase("SUPERMARKET") || input.equalsIgnoreCase("COSTUMER") ||
                input.equalsIgnoreCase("COURIER") || input.equalsIgnoreCase("exit")));
        StarterMenuOptions starterMenuOptions = StarterMenuOptions.valueOf(input.toUpperCase());
        switch (starterMenuOptions) {
            case COSTUMER -> {
                addNewCostumer(scanner, getCostumersInstance());
            }
            case COURIER -> {
                addNewCourier(scanner, Panel.getCouriersInstance());
            }
            case SUPERMARKET -> {
                addNewSuperMarket(scanner, Panel.getSuperMarketsInstance());
            }
        }
    }

    public static void addNewSuperMarket(Scanner scanner, ArrayList<SuperMarket> superMarkets) {
        Panel.getSuperMarketsInstance().add(createSuperMarket(scanner, superMarkets));
    }

    private static void addNewCostumer(Scanner scanner, ArrayList<Costumer> costumers) {
        getCostumersInstance().add(createCostumer(scanner, costumers));
    }
}
