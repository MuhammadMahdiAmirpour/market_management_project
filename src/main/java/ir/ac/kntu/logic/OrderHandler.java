package ir.ac.kntu.logic;

import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.enums.OrderState;
import ir.ac.kntu.objects.*;
import ir.ac.kntu.reporter.Reporter;


public class OrderHandler {

    Order order;
    Location supermarketLocation;
    Location costumerLocation;
    FakeDate deadLine;
    Courier courier;
    SuperMarket from;

    Costumer to;

    public void handle(Order order) {
        Reporter reporter = new Reporter();
        if (deadLine.getMonth() == Panel.fakeDate.getMonth() && deadLine.getDay() == Panel.fakeDate.getDay() &&
                Panel.fakeDate.getYear() == deadLine.getYear() && Panel.fakeDate.getHour() == deadLine.getHour() &&
                Panel.fakeDate.getMinute() == deadLine.getMinute() &&
                Panel.fakeDate.getSecond() == deadLine.getSecond()) {
            order.setOrderState(OrderState.DELIVERED);
            courier.setBusy(false);
            reporter.report("courier: \n" + courier.toString() + "\n" + "delivered this order\n" + order);
            courier.addToDeliveredOrders(order);
            courier.addToThisMonthOutCome((int) (order.getTotalPrice() * 0.05));
            if (Panel.fakeDate.getDay() == 28 && Panel.fakeDate.getHour() == 24 && Panel.fakeDate.getMinute() == 60 &&
                    Panel.fakeDate.getSecond() == 60) {
                courier.addToWalletBalance(courier.getThisMonthOutCome());
            }
            from.addToNumberOfSales();
            from.addToTp(order.getCommodityList().stream().mapToInt(commodity ->
                    Integer.parseInt(commodity.getPrice()) * Panel.getTpMap().get(commodity.getType())).sum());
            from.addToTotalSalesAmount(order.getTotalPrice() + order.getCommodityList().stream().mapToInt(commodity ->
                    Integer.parseInt(commodity.getPrice()) * Panel.getTpMap().get(commodity.getType())).sum());
            to.spend(order.getTotalPrice());
            order.setDateAndTimeOfGet(deadLine);
            order.getCommodityList().forEach(commodity -> from.getStore().removeFromStorage(commodity));
            reporter.report("order: \n" + order + "\n" +
                    "delivered by : \n" + courier.toString() + "\n" +
                    "from : " + from.toString() + "\n" +
                    "to : " + to.toString() + "\n");
        }
    }

    public OrderHandler(Order order, SuperMarket supermarket, Location costumerLocation, Courier courier,
                        Costumer to) {
        this.order = order;
        this.to = to;
        this.costumerLocation = costumerLocation;
        this.supermarketLocation = supermarket.getLocation();
        this.deadLine = new FakeDate();
        deadLine.setYear(Panel.fakeDate.getYear());
        deadLine.setMonth(Panel.fakeDate.getMonth());
        deadLine.setDay(Panel.fakeDate.getDay());
        deadLine.setHour(Panel.fakeDate.getHour());
        deadLine.setMinute(Panel.fakeDate.getMinute());
        deadLine.setSecond(Panel.fakeDate.getSecond());
        deadLine.addToSeconds(10  + costumerLocation.getDistanceFrom(supermarketLocation));
        this.courier = courier;
    }
}
