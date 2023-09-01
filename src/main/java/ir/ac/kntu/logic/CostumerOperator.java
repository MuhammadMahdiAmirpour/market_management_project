package ir.ac.kntu.logic;

import ir.ac.kntu.CLI.AdminPanel;
import ir.ac.kntu.enums.PersonInfoEditOptions;
import ir.ac.kntu.enums.WalletBalance;
import ir.ac.kntu.objects.Costumer;
import ir.ac.kntu.objects.Order;
import ir.ac.kntu.objects.SuperMarket;

import java.util.Objects;
import java.util.Scanner;

import static ir.ac.kntu.logic.InfoEditor.*;

public class CostumerOperator {

    public static void editCostumerInfo(Scanner scanner, Costumer costumer) {
        PersonInfoEditOptions choice = getTheCostumerEditionChoice(scanner);
        switch (choice) {
            case FN -> editFirstName(scanner, costumer);
            case LN -> editLastName(scanner, costumer);
            case UN -> editUsername(scanner, costumer);
            case PASSWORD -> editPassword(scanner, costumer);
            case LOCATION -> editLocation(scanner, costumer);
            case ID -> editNationalID(scanner, costumer);
            case PHONE -> editPhoneNumber(scanner, costumer);
            case WB -> editWalletBalance(scanner, costumer);
        }
    }


    public static PersonInfoEditOptions getTheCostumerEditionChoice(Scanner scanner) {
        String input;
        PersonInfoEditOptions result = null;
        do {
            showCostumerInfoEditOptions();
            input = scanner.nextLine();
            if (!PersonInfoEditOptions.contains(input)) {
                System.out.println("""
                        wrong input
                        please try again later
                        """);
                continue;
            }
            result = PersonInfoEditOptions.match(input);
        } while (!PersonInfoEditOptions.contains(input));
        return result;
    }

    public static void showCostumerInfoEditOptions() {
        System.out.println("""
                what do you want to edit:
                FN: first name
                LN: last name
                UN: username
                password
                location
                ID: national ID
                phone
                WB: wallet balance
                """);
    }

    public static void editWalletBalance(Scanner scanner, Costumer costumer) {
        String input;
        WalletBalance choice = null;
        do {
            System.out.println("""
                    deposit
                    withdraw
                    """);
            input = scanner.nextLine();
            if (!WalletBalance.contains(input)) {
                System.out.println("""
                        wrong input !
                        try again ...
                        """);
                continue;
            }
            choice = WalletBalance.match(input);
        } while (!WalletBalance.contains(input) && choice == null);
        switch (Objects.requireNonNull(choice)) {
            case DEPOSIT -> {
                depositToCostumerAccount(scanner, costumer);
            }
            case WITHDRAW -> {
                withdrawFromCostumerAccount(scanner, costumer);
            }
        }
    }

    private static void withdrawFromCostumerAccount(Scanner scanner, Costumer costumer) {
        String input;
        double result = 0L;
        do {
            System.out.println("""
                    how much do you want to withdraw?
                    """);
            input = scanner.nextLine();
            if (!input.chars().allMatch(Character::isDigit) ||
                    Double.parseDouble(input) > costumer.getWalletBalance()) {
                System.out.println("""
                        you do not have this much amount of money !
                        all you can take is :
                        """
                        + costumer.getWalletBalance() +
                        """
                                please try again !
                                """);
                continue;
            }
            result = Double.parseDouble(input);
        } while (Double.parseDouble(input) > costumer.getWalletBalance() ||
                !input.chars().allMatch(Character::isDigit));
        costumer.setWalletBalance((int) (costumer.getWalletBalance() - result));
    }

    private static void depositToCostumerAccount(Scanner scanner, Costumer costumer) {
        String input;
        double result = 0L;
        do {
            System.out.println("""
                    how much do you want to deposit?
                    """);
            input = scanner.nextLine();
            if (input.chars().allMatch((Character::isDigit))) {
                System.out.println("""
                        wrong input !
                        try again ...
                        """);
                continue;
            }
            result = Double.parseDouble(input);
        } while (!input.chars().allMatch(Character::isDigit));
        costumer.setWalletBalance((int) (costumer.getWalletBalance() + result));
    }

    public static void showCostumerOrderHistory(Costumer costumer) {
        costumer.getOrderList().forEach(order -> {
            System.out.println(order.toString());
        });
    }

    public static void costumerBuysGoods(Scanner scanner, Costumer costumer) {
        SuperMarket target = AdminPanel.chooseSupermarket(scanner);
        ReceiptContainer receiptContainer = new ReceiptContainer();
        receiptContainer.setReceipt(JsonGenerator.generateBuyRecipe());
        Order order = new Order();
        receiptContainer.process(order);
        costumer.getOrderList().add(order);
        target.setTotalSalesAmount(target.getTotalSalesAmount() + 1);
        target.setBalance(target.getBalance() + order.getTotalPrice());
    }
}
