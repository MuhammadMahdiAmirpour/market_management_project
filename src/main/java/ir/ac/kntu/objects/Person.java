package ir.ac.kntu.objects;

import ir.ac.kntu.enums.ReceiptType;
import ir.ac.kntu.logic.JsonGenerator;
import ir.ac.kntu.logic.ReceiptContainer;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static ir.ac.kntu.CLI.Panel.clearConsole;
import static ir.ac.kntu.CLI.Panel.getReceiptType;

public class Person extends Node {
    private ArrayList<Order> orderList = new ArrayList<>();
    private int walletBalance;
    private String firstName;
    private String lastName;
    private String nationalID;
    private String phoneNumber;

    private ReceiptContainer receiptContainer = new ReceiptContainer();

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public ReceiptContainer getReceiptContainer() {
        return receiptContainer;
    }

    public void setReceiptContainer(ReceiptContainer receiptContainer) {
        this.receiptContainer = receiptContainer;
    }

    public Person(int walletBalance, String firstName, String lastName, String nationalID,
                  String userName, String password, String phoneNumber) {
        setUsername(userName);
        setPassword(password);
        setWalletBalance(walletBalance);
        setFirstName(firstName);
        setLastName(lastName);
        setNationalID(nationalID);
        setPhoneNumber(phoneNumber);
    }

    @Override
    public String toString() {
        return "Person: { " +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", userName='" + getUsername() + '\'' +
                ", nationalID='" + getNationalID() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\''
                + '}';
    }

    public void newOrder(Scanner scanner) {
        clearConsole();
        String receipt = JsonGenerator.generateBuyRecipe();
        ReceiptType receiptType = getReceiptType(scanner);
        receiptContainer.setReceipt(receipt);
        receiptContainer.setReceipt_type(receiptType);
        receiptContainer.process(orderList.get(orderList.toArray().length));
    }


    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return getWalletBalance() == person.getWalletBalance() && getFirstName().equals(person.getFirstName()) &&
                getLastName().equals(person.getLastName()) && getNationalID().equals(person.getNationalID()) &&
                getPassword().equals(person.getPassword()) && getPhoneNumber().equals(person.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWalletBalance(), getFirstName(), getLastName(), getNationalID(), getPassword(),
                getPhoneNumber());
    }

    public void addToWalletBalance(int money) {
        this.walletBalance += money;
    }
}
