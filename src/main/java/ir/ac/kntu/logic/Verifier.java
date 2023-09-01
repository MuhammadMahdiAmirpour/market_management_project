package ir.ac.kntu.logic;

import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.objects.Admin;
import ir.ac.kntu.objects.Costumer;
import ir.ac.kntu.objects.Courier;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verifier {
    public static Courier verifyCourier(Scanner scanner) {
        AtomicReference<Courier> result = new AtomicReference<>(null);
        String username = getUsernameToVerify(scanner);
        String password = getPasswordToVerify(scanner);
        Panel.getCouriersInstance().forEach(courier -> {
            if (password.equals(courier.getPassword()) && username.equals(courier.getUsername())) {
                result.set(courier);
            }
        });
        return result.get();
    }

    public static String getPasswordToVerify(Scanner scanner) {
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
            if (!matcher.matches()) {
                System.out.println("""
                        wrong input !
                        try again...
                        """);
            }
        } while (!matcher.matches());
        return password;
    }

    public static String getUsernameToVerify(Scanner scanner) {
        String userName;
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        Matcher nameMatcher;
        do {
            System.out.println("username?");
            userName = scanner.nextLine();
            nameMatcher = usernamePattern.matcher(userName);
            if (!nameMatcher.matches()) {
                System.out.println("""
                        wrong input !
                        try again...
                        """);
            }
        } while (!nameMatcher.matches());
        return userName;
    }

    public static Costumer verifyCostumer(Scanner scanner) {
        AtomicReference<Costumer> result = new AtomicReference<>(null);
        String username = getUsernameToVerify(scanner);
        String password = getPasswordToVerify(scanner);
        Panel.getCostumersInstance().forEach(costumer -> {
            System.out.println(costumer.toString());
            if (password.equals(costumer.getPassword()) && username.equals(costumer.getUsername())) {
                result.set(costumer);
            }
        });
        return result.get();
    }

    public static boolean isVerifiedAdmin(Scanner scanner) {
        String username = getUsernameToVerify(scanner);
        String password = getPasswordToVerify(scanner);
        return Admin.getInstance().getUserName().equals(username) &&
                Admin.getInstance().getPassword().equals(password);
    }
}
