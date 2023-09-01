package ir.ac.kntu.logic;

import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.objects.*;

import java.util.Scanner;

import static ir.ac.kntu.logic.ObjectBuilder.getLocationData;

public class InfoEditor {
    public static <T extends Person> void editPhoneNumber(Scanner scanner, T person) {
        String input = ObjectBuilder.getThePhoneNumber(scanner, Panel.getPeople());
        person.setPhoneNumber(input);
    }

    public static <T extends Person> void editNationalID(Scanner scanner, T person) {
        String input = ObjectBuilder.getTheNationalID(scanner, Panel.getPeople());
        person.setNationalID(input);
    }

    public static <T extends Node> void editLocation(Scanner scanner, T node) {
        if (node instanceof Costumer) {
            Location location = getLocationData(scanner);
            ((Costumer) node).setLocation(location);
        } else if (node instanceof SuperMarket) {
            Location location = getLocationData(scanner);
            ((SuperMarket) node).setLocation(location);
        } else if (node instanceof Courier) {
            System.out.println("""
                    Couriers can not change the location because they do not have location attribute !
                    """);
        }
    }

    public static <T extends Person> void editPassword(Scanner scanner, T person) {
        String input = ObjectBuilder.getThePassword(scanner, Panel.getNodes());
        person.setPassword(input);
    }

    public static <T extends Person> void editUsername(Scanner scanner, T person) {
        String input = ObjectBuilder.getTheUserName(scanner, Panel.getNodes());
        person.setUsername(input);
    }

    public static <T extends Person> void editLastName(Scanner scanner, T person) {
        String input = ObjectBuilder.getTheLastName(scanner);
        person.setLastName(input);
    }

    public static <T extends Person> void editFirstName(Scanner scanner, T person) {
        String input = ObjectBuilder.getTheFirstName(scanner);
        person.setFirstName(input);
    }
}
