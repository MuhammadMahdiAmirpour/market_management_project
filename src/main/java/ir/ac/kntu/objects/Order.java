package ir.ac.kntu.objects;

import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.enums.OrderState;

import java.util.ArrayList;

public class Order {

    private ArrayList<Commodity> commodityList = new ArrayList<>();
    private OrderState orderState;
    private FakeDate dateAndTimeOfSet;
    private FakeDate dateAndTimeOfGet;

    public ArrayList<Commodity> commodityList() {
        return commodityList;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setCommodityList(ArrayList<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public ArrayList<Commodity> getCommodityList() {
        return this.commodityList;
    }

    public void addToCommodityList(Commodity commodity) {
        if (commodityList.contains(commodity)) {
            this.commodityList.forEach(commodity1 -> {
                if (commodity1.getType().equals(commodity.getType())) {
                    commodity1.setQuantity(commodity1.getQuantity() + 1);
                }
            });
        } else {
            commodityList.add(commodity);
        }
    }

    public void removeFromCommodityList(Commodity commodity) {
        if (commodityList.contains(commodity)) {
            this.commodityList.forEach(commodity1 -> {
                if (commodity1.getType().equals(commodity.getType())) {
                    if (commodity1.getQuantity() == 1) {
                        commodityList.remove(commodity1);
                    } else {
                        commodity1.setQuantity(commodity1.getQuantity() - 1);
                    }
                }
            });
        }
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public int getTotalPrice() {
        return getCommodityList().stream().mapToInt(commodity -> Integer.parseInt(commodity.getPrice())).sum();
    }

    public Order() {

    }

    public Order(OrderState orderState) {
        setOrderState(orderState);
        setDateAndTimeOfSet(Panel.fakeDate);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        getCommodityList().forEach(result::append);
        return "order commodities : \n" + result + "\n" +
                "order state: " + this.orderState + "\n" +
                "date of set: " + this.dateAndTimeOfSet + "\n" +
                "date of get: " + this.dateAndTimeOfGet + "\n";
    }

    public FakeDate getDateAndTimeOfSet() {
        return dateAndTimeOfSet;
    }

    public void setDateAndTimeOfSet(FakeDate dateAndTimeOfSet) {
        this.dateAndTimeOfSet = new FakeDate(dateAndTimeOfSet.getYear(), dateAndTimeOfSet.getMonth(),
                dateAndTimeOfSet.getDay(), dateAndTimeOfSet.getHour(), dateAndTimeOfSet.getMinute(),
                dateAndTimeOfSet.getSecond());
    }

    public FakeDate getDateAndTimeOfGet() {
        return dateAndTimeOfGet;
    }

    public void setDateAndTimeOfGet(FakeDate dateAndTimeOfGet) {
        this.dateAndTimeOfGet = new FakeDate(dateAndTimeOfGet.getYear(), dateAndTimeOfGet.getMonth(),
                dateAndTimeOfGet.getDay(), dateAndTimeOfGet.getHour(), dateAndTimeOfGet.getMinute(),
                dateAndTimeOfGet.getSecond());
    }
}
