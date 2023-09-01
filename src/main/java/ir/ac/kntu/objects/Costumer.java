package ir.ac.kntu.objects;

import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.enums.OrderState;
import ir.ac.kntu.logic.JsonGenerator;
import ir.ac.kntu.logic.ReceiptContainer;
import ir.ac.kntu.reporter.Reporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Costumer extends Person {
    private ArrayList<Order> orderList = new ArrayList<>();
    private Location location;

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Costumer(int walletBalance, String firstName, String lastName, String nationalID, String userName,
                    String password, String phoneNumber, Location location, ArrayList<Order> orderList) {
        super(walletBalance, firstName, lastName, nationalID, userName, password, phoneNumber);
        setLocation(location);
        setOrderList(orderList);
    }

    public void getOrderHistory() {
        for (Order order : getOrderList()) {
            System.out.println(order);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void addOrder(Order order) {
        getOrderList().add(order);
    }

    public ArrayList<Order> sortOrderListByPrice() {
        getOrderList().sort(Comparator.comparing(Order::getTotalPrice));
        return getOrderList();
    }

    public void spend(int amount) {
        setWalletBalance(getWalletBalance() - amount);
    }

    public void setRandomOrder() throws IOException {
        Reporter reporter = new Reporter();
        String report = "costumer: " + this + "Has just set an order\n";
        reporter.report(report);
        ReceiptContainer receiptContainer = new ReceiptContainer();
        receiptContainer.setReceipt(JsonGenerator.generateBuyRecipe());
        Order order = new Order();
        order.setOrderState(OrderState.PROCESSING);
        receiptContainer.process(order);
        SuperMarket superMarket = Panel.getRandomSuperMarket();
        do {
            superMarket.buy();
        } while (superMarket.hasEnough(order));
        Courier courier;
        do {
            courier = Panel.getRandomCourier();
        } while (courier.isBusy());
        courier.deliverOrder(this, order, superMarket, getLocation());
    }
}
