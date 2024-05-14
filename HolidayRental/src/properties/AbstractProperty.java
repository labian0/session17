package properties;

import holidayrental.Rent;
import java.util.Objects;
import locations.AbstractLocation;
/**
 * Abstract property class. No abstract methods by the "is" methods should be
 * correctly implemented in extending classes.
 */
public abstract class AbstractProperty {
    public static final double MIN_PRICE = 1.00;
    public static final double MAX_PRICE = 10000.00;
    private final double price;
    private final String description;
    private AbstractLocation location=null;
    private Rent currentRent = null;

    public boolean isAppartment() {
        return false;
    }

    public boolean isCabin() {
        return false;
    }

    public boolean isHouse() {
        return false;
    }

    public AbstractProperty(double price, String description) {
        this.price = price;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String type = "";
        if (isCabin()) {
            type += "Cabin: ";
        }
        if (isHouse()) {
            type += "House: ";
        }
        if (isAppartment()) {
            type += "Appartment: ";
        }
        return type + "price=" + price + ", description=" + description;
    }

    @Override
    public int hashCode() {
        return this.description.hashCode() + 17*(int)this.price;
    }

    @Override
    public boolean equals(Object obj) {
        return false; // TODO
    }

    public void setLocation(AbstractLocation location) {
        // TODO
    }
    public void setRent(Rent newRent) {
        // TODO
    }
    public void endRent() {
        // TODO
    }
    public Rent getRent() {
        return currentRent;
    }
    public boolean available() {
        return currentRent==null;
    }
}