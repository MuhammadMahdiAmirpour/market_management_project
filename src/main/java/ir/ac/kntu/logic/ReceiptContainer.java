package ir.ac.kntu.logic;

import ir.ac.kntu.enums.CommodityType;
import ir.ac.kntu.enums.ReceiptType;
import ir.ac.kntu.objects.Commodity;
import ir.ac.kntu.objects.Order;
import ir.ac.kntu.objects.Storage;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ir.ac.kntu.enums.CommodityType.*;

public class ReceiptContainer {
    private ArrayList<Commodity> commodities = new ArrayList<>();
    private ReceiptType receipt_type;

    private String receipt;

    public void setCommodities(ArrayList<Commodity> commodities) {
        this.commodities = commodities;
    }

    public ReceiptContainer() {

    }

    public ReceiptType getReceipt_type() {
        return receipt_type;
    }

    public void setReceipt_type(ReceiptType receipt_type) {
        this.receipt_type = receipt_type;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }


    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }

    public ReceiptContainer(String receipt, ReceiptType receipt_type) {
        this.receipt = receipt;
        this.receipt_type = receipt_type;
    }

    public void process(Order order) {
        Pattern protein_pattern = Pattern.compile("\"protein\":\\[.*?},],");
        Matcher protein_matcher = protein_pattern.matcher(receipt);
        if (protein_matcher.find()) {
            extractByCommodityType(commodities, protein_matcher.group(), protein, order);
        }
        Pattern sanitary_pattern = Pattern.compile("\"sanitary\":\\[.*?},],");
        Matcher sanitary_matcher = sanitary_pattern.matcher(receipt);
        if (sanitary_matcher.find()) {
            extractByCommodityType(commodities, sanitary_matcher.group(), sanitary, order);
        }
        Pattern diary_pattern = Pattern.compile("\"diary\":\\[.*?},],");
        Matcher diary_matcher = diary_pattern.matcher(receipt);
        if (diary_matcher.find()) {
            extractByCommodityType(commodities, diary_matcher.group(), diary, order);
        }
        Pattern drink_pattern = Pattern.compile("\"drink\":\\[.*?},],");
        Matcher drink_matcher = drink_pattern.matcher(receipt);
        if (drink_matcher.find()) {
            extractByCommodityType(commodities, drink_matcher.group(), drink, order);
        }
        Pattern junk_food_pattern = Pattern.compile("\"junkFood\":\\[.*?},],");
        Matcher junk_food_matcher = junk_food_pattern.matcher(receipt);
        if (junk_food_matcher.find()) {
            extractByCommodityType(commodities, junk_food_matcher.group(), junkFood, order);
        }
    }

    public void extractProteins(Storage storage) {
        Pattern protein_pattern = Pattern.compile("\"protein\":\\[.*?},],");
        Matcher protein_matcher = protein_pattern.matcher(receipt);
        if (protein_matcher.find()) {
            extractByCommodityType(commodities, protein_matcher.group(), protein, storage);
        }
    }

    public void extractSanitary(Storage storage) {
        Pattern sanitary_pattern = Pattern.compile("\"sanitary\":\\[.*?},],");
        Matcher sanitary_matcher = sanitary_pattern.matcher(receipt);
        if (sanitary_matcher.find()) {
            extractByCommodityType(commodities, sanitary_matcher.group(), sanitary, storage);
        }
    }

    public void extractDiary(Storage storage) {
        Pattern diary_pattern = Pattern.compile("\"diary\":\\[.*?},],");
        Matcher diary_matcher = diary_pattern.matcher(receipt);
        if (diary_matcher.find()) {
            extractByCommodityType(commodities, diary_matcher.group(), diary, storage);
        }
    }

    public void extractDrink(Storage storage) {
        Pattern drink_pattern = Pattern.compile("\"drink\":\\[.*?},],");
        Matcher drink_matcher = drink_pattern.matcher(receipt);
        if (drink_matcher.find()) {
            extractByCommodityType(commodities, drink_matcher.group(), drink, storage);
        }
    }

    public void extractJunkFood(Storage storage) {
        Pattern junk_food_pattern = Pattern.compile("\"junkFood\":\\[.*?},],");
        Matcher junk_food_matcher = junk_food_pattern.matcher(receipt);
        if (junk_food_matcher.find()) {
            extractByCommodityType(commodities, junk_food_matcher.group(), junkFood, storage);
        }
    }

    public void process(Storage storage) {
        extractDiary(storage);
        extractDrink(storage);
        extractProteins(storage);
        extractProteins(storage);
        extractSanitary(storage);
        extractJunkFood(storage);
    }

    public void extractByCommodityType(ArrayList<Commodity> commodities, String receiptSubString,
                                       CommodityType commodityType, Order order) {
        addToCommodities(createCommodityListStringObject(receiptSubString), commodities, commodityType, order);
    }

    public ArrayList<String> createCommodityListStringObject(String receiptSubString) {
        ArrayList<String> commodity_list = new ArrayList<>();
        Pattern commodity_pattern = Pattern.compile("\"[a-zA-Z]*\":\\{.*?}");
        Matcher commodity_matcher = commodity_pattern.matcher(receiptSubString);
        while (commodity_matcher.find()) {
            commodity_list.add(commodity_matcher.group());
        }
        return commodity_list;
    }

    public void extractByCommodityType(ArrayList<Commodity> commodities, String receiptSubString,
                                       CommodityType commodityType, Storage storage) {
       addToCommodities(createCommodityListStringObject(receiptSubString), commodities, commodityType, storage);
    }

    private void addToCommodities(ArrayList<String> commodity_list, ArrayList<Commodity> commodities,
                                  CommodityType commodity_type, Order order) {
        for (String commodity_to_string : commodity_list) {
            Commodity commodity_object = createNewCommodity(commodities, commodity_type, commodity_to_string);
            if (receipt_type == ReceiptType.SELL) {
                order.removeFromCommodityList(commodity_object);
            } else {
                order.addToCommodityList(commodity_object);
            }
        }
    }

    private Commodity createNewCommodity(ArrayList<Commodity> commodities, CommodityType commodity_type,
                                         String commodity_to_string) {
        Commodity commodity_object = new Commodity();
        commodity_object.setType(commodity_type);
        setName(commodity_object, commodity_to_string);
        setPrice(commodity_object, commodity_to_string);
        setQuantity(commodity_object, commodity_to_string);
        setProductionDate(commodity_object, commodity_to_string);
        setExpirationDate(commodity_object, commodity_to_string);
        commodities.add(commodity_object);
        return commodity_object;
    }

    private void addToCommodities(ArrayList<String> commodity_list, ArrayList<Commodity> commodities,
                                  CommodityType commodity_type, Storage storage) {
        for (String commodity_to_string : commodity_list) {
            Commodity commodity_object = createNewCommodity(commodities, commodity_type, commodity_to_string);
            if (receipt_type == ReceiptType.SELL) {
                storage.removeFromStorage(commodity_object);
            } else {
                storage.addToStorage(commodity_object);
            }
        }
    }

    public static void setQuantity(Commodity commodity, String commodity_to_string) {
        Pattern quantity_pattern = Pattern.compile("\"quantity\":\"\\d+\"");
        Matcher quantity_matcher = quantity_pattern.matcher(commodity_to_string);
        if (quantity_matcher.find()) {
            commodity.setQuantity(Double.parseDouble(quantity_matcher.group().replace
                            ("\"quantity\":", "").
                    replace("\"", "")));
        }
    }

    public static void setName(Commodity commodity, String commodity_to_string) {
        Pattern name_pattern = Pattern.compile("^\"[a-zA-Z]*?\"");
        Matcher name_matcher = name_pattern.matcher(commodity_to_string);
        if (name_matcher.find()) {
            commodity.setName(name_matcher.group().replace("\"", ""));
        }
    }

    public static void setPrice(Commodity commodity, String commodity_to_string) {
        Pattern price_pattern = Pattern.compile("\"price\":\"\\d*\"");
        Matcher price_matcher = price_pattern.matcher(commodity_to_string);
        if (price_matcher.find()) {
            commodity.setPrice(price_matcher.group().replace("\"price\":", "").
                    replace("\"", ""));
        }
    }

    public static void setProductionDate(Commodity commodity, String commodity_to_string) {
        Pattern production_date_pattern = Pattern.compile("\"productionDate\":\"\\d+-\\d+-\\d+\"");
        Matcher production_date_matcher = production_date_pattern.matcher(commodity_to_string);
        if (production_date_matcher.find()) {
            commodity.setProductionDate(production_date_matcher.group().
                    replace("\"productionDate\":", "").replace("\"", ""));
        }
    }

    public static void setExpirationDate(Commodity commodity, String commodity_to_string) {
        Pattern expiration_date_pattern = Pattern.compile("\"expirationDate:\"\\d+-\\d+-\\d+\"");
        Matcher expiration_date_matcher = expiration_date_pattern.matcher(commodity_to_string);
        if (expiration_date_matcher.find()) {
            commodity.setExpirationDate(expiration_date_matcher.group().
                    replace("\"expirationDate:", "").replace("\"", ""));
        }
    }
}
