package ir.ac.kntu.objects;

import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.logic.JsonGenerator;
import ir.ac.kntu.logic.ReceiptContainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class SuperMarket extends Node {

    @Override
    public String toString() {
        return "SuperMarket{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", store=" + store +
                ", name='" + name + '\'' +
                ", numberOfSales=" + numberOfSales +
                ", address='" + address + '\'' +
                ", location=" + location +
                ", takeProfit=" + takeProfit +
                ", totalSalesAmount=" + totalSalesAmount +
                '}';
    }

    private Storage store = new Storage();
    private String name;
    private int numberOfSales;
    private String address;
    private Location location;
    private int takeProfit;
    private int totalSalesAmount;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = Math.floor(balance);
    }

    private double balance = 0L;

    public SuperMarket(String name, int numberOfSales, String address, String username, String password,
                       Location location, int takeProfit, int totalSalesAmount, Storage storage) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setNumberOfSales(numberOfSales);
        setAddress(address);
        setLocation(location);
        setTakeProfit(takeProfit);
        setTotalSalesAmount(totalSalesAmount);
        setStore(storage);
    }

    public Storage getStore() {
        return store;
    }

    public void setStore(Storage store) {
        this.store = store;
    }

    public SuperMarket() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTakeProfit() {
        return takeProfit;
    }

    public void setTakeProfit(int takeProfit) {
        this.takeProfit = takeProfit;
    }

    public int getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public void setTotalSalesAmount(int totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuperMarket that)) return false;
        return getNumberOfSales() == that.getNumberOfSales() && getTakeProfit() == that.getTakeProfit() &&
                getTotalSalesAmount() == that.getTotalSalesAmount() && getName().equals(that.getName()) &&
                getUsername().equals(that.getUsername()) && getPassword().equals(that.getPassword()) &&
                getAddress().equals(that.getAddress()) && getLocation().equals(that.getLocation()) &&
                getStore().equals(that.getStore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumberOfSales(), getUsername(), getPassword(), getAddress(), getLocation(),
                getTakeProfit(), getTotalSalesAmount(), getStore().hashCode());
    }

    public void removeExpiredCommodities() {
        getStore().getStorage().forEach(commodity -> {
            if (commodity.getExpirationDate().compareTo(LocalDate.now()) > 0) {
                getStore().removeFromStorage(commodity);
                setBalance(getBalance() +
                        ((commodity.getQuantity() * Double.parseDouble(commodity.getPrice())) * 0.8));
            }
        });
    }

    public void addToNumberOfSales() {
        this.numberOfSales++;
    }

    public void addToTotalSalesAmount(int amount) {
        this.totalSalesAmount += amount;
    }

    public ArrayList<Order> getOrderList() {
        ArrayList<Order> result = new ArrayList<>();
        Panel.getCostumersInstance().forEach(costumer -> {
            result.addAll(costumer.getOrderList());
        });
        Panel.getCouriersInstance().forEach(courier -> {
            result.addAll(courier.getOrderList());
        });
        return result;
    }

    public ArrayList<Order> sortOrdersByPrice() {
        ArrayList<Order> result = getOrderList();
        result.sort(Comparator.comparing(Order::getTotalPrice));
        return result;
    }

    public void addToTp(int amount) {
        this.takeProfit += amount;
    }

    public boolean hasEnough(Order order) {
        AtomicBoolean result = new AtomicBoolean(false);
        ArrayList<Commodity> storage = getStore().getStorage();
        order.getCommodityList().forEach(commodity -> {
            if (!(storage.contains(commodity) &&
                    storage.get(storage.indexOf(commodity)).getQuantity() > commodity.getQuantity())){
                result.set(true);
            }
        });
        return result.get();
    }

    public void buy(){
        ReceiptContainer receiptContainer = new ReceiptContainer();
        receiptContainer.setReceipt(JsonGenerator.generateBuyRecipe());
        Order order = new Order();
        receiptContainer.process(order);
        getStore().addToStorage(order.commodityList());
    }
}
