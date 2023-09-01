package ir.ac.kntu.objects;

import ir.ac.kntu.enums.OrderState;
import ir.ac.kntu.logic.OrderHandler;

import java.util.ArrayList;
import java.util.Comparator;

public class Courier extends Person {

    private boolean isBusy = false;
    private int thisMonthOutCome;
    private Order followingOrder;
    private final ArrayList<Order> deliveredOrders = new ArrayList<>();


    public Courier(int walletBalance, String firstName, String lastName, String nationalID, String userName,
                   String password, String phoneNumber, ArrayList<Order> deliveredOrders) {
        super(walletBalance, firstName, lastName, nationalID, userName, password, phoneNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void deliverOrder(Costumer costumer, Order order, SuperMarket superMarket, Location costumerLocation) {
        if (costumer.getWalletBalance() < order.getTotalPrice()) {
            System.out.println("costumer does not have enough money");
            return;
        }
        setFollowingOrder(order);
        this.setBusy(true);
        order.setOrderState(OrderState.DBC);
        OrderHandler orderHandler = new OrderHandler(order, superMarket, costumerLocation, this, costumer);
        orderHandler.handle(order);
    }

    public ArrayList<Order> sortOrdersByPrice() {
        getOrderList().sort(Comparator.comparing(Order::getTotalPrice));
        return getOrderList();
    }

    public int getThisMonthOutCome() {
        return thisMonthOutCome;
    }

    public void setThisMonthOutCome(int thisMonthOutCome) {
        this.thisMonthOutCome = thisMonthOutCome;
    }

    public Order getFollowingOrder() {
        return followingOrder;
    }

    public void setFollowingOrder(Order followingOrder) {
        this.followingOrder = followingOrder;
    }

    public ArrayList<Order> getDeliveredOrders() {
        return deliveredOrders;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void addToDeliveredOrders(Order order) {
        getOrderList().add(order);
    }

    public void addToThisMonthOutCome(int money) {
        this.thisMonthOutCome += money;
    }
}
