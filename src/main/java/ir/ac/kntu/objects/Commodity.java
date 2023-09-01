package ir.ac.kntu.objects;

import ir.ac.kntu.enums.CommodityType;

import java.time.LocalDate;
import java.util.Comparator;

public class Commodity {
    private String name;
    private CommodityType type;
    private double quantity = 0L;
    private String price;
    private LocalDate production_date;
    private LocalDate expiration_date;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setType(CommodityType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductionDate(String date) {
        String[] dateNums = date.split("-");
        this.production_date = LocalDate.of
                (Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setExpirationDate(String date) {
        String[] dateNums = date.split("-");
        this.expiration_date = LocalDate.of
                (Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
    }

    public CommodityType getType() {
        return type;
    }

    public LocalDate getExpirationDate() {
        return expiration_date;
    }

    public String getPrice() {
        return price;
    }

    public Commodity() {
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", price='" + price + '\'' +
                ", production_date=" + production_date +
                ", expiration_date=" + expiration_date +
                ", quantity='" + quantity + '\'' +
                "}\n";
    }

    public static Comparator<Commodity> QuantityComparator = (commodity1, commodity2) -> {
        Double quantity1 = commodity1.getQuantity();
        Double quantity2 = commodity2.getQuantity();
        return quantity1.compareTo(quantity2);
    };
    public static Comparator<Commodity> ExpirationDateComparator = (commodity1, commodity2) -> {
        LocalDate date1 = commodity1.getExpirationDate();
        LocalDate date2 = commodity2.getExpirationDate();
        return date1.compareTo(date2);
    };

    public static Comparator<Commodity> PriceComparator = (commodity1, commodity2) -> {
        Long price1 = Long.parseLong(commodity1.getPrice());
        Long price2 = Long.parseLong(commodity2.getPrice());
        return price1.compareTo(price2);
    };

    public String getName() {
        return name;
    }
}
