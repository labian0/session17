package holidayrental;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import properties.AbstractProperty;
import people.Person;
import locations.AbstractLocation;
import java.util.Set;
//import locations.BeachArea;
import locations.CityArea;
import locations.ForestArea;
import properties.Appartment;
//import properties.Cabin;
//import properties.House;

/**
 * Main application class. Is associated to all of the data.
 */
public class HolidayRental {

    private final Set<AbstractLocation> allLocations;
    private final Set<AbstractProperty> allProperties;
    private final Set<Person> everybody;
    private final Set<Rent> allRents;

    HolidayRental() {
        this.allLocations = new HashSet<>();
        this.allProperties = new HashSet<>();
        this.everybody = new HashSet<>();
        this.allRents = new HashSet<>();
    }

    public static void main(String[] args) {
        HolidayRental app = new HolidayRental();
        app.fillInitialData();
        app.fillAdditionalData();
        new UserInteractions().run(app);
    }
    private void fillAdditionalData() {
        // Your additional data here.
    }

    private void fillInitialData() {
        Person p1 = new Person("Alice");
        Person p2 = new Person("Bob");
        Person p3 = new Person("Carol");
        Person p4 = new Person("Dave");
        everybody.add(p1);
        everybody.add(p2);
        everybody.add(p3);
        everybody.add(p4);
        //AbstractLocation l1 = new BeachArea("Otter beach");
        AbstractLocation l2 = new CityArea("Penguin village");
        AbstractLocation l3 = new ForestArea("Lost woods");
        //allLocations.add(l1);
        allLocations.add(l2);
        allLocations.add(l3);
        //AbstractProperty pro1 = new Cabin(29.50, "Sam suffy");
        AbstractProperty pro2 = new Appartment(129.50, "42, Luxury suites");
        //AbstractProperty pro3 = new House(400.00, "Meadows manor");
        //allProperties.add(pro1);
        allProperties.add(pro2);
        //allProperties.add(pro3);
        //l1.addProperty(pro1);
        l2.addProperty(pro2);
        //l3.addProperty(pro3);
        //startRent(p1, pro1, LocalDate.of(2026, 1, 1));
        startRent(p2, pro2, LocalDate.of(2026, 2, 15));
        //startRent(p3, pro3, LocalDate.of(2026, 3, 20));
        //endRent(pro1, LocalDate.of(2026, 4, 1));
        //startRent(p4, pro1, LocalDate.of(2026, 4, 2));
    }

    void startRent(Person p, AbstractProperty pro, LocalDate start) {
        // TODO:
        // Create the Rent object
        Rent rent = new Rent(p,pro,start);
        
        // Add that object to the collection of rents
        this.allRents.add(rent);
        // Change the rent of the property
        pro.setRent(rent);
        // Add the rent to the rent history of the person
        p.addRent(rent);
    }

    void endRent(AbstractProperty property, LocalDate end) {
        Rent r = property.getRent();
        if (r == null) {
            return;
        }
        r.setEnd(end);
        property.endRent();
    }

    Map<AbstractLocation, Set<AbstractProperty>> getPropertiesByLocation() {
        Map<AbstractLocation, Set<AbstractProperty>> res = new HashMap<>();
        for (AbstractLocation l : allLocations) {
            res.put(l, new HashSet<>(l.getProperties()));
        }
        return res;
    }
    Set<Person> getPeople() {
        return new HashSet<>(everybody);
    }
    Set<Rent> getRents() {
        return new HashSet<>(allRents);
    }

    void addPerson(Person p) {
        if (p!=null) {
            everybody.add(p);
        }
    }

    void addLocation(AbstractLocation loc) {
        if (loc!=null) {
            allLocations.add(loc);
        }
    }

    void addPropertyAt(AbstractProperty prop, AbstractLocation loc) {
        if (loc==null || prop==null) {
            return;
        }
        loc.addProperty(prop);
        allProperties.add(prop);
    }

}
