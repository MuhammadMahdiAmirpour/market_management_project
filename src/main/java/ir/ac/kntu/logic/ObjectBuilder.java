package ir.ac.kntu.logic;

import ir.ac.kntu.objects.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectBuilder {

    public static String getTheNationalID(Scanner scanner, ArrayList<? extends Person> people) {
        String nationalID;
        Pattern patternID = Pattern.compile("^[0-9]{9}$");
        Matcher matcherID;
        do {
            System.out.println("""
                    national ID?
                    length = 9
                    """);
            nationalID = scanner.nextLine();
            matcherID = patternID.matcher(nationalID);
            if (nationalIdAlreadyExists(nationalID, people)) {
                System.out.println("""
                        sorry
                        this national ID already exists
                        please try with another one
                        """);
                continue;
            }
            if (!matcherID.matches()) {
                System.out.println("""
                        wrong input!
                        try again...
                        """);
            }
        } while (!(matcherID.matches() && !nationalIdAlreadyExists(nationalID, people)));
        return nationalID;
    }

    public static boolean nationalIdAlreadyExists(String nationalId, ArrayList<? extends Person> people) {
        AtomicBoolean result = new AtomicBoolean(false);
        people.forEach(person -> {
            if (person.getNationalID().equals(nationalId)) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static Courier createCourier(Scanner scanner, ArrayList<Courier> couriers) {
        return new Courier(0, getTheFirstName(scanner), getTheLastName(scanner),
                getTheNationalID(scanner, couriers), getTheUserName(scanner, couriers),
                getThePassword(scanner, couriers), getThePhoneNumber(scanner, couriers), new ArrayList<>());
    }

    public static SuperMarket createSuperMarket(Scanner scanner, ArrayList<SuperMarket> superMarkets) {
        return new SuperMarket(getTheSuperMarketName(scanner, superMarkets), 0,
                getTheAddress(scanner), getTheUserName(scanner, superMarkets), getThePassword(scanner, superMarkets),
                getLocationData(scanner), 0, 0, new Storage());
    }

    private static String getTheAddress(Scanner scanner) {
        String address;
        Pattern addressPattern = Pattern.compile("([a-zA-Z]{2,20}-)+" + "([a-zA-Z]{2,20})");
        Matcher addressMatcher;
        do {
            System.out.println("""
                    enter the address
                    example : ZZZZ-AAAA-hhhhh-asdfADF
                    """);
            address = scanner.nextLine();
            addressMatcher = addressPattern.matcher(address);
            if (!addressMatcher.matches()) {
                System.out.println("""
                        wrong input !
                        try again...
                        """);
            }
        } while (!addressMatcher.matches());
        return address;
    }

    public static Costumer createCostumer(Scanner scanner, ArrayList<Costumer> costumers) {
        return new Costumer(0, getTheFirstName(scanner), getTheLastName(scanner),
                getTheNationalID(scanner, costumers), getTheUserName(scanner, costumers),
                getThePassword(scanner, costumers), getThePhoneNumber(scanner, costumers), getLocationData(scanner),
                new ArrayList<>());
    }

    public static String getThePhoneNumber(Scanner scanner, ArrayList<? extends Person> people) {
        String phoneNumber;
        Pattern patternPhone = Pattern.compile("^[0-9]{11}$");
        Matcher matcherPhone;
        do {
            System.out.println("""
                    phone number?
                    length = 11
                    """);
            phoneNumber = scanner.nextLine();
            matcherPhone = patternPhone.matcher(phoneNumber);
            if (phoneNumberAlreadyExists(phoneNumber, people)) {
                System.out.println("""
                        sorry
                        this phone number already exists
                        please try with another one
                        """);
            }
            if (!matcherPhone.matches()) {
                System.out.println("""
                        wrong input!
                        try again...
                        """);
            }
        } while (!(matcherPhone.matches() && !phoneNumberAlreadyExists(phoneNumber, people)));
        return phoneNumber;
    }

    private static boolean phoneNumberAlreadyExists(String phoneNumber, ArrayList<? extends Person> people) {
        AtomicBoolean result = new AtomicBoolean(false);
        people.forEach(person -> {
            if (person.getPhoneNumber().equals(phoneNumber)) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static String getThePassword(Scanner scanner, ArrayList<? extends Node> nodes) {
        String password;
        Pattern passwordPattern = Pattern.compile("^[0-9]{8,20}$");
        Matcher matcher;
        do {
            System.out.println("""
                    password?
                    at least 8 and maximum 20 characters all digits
                    """);
            password = scanner.nextLine();
            matcher = passwordPattern.matcher(password);
            if (alreadyContainsPassword(password, nodes)) {
                System.out.println("""
                        sorry
                        this password already exists
                        please try another one
                        """);
                continue;
            }
            if (!matcher.matches()) {
                System.out.println("""
                        wrong input !
                        try again...
                        """);
            }
        } while (!(matcher.matches() && ! alreadyContainsPassword(password, nodes)));
        return password;
    }

    public static boolean alreadyContainsPassword(String password, ArrayList<? extends Node> nodes) {
        AtomicBoolean result = new AtomicBoolean(false);
        nodes.forEach(node -> {
            if (node.getPassword().equals(password) || Admin.getInstance().getPassword().equals(password)) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static boolean alreadyContainsUsername(String username, ArrayList<? extends Node> nodes) {
        AtomicBoolean result = new AtomicBoolean(false);
        nodes.forEach(node -> {
            if (node.getUsername().equals(username) || Admin.getInstance().getUserName().equals(username)) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static String getTheUserName(Scanner scanner, ArrayList<? extends Node> nodes) {
        String userName;
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        Matcher nameMatcher;
        do {
            System.out.println("username?");
            userName = scanner.nextLine();
            nameMatcher = usernamePattern.matcher(userName);
            if (alreadyContainsUsername(userName, nodes)) {
                System.out.println("""
                        sorry this username already exists !
                        try another one
                        """);
            }
            if (!nameMatcher.matches()) {
                System.out.println("""
                        wrong input !
                        try again...
                        """);
            }
        } while (!(nameMatcher.matches() && !alreadyContainsUsername(userName, nodes)));
        return userName;
    }

    public static String getTheFirstName(Scanner scanner) {
        String name;
        Pattern namePattern = Pattern.compile("^[a-zA-Z]{2,}$");
        Matcher nameMatcher;
        do {
            System.out.println("first name?");
            name = scanner.nextLine();
            nameMatcher = namePattern.matcher(name);
            if (!nameMatcher.matches()) {
                System.out.println("""
                        wrong input !
                        try again...
                        """);
            }
        } while (!nameMatcher.matches());
        return name;
    }

    public static String getTheLastName(Scanner scanner) {
        Pattern namePattern = Pattern.compile("^[a-zA-Z]{2,}$");
        String name;
        Matcher nameMatcher;
        do {
            System.out.println("last name?");
            name = scanner.nextLine();
            nameMatcher = namePattern.matcher(name);
            if (!nameMatcher.matches()) {
                System.out.println("""
                        wrong input !
                        try again...
                        """);
            }
        } while (!nameMatcher.matches());
        return name;
    }

    public static boolean supermarketNameAlreadyExists(String supermarketName, ArrayList<SuperMarket> superMarkets) {
        AtomicBoolean result = new AtomicBoolean(false);
        superMarkets.forEach(superMarket -> {
            if (supermarketName.equals(superMarket.getName())) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static String getTheSuperMarketName(Scanner scanner, ArrayList<SuperMarket> superMarkets) {
        String name;
        Pattern namePattern = Pattern.compile("^[a-zA-Z]{2,}$");
        Matcher nameMatcher;
        do {
            System.out.println("supermarket name?");
            name = scanner.nextLine();
            nameMatcher = namePattern.matcher(name);
            if (supermarketNameAlreadyExists(name, superMarkets)) {
                System.out.println("""
                        sorry
                        this name already exists !
                        try another one
                        """);
                continue;
            }
            if (!nameMatcher.matches()) {
                System.out.println("""
                        wrong input !
                        try again...
                        """);
            }
        } while (!(nameMatcher.matches() && !supermarketNameAlreadyExists(name, superMarkets)));
        return name;
    }


    public static Location getLocationData(Scanner scanner) {
        int xLocation;
        Location location = new Location();
        do {
            System.out.println("X location?");
            xLocation = scanner.nextInt();
            if (!(0 <= xLocation && xLocation <= 100)) {
                System.out.println("""
                        wrong input!
                        try again...
                        """);
            }
        } while (!(0 <= xLocation && xLocation <= 100));
        location.setX(xLocation);
        int yLocation;
        do {
            System.out.println("Y location?");
            yLocation = scanner.nextInt();
            if (!(0 <= yLocation && yLocation <= 100)) {
                System.out.println("""
                        wrong input!
                        try again...
                        """);
            }
        } while (!(0 <= yLocation && yLocation <= 100));
        location.setY(yLocation);
        return location;
    }
}
