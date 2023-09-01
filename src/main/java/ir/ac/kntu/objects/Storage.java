package ir.ac.kntu.objects;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Storage {

    private final ArrayList<Commodity> storage = new ArrayList<>();

    public Storage() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage storage1)) return false;
        return Objects.equals(getStorage(), storage1.getStorage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStorage());
    }

    public void addToStorage(ArrayList<Commodity> commodities) {
        commodities.forEach(this::addToStorage);
    }

    public void addToStorage(Commodity commodity) {
        if (!storage.contains(commodity)) {
            this.storage.add(commodity);
        } else {
            storage.get(storage.indexOf(commodity)).setQuantity
                    ((storage.get(storage.indexOf(commodity)).getQuantity()) + commodity.getQuantity());
        }
    }

    public ArrayList<Commodity> getStorage() {
        return storage;
    }

    public void removeFromStorage(Commodity commodity) {
        if (!storage.contains(commodity)) {
            System.out.println("this commodity does not exist in the storage! \n you can not sell it");
        } else {
            for (int i = 0; i < commodity.getQuantity(); i++) {
                getStorage().get(getStorage().indexOf(commodity)).setQuantity(
                        (getStorage().get(getStorage().indexOf(commodity)).getQuantity()) - 1);
                if (getStorage().get(getStorage().indexOf(commodity)).getQuantity() == 0) {
                    getStorage().remove(getStorage().get(getStorage().indexOf(commodity)));
                    break;
                }
            }
        }
    }

    public void getTotalQuantity() {
        AtomicReference<Double> result = new AtomicReference<>((double) 0L);
        getStorage().forEach(commodity -> {
            result.set(commodity.getQuantity() + result.get());
        });
        System.out.println(result.get());
    }
}
