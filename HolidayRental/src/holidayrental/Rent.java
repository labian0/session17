package holidayrental;

import java.time.LocalDate;
import people.Person;
import properties.AbstractProperty;

/**
 * The rent class.
 * Immutable, except for the end date that can only be set once.
 */
public class Rent {

    private final Person tenant;
    private final AbstractProperty rented;
    private final LocalDate start;
    private LocalDate end = null;

    public Rent(Person tenant, AbstractProperty rented, LocalDate start) {
        this.tenant = tenant;
        this.rented = rented;
        this.start = start;
    }

    public int duration() {
        if (end==null) {
            return -1;
        }
        return (int) (end.toEpochDay() - start.toEpochDay()) + 1;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
    public double totalPrice() {
        if (end==null) {
            return -1;
        }
        return duration()*rented.getPrice();
    }

    public Person getTenant() {
        return tenant;
    }

    public AbstractProperty getProperty() {
        return rented;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    @Override
    public String toString() {
        String s = "Rent by: "+tenant+ " , for: "+rented;
        if (end==null) {
            s+=" active and started on: "+start;
        }
        else {
            s+=" finished, was from: "+start+" to: "+end+"; total price:"+totalPrice();
        }
        return s;
    }
    
    
}
