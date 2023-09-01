package ir.ac.kntu;

import ir.ac.kntu.CLI.Panel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Panel panel = Panel.getInstance();
        panel.runPanel(new Scanner(System.in));
    }
}
