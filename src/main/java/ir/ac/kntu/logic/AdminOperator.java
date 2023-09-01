package ir.ac.kntu.logic;

import ir.ac.kntu.CLI.AdminPanel;
import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.enums.GetListOptions;
import ir.ac.kntu.enums.OrderState;
import ir.ac.kntu.enums.TimeTravelOptions;
import ir.ac.kntu.objects.Costumer;
import ir.ac.kntu.objects.Order;
import ir.ac.kntu.objects.SuperMarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class AdminOperator {
    public static boolean containsSuperMarket(String supermarketName) {
        AtomicBoolean result = new AtomicBoolean(false);
        Panel.getSuperMarketsInstance().forEach(superMarket -> {
            if (superMarket.getName().equals(supermarketName)) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static void buySupplyForTheSuperMarket(SuperMarket superMarket) {
        ReceiptContainer receiptContainer = new ReceiptContainer();
        receiptContainer.setReceipt(JsonGenerator.generateBuyRecipe());
        receiptContainer.process(superMarket.getStore());
        superMarket.getStore().addToStorage(receiptContainer.getCommodities());
    }

    public static void showTotalSupply() {
        Panel.getSuperMarketsInstance().forEach(superMarket ->
                superMarket.getStore().getStorage().forEach(System.out::println));
    }

    public static void showStoreSupply(SuperMarket superMarket) {
        superMarket.getStore().getStorage().forEach(System.out::println);
    }

    public static void showSupplyList(SuperMarket superMarket) {
        superMarket.getStore().getStorage().forEach(commodity -> System.out.println(commodity.getName()));
    }

    public static void editOrderState(Scanner scanner) {
        String input;
        System.out.println("in which supermarket do you want to change the order state?");
        SuperMarket target = AdminPanel.chooseSupermarket(scanner);
        Order order = chooseTheOrder(scanner, target);
        order.setOrderState(getTheInputOrderState(scanner, order));
    }

    private static OrderState getTheInputOrderState(Scanner scanner, Order order) {
        String input;
        OrderState result = null;
        do {
            System.out.println("current order state is : " + order.getOrderState());
            System.out.println("choose the order state that you want : from the list below");
            Arrays.stream(OrderState.values()).forEach(System.out::println);
            input = scanner.nextLine();
            if (!OrderState.contains(input)) {
                System.out.println("""
                        wrong input
                        please try ...
                        """);
                continue;
            }
            result = OrderState.match(input);
        } while (!OrderState.contains(input));
        return result;
    }

    public static Order chooseTheOrder(Scanner scanner, SuperMarket superMarket) {
        String input;
        Order result = null;
        ArrayList<Order> orders = superMarket.getOrderList();
        do {
            System.out.println("choose the order from the list below: ");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println("number " + i + "_" + orders.get(i));
            }
            System.out.println("""
                    note :
                    only enter numbers !!!
                    """);
            input = scanner.nextLine();
            if (!input.chars().allMatch(Character::isDigit)) {
                System.out.println("""
                        wrong input!
                        please try again ...
                        """);
                continue;
            }
            if (input.chars().allMatch(Character::isDigit)) {
                if (!(Integer.parseInt(input) > 0 && Integer.parseInt(input) < orders.size())) {
                    System.out.println("""
                            wrong input!
                            please try again ...
                            """);
                }
            }
            result = orders.get(Integer.parseInt(input));
        } while (!input.chars().allMatch(Character::isDigit) || (input.chars().allMatch(Character::isDigit) &&
                !(Integer.parseInt(input) > 0 && Integer.parseInt(input) < orders.size())));
        return result;
    }

    public static void timeTravel(Scanner scanner) throws InterruptedException {
        String input;
        TimeTravelOptions choice = null;
        do {
            System.out.println("when do you want to travel to?");
            TimeTravelOptions.showOptions();
            input = scanner.nextLine();
            if (!TimeTravelOptions.contains(input)) {
                System.out.println("""
                        wrong input
                        try again
                        """);
                continue;
            }
            choice = TimeTravelOptions.match(input);
        } while (!TimeTravelOptions.contains(input));
        switch (Objects.requireNonNull(choice)) {
            case MONTH -> Panel.goToOneMonthLater();
            case DAY -> Panel.goToOneDayLater();
            case MINUET -> Panel.goToOneMinuteLater();
            case HOUR -> Panel.goToOneHourLater();
            case WEEK -> Panel.goToOneWeekLater();
        }
    }

    public static void seeCostumersOrder(Scanner scanner) {
        String input;
        int choice = 0;
        ArrayList<Costumer> costumers = Panel.getCostumersInstance();
        do {
            System.out.println("choose your costumer");
            for (int i = 0; i < costumers.size(); i++) {
                System.out.println(i + " - " + costumers.get(i));
            }
            input = scanner.nextLine();
            if (!input.chars().allMatch(Character::isDigit)) {
                System.out.println("""
                        wrong input
                        try again ...
                        """);
                continue;
            }
            if (input.chars().allMatch(Character::isDigit)) {
                if (!(0 <= Integer.parseInt(input) && Integer.parseInt(input) < costumers.size())) {
                    System.out.println("""
                            wrong input
                            try again ...
                            """);
                    continue;
                }
            }
            choice = Integer.parseInt(input);
        } while (!input.chars().allMatch(Character::isDigit) ||
                (input.chars().allMatch(Character::isDigit) &&
                        !(0 <= Integer.parseInt(input) && Integer.parseInt(input) < costumers.size())));
        Costumer target = costumers.get(choice);
        String result = "fist name : " + target.getFirstName() + "\n" +
                "last name : " + target.getLastName() + "\n" +
                "username : " + target.getUsername() + "\n" +
                "password : " + target.getPassword() + "\n" +
                "location : " + target.getLocation().toString() + "\n" +
                "national ID : " + target.getNationalID() + "\n" +
                "phone number : " + target.getPhoneNumber() + "\n" +
                "wallet balance : " + target.getWalletBalance() + "\n";
        System.out.println("your costumer info: ");
        System.out.println(result);
        System.out.println("orders : ");
        target.getOrderHistory();
    }

    public static void getList(Scanner scanner) {
        String input;
        GetListOptions choice = null;
        do {
            System.out.println("""
                    costumers
                    couriers
                    orders
                    commodities
                    """);
            input = scanner.nextLine();
            if (!GetListOptions.contains(input)) {
                System.out.println("""
                        wrong input
                        try again later
                        """);
                continue;
            }
            choice = GetListOptions.match(input);
        } while (!GetListOptions.contains(input));
        switch (Objects.requireNonNull(choice)) {
            case COSTUMERS -> Panel.getCostumersInstance().forEach(System.out::println);
            case COURIERS -> Panel.getCouriersInstance().forEach(System.out::println);
            case ORDERS -> Panel.getSuperMarketsInstance().forEach(SuperMarket::getOrderList);
            case COMMODITIES -> Panel.getSuperMarketsInstance().forEach(superMarket -> {
                superMarket.getStore().getStorage().forEach(System.out::println);
            });
        }
    }
}
