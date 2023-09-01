package ir.ac.kntu.logic;

import ir.ac.kntu.enums.PersonInfoEditOptions;
import ir.ac.kntu.objects.Courier;

import java.util.Scanner;

import static ir.ac.kntu.logic.InfoEditor.*;

public class CourierOperator {
    public static void editCourierInfo(Scanner scanner, Courier courier) {
        PersonInfoEditOptions choice = getTheCourierOptionChoice(scanner);
        switch (choice) {
            case FN -> editFirstName(scanner, courier);
            case LN -> editLastName(scanner, courier);
            case UN -> editUsername(scanner, courier);
            case PHONE -> editPhoneNumber(scanner, courier);
            case ID -> editNationalID(scanner, courier);
            case PASSWORD -> editPassword(scanner, courier);
            case WB -> editCourierWalletBalance(scanner, courier);
        }
    }

    private static void editCourierWalletBalance(Scanner scanner, Courier courier) {
        String input;
        do {
            System.out.println("enter the amount of money you want to withdraw...");
            input = scanner.nextLine();
            if (!input.chars().allMatch(Character::isDigit)) {
                System.out.println("""
                        wrong input !
                        try again ...
                        """);
                continue;
            }
            if (input.chars().allMatch(Character::isDigit)) {
                if (!(0 < Double.parseDouble(input) && Double.parseDouble(input) < courier.getWalletBalance())) {
                    System.out.println("""
                            wrong input !
                            try again ...
                            """);
                }
            }
        } while (!input.chars().allMatch(Character::isDigit) ||
                (input.chars().allMatch(Character::isDigit) &&
                        !(0 < Double.parseDouble(input) &&
                                Double.parseDouble(input) < courier.getWalletBalance())));
        courier.setWalletBalance((int) (courier.getWalletBalance() - Double.parseDouble(input)));
    }


    public static PersonInfoEditOptions getTheCourierOptionChoice(Scanner scanner) {
        String input;
        PersonInfoEditOptions result = null;
        do {
            System.out.println("choose what do you want to do from the list below: ");
            PersonInfoEditOptions.showOptions();
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
}
