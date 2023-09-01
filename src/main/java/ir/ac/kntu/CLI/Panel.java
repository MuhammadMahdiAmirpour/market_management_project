package ir.ac.kntu.CLI;

import ir.ac.kntu.enums.CommodityType;
import ir.ac.kntu.enums.ReceiptType;
import ir.ac.kntu.enums.StarterMenuOptions;
import ir.ac.kntu.logic.TimeHandler;
import ir.ac.kntu.objects.*;
import ir.ac.kntu.threads.AutoRunner;

import java.util.*;

import static ir.ac.kntu.CLI.AdminPanel.runAdminPanel;
import static ir.ac.kntu.CLI.CostumerPanel.runCostumerPanel;
import static ir.ac.kntu.CLI.CourierPanel.runCourierPanel;
import static ir.ac.kntu.enums.ReceiptType.BUY;
import static ir.ac.kntu.enums.ReceiptType.SELL;
import static ir.ac.kntu.logic.NodeAdder.addNewNode;
import static ir.ac.kntu.logic.Verifier.*;

public class Panel {

    private static final HashMap<CommodityType, Integer> tpMap = new HashMap<>();
    private static ArrayList<Costumer> costumers = getCostumersInstance();
    private final Admin admin = Admin.getInstance();
    public static Panel instance;
    private static ArrayList<Courier> couriers = getCouriersInstance();
    private static ArrayList<SuperMarket> superMarkets = getSuperMarketsInstance();
    public static final FakeDate fakeDate = new FakeDate();

    public static boolean isThreadMainAlive;

    public static ArrayList<Costumer> getCostumersInstance() {
        if (costumers == null) {
            costumers = new ArrayList<>();
        }
        return costumers;
    }

    public static ArrayList<Courier> getCouriersInstance() {
        if (couriers == null) {
            couriers = new ArrayList<>();
        }
        return couriers;
    }

    public static ArrayList<SuperMarket> getSuperMarketsInstance() {
        if (superMarkets == null) {
            superMarkets = new ArrayList<>();
        }
        return superMarkets;
    }

    public static Panel getInstance() {
        if (instance == null) {
            instance = new Panel();
        }
        return instance;
    }

    public void runPanel(Scanner scanner) throws InterruptedException {
        isThreadMainAlive = Thread.currentThread().isAlive();
        TimeHandler timeHandler = new TimeHandler();
        timeHandler.start();
        addCostumer();
        addCourier();
        addSuperMarket();
        String input;
        do {
            System.out.println(fakeDate);
            System.out.println(Thread.activeCount());
            System.out.println("""
                    get in panel as:
                    ADMIN
                    COSTUMER
                    COURIER
                    NEW
                    SAW: seat and watch
                    exit
                    """);
            input = scanner.nextLine();
            if (!StarterMenuOptions.contains(input)) {
                System.out.println("wrong input ! try again : \n");
                clearConsole();
                continue;
            }
            if (input.equalsIgnoreCase("exit")) {
                continue;
            }
            StarterMenuOptions starterMenuOptions = StarterMenuOptions.valueOf(input.toUpperCase());
            if (!StarterMenuOptions.contains(input)) {
                continue;
            }
            switch (starterMenuOptions) {
                case COSTUMER -> {
                    Costumer costumer = verifyCostumer(scanner);
                    if (costumer != null) {
                        runCostumerPanel(scanner, costumer);
                    } else {
                        System.out.println("costumer not found !");
                    }
                }
                case ADMIN -> {
                    if (isVerifiedAdmin(scanner)) {
                        runAdminPanel(scanner, admin);
                    } else {
                        System.out.println("admin not found !");
                    }
                }
                case COURIER -> {
                    Courier courier = verifyCourier(scanner);
                    if (courier != null) {
                        runCourierPanel(scanner, courier);
                    } else {
                        System.out.println("courier not found !");
                    }
                }
                case NEW -> addNewNode(scanner);
                case SAW -> autoRun();
            }
        } while (!input.equalsIgnoreCase("exit"));
        isThreadMainAlive = false;
        System.exit(1);
    }

    private void autoRun() {
        AutoRunner autoRunner = new AutoRunner(getCostumersInstance());
        autoRunner.setDaemon(true);
        autoRunner.start();
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static ReceiptType getReceiptType(Scanner scanner) {
        String receiptTypeInput;
        System.out.println("""
                which receipt?
                _buy
                _sell
                type the number !
                """);
        do {
            receiptTypeInput = scanner.nextLine();
            if (!(receiptTypeInput.toLowerCase(Locale.ROOT).equals("sell") ||
                    receiptTypeInput.toLowerCase(Locale.ROOT).equals("buy"))) {
                System.out.println("""
                        wrong input!
                        try again!
                        """);
            }
        } while (!(receiptTypeInput.toLowerCase(Locale.ROOT).equals("sell") ||
                receiptTypeInput.toLowerCase(Locale.ROOT).equals("buy")));
        ReceiptType receiptType = null;
        if (receiptTypeInput.equals("buy")) {
            receiptType = BUY;
        }
        if (receiptTypeInput.equals("sell")) {
            receiptType = SELL;
        }
        return receiptType;
    }

    public static ArrayList<? extends Person> getPeople() {
        ArrayList<Person> result = new ArrayList<>();
        result.addAll(getCostumersInstance());
        result.addAll(getCostumersInstance());
        return result;
    }

    public static ArrayList<Node> getNodes() {
        ArrayList<Node> result = new ArrayList<>();
        result.addAll(getSuperMarketsInstance());
        result.addAll(getCostumersInstance());
        result.addAll(getCostumersInstance());
        return result;
    }

    public static void cleanSuperMarkets() {
        getSuperMarketsInstance().forEach(SuperMarket::removeExpiredCommodities);
    }

    public static void goToOneDayLater() throws InterruptedException {
        for (int i = 0; i < 24; i++) {
            goToOneHourLater();
        }
    }

    private static void goToOncSecondLater() throws InterruptedException {
        Thread.sleep(1);
        fakeDate.addToSeconds(1);
    }

    public static void goToOneMinuteLater() throws InterruptedException {
        for (int i = 0; i < 60; i++) {
            goToOncSecondLater();
        }
    }

    public static void goToOneWeekLater() throws InterruptedException {
        for (int i = 0; i < 7; i++) {
            goToOneDayLater();
        }
    }

    public static void goToOneMonthLater() throws InterruptedException {
        for (int i = 0; i < 28; i++) {
            goToOneDayLater();
        }
    }

    public static void goToOneHourLater() throws InterruptedException {
        for (int i = 0; i < 60; i++) {
            goToOneMinuteLater();
        }
    }

    public Panel() {
        tpMap.put(CommodityType.diary, 20);
        tpMap.put(CommodityType.drink, 25);
        tpMap.put(CommodityType.protein, 50);
        tpMap.put(CommodityType.junkFood, 40);
        tpMap.put(CommodityType.sanitary, 45);
    }

    public static HashMap<CommodityType, Integer> getTpMap() {
        return tpMap;
    }

    public void addSuperMarket() {
        superMarkets.add(new SuperMarket("supermarket1", 0, "America-newYork-Brooklyn",
                "supermarket1username", "12345678", new Location(45, 54),
                0, 0, new Storage()));
        superMarkets.add(new SuperMarket("supermarket2", 0, "Iran-Tehran-tajrish",
                "supermarket2username", "87654321", new Location(87, 78),
                0, 0, new Storage()));
        superMarkets.add(new SuperMarket("supermarket3", 0, "Canada-Ontario-toronto",
                "supermarket3username", "67896789", new Location(64, 46),
                0, 0, new Storage()));
        superMarkets.add(new SuperMarket("supermarket4", 0, "Italy-Lazio-Rome",
                "supermarket4username", "98769876", new Location(87, 78),
                0, 0, new Storage()));
        superMarkets.add(new SuperMarket("supermarket5", 0, "Germany-Bavaria-munich",
                "supermarket5username", "76544567", new Location(98, 89),
                0, 0, new Storage()));
    }

    public void addCostumer() {
        costumers.add(new Costumer(0, "kobra", "yazdah", "858585858",
                "kobra11", "33333333", "09858585858", new Location(3, 4),
                new ArrayList<>()));
        costumers.add(new Costumer(0, "fati", "komando", "696969696",
                "fatiComando", "22222222", "09696969696", new Location(6, 8),
                new ArrayList<>()));
        costumers.add(new Costumer(0, "asghar", "asghari", "000000000",
                "asghari", "11111111", "00000000000", new Location(43, 34),
                new ArrayList<>()));
        costumers.add(new Costumer(0, "maede", "kafshi", "111111111",
                "maedeKafshi", "44444444", "44444444444", new Location(12, 21),
                new ArrayList<>()));
        costumers.add(new Costumer(0, "reza", "moradi", "212121212",
                "moradi", "55555555", "55555555555", new Location(23, 28),
                new ArrayList<>()));
    }

    public void addCourier() {
        couriers.add(new Courier(0, "mammad", "mammadi", "999999999",
                "mammadi", "99999999", "99999999999", new ArrayList<>()));
        couriers.add(new Courier(0, "mammali", "mammalili", "777777777",
                "malmal", "77777777", "77777777777", new ArrayList<>()));
        couriers.add(new Courier(0, "khadjah", "gherghi", "666666666",
                "gherghi", "66666666", "66666666666", new ArrayList<>()));
        couriers.add(new Courier(0, "hale", "motorBatmani", "555555555",
                "batman", "55555554", "55555555555", new ArrayList<>()));
        couriers.add(new Courier(0, "aghdas", "samurai", "878787878",
                "asghdasSamoor", "09090909", "09090909099", new ArrayList<>()));
    }

    public static Courier getRandomCourier() {
        Random random = new Random();
        return getCouriersInstance().get(random.nextInt(5));
    }

    public static SuperMarket getRandomSuperMarket() {
        Random random = new Random();
        return getSuperMarketsInstance().get(random.nextInt(5));
    }
}
