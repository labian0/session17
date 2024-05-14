package holidayrental;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import locations.AbstractLocation;
//import locations.BeachArea;
import locations.CityArea;
import locations.ForestArea;
import people.PeopleComparator;
import people.Person;
import properties.AbstractProperty;
import properties.Appartment;
//import properties.Cabin;
//import properties.House;
import properties.PriceComparator;

/**
 * Class for user input and output, separate from the data placed in a
 * HolidayRental object (myApp).
 */
public class UserInteractions {

    private final Scanner scan;
    private final List<String> menuItems;
    private final List<String> locationTypes;
    private final List<String> propertyTypes;

    private HolidayRental myApp;

    UserInteractions() {
        scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        menuItems = new ArrayList<>();
        menuItems.add("Quit"); // 0
        menuItems.add("List all properties by location"); // 1
        menuItems.add("List everybody"); // 2
        menuItems.add("Add new person"); // 3
        menuItems.add("Add new location"); // 4
        menuItems.add("Add new property"); // 5
        menuItems.add("Rent a property"); // 6
        menuItems.add("List all active rentals"); // 7
        menuItems.add("Perform a checkout"); // 8
        menuItems.add("Display the total amount of money involved in finished rentals"); // 9
        menuItems.add("List all finished rentals"); // 10
        menuItems.add("Search for a property by various criteria"); // 11
        menuItems.add("Display statistics"); // 12
        locationTypes = new ArrayList<>();
        locationTypes.add("Forest");
        locationTypes.add("Beach");
        locationTypes.add("City");
        propertyTypes = new ArrayList<>();
        propertyTypes.add("Cabin");
        propertyTypes.add("House");
        propertyTypes.add("Appartment");
    }

    void run(HolidayRental app) {
        this.myApp = app;
        boolean quit;
        do {
            displayMenu();
            int choice = ARR_userNumericInput(0, menuItems.size() - 1, "Choose an action");
            quit = performAction(choice);
        } while (!quit);
    }

    private void displayMenu() {
        System.out.println("What do you want to do?");
        displayList(menuItems);
    }

    private void displayList(List<String> list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                System.out.println("" + i + "." + list.get(i));
            }
        } else {
            System.out.println("Giving up: no matches.");
        }
    }

    /**
     * Method used to ask for a numerical input between the specified limits and
     * prompt
     *
     * @param min Minimum possible valid value
     * @param max Maximum possible valid value
     * @param prompt String used to customize the sentence displayed
     * @return int chosen by the user
     */
    private int ARR_userNumericInput(int min, int max, String prompt) {
        int input = -1;
        do {
            System.out.format("%s.\nYour choice? [%d - %d]", prompt, min, max);
            System.out.println();
            input = scan.nextInt();
        } while (min > input || max < input);
        return input;
    }

    /**
     * Method used to ask for a String input with the specified prompt
     *
     * @param prompt String used to customize the sentence displayed
     * @return String chosen by the user
     */
    private String ARR_userStringInput(String prompt) {
        String input;
        do {
            System.out.format("Please enter %s:", prompt);
            System.out.println();
            input = scan.next();
        } while (input.length() < 1);
        return input;
    }

    /**
     * Method used to execute the method associated to the numerical input of
     * the user
     *
     * @param choice int chosen by the user
     * @return boolean that indicates that the user quits the app
     */
    private boolean performAction(int choice) {
        boolean res = false;
        switch (choice) {
            case 0:
                res = true;
                break;
            case 1: {
                Map<AbstractLocation, Set<AbstractProperty>> pbyl = myApp.getPropertiesByLocation();
                for (AbstractLocation l : pbyl.keySet()) {
                    if (pbyl.get(l).isEmpty()) {
                        System.out.println("No properties in " + l + ".");
                    } else {
                        System.out.println("All properties in " + l + ":");
                        for (AbstractProperty p : pbyl.get(l)) {
                            System.out.println("  -" + p);
                        }
                    }
                }
            }
            break;
            case 2:
                List<Person> people = new ArrayList<>(myApp.getPeople());
                people.sort(new PeopleComparator());
                if (people.isEmpty()) {
                    System.out.println("Nobody registered.");
                } else {
                    System.out.println("" + people.size() + " people registered:");

                    for (Person p : people) {
                        System.out.println("  -" + p);
                    }
                }
                break;
            case 3: {
                // TODO:
                // Prompt the user for the data required
                // Create the Person object
                // Call the correct method on myApp with the new object
            }
            break;
            case 4: {
                System.out.println("A new location can be added in the following area types:");
                displayList(locationTypes);
                int chosen = ARR_userNumericInput(0, locationTypes.size() - 1, "the chosen area type");
                String desc = ARR_userStringInput("the description of the location");
                AbstractLocation loc = null;
                switch (chosen) {
                    case 0:
                        loc = new ForestArea(desc);
                        break;
                    case 1:
                        //loc = new BeachArea(desc);
                        break;
                    case 2:
                        loc = new CityArea(desc);
                }
                if (myApp.getPropertiesByLocation().keySet().contains(loc)) {
                    System.out.println("Not added: this location was already present!");
                } else {
                    myApp.addLocation(loc);
                }
            }
            break;
            case 5: {
                Map<AbstractLocation, Set<AbstractProperty>> pbyl = myApp.getPropertiesByLocation();
                Map<String, AbstractLocation> locations = new HashMap<>();
                List<String> locStrings = new ArrayList<>();
                for (AbstractLocation loc : pbyl.keySet()) {
                    String s = loc.toString();
                    locStrings.add(s);
                    locations.put(s, loc);
                }
                System.out.println("Available locations for a new property:");
                displayList(locStrings);
                int chosen = ARR_userNumericInput(0, locStrings.size() - 1,
                        "the chosen location");
                AbstractLocation here = locations.get(locStrings.get(chosen));
                String desc = ARR_userStringInput("the description of the new property");
                System.out.println("Available property types:");
                displayList(propertyTypes);
                int alsoChosen = ARR_userNumericInput(0, propertyTypes.size() - 1,
                        "the chosen type");
                int price = ARR_userNumericInput((int) AbstractProperty.MIN_PRICE * 100,
                        (int) AbstractProperty.MAX_PRICE * 100, "the price times 100"
                        + " (taking cents into account, 1050 -> 10.50");
                AbstractProperty prop = null;
                switch (alsoChosen) {
                    case 0:
                        //prop = new Cabin(price / 100.0, desc);
                        break;
                    case 1:
                        //prop = new House(price / 100.0, desc);
                        break;
                    case 2:
                        prop = new Appartment(price / 100.0, desc);
                        break;
                }
                if (prop == null) {
                    System.out.println("Could not create a new property.");
                } else if (here.test(prop)) {
                    System.out.println("This property is not suitable for the"
                            + " chosen location.");
                } else if (here.getProperties().contains(prop)) {
                    System.out.println("This property was already present"
                            + " at that location.");
                } else {
                    myApp.addPropertyAt(prop, here);
                }
            }
            break;
            case 6: {
                // TODO
            }
            break;
            case 7: {
                // TODO
            }
            break;
            case 8: {
                List<Rent> rents = new ArrayList<>(myApp.getRents());
                rents.removeIf(new Finished());
                if (rents.isEmpty()) {
                    System.out.println("No active rents.");
                } else {
                    System.out.println("Active rents available for checkout:");
                    List<String> ls = new ArrayList<>();
                    Map<String, Rent> identifiedRent = new HashMap<>();
                    for (Rent r : rents) {
                        ls.add(r.toString());
                        identifiedRent.put(r.toString(), r);
                    }
                    displayList(ls);
                    int rentNumber = ARR_userNumericInput(0, ls.size() - 1,
                            "the number of the rent to checkout");
                    Rent theRent = identifiedRent.get(ls.get(rentNumber));
                    LocalDate end = dateInputAfter(theRent.getStart(),
                            "the date of the checkout");
                    myApp.endRent(theRent.getProperty(), end);
                }
            }
            break;
            case 9:
                double totalMoney=0.0;
                // TODO
                System.out.println("Total money involved in finished rents : "+ totalMoney);
                break;
            case 10: {
                List<Rent> rents = new ArrayList<>(myApp.getRents());
                rents.removeIf(new Finished().negate());
                if (rents.isEmpty()) {
                    System.out.println("No finished rents.");
                } else {
                    System.out.println("" + rents.size() + " finished rents:");
                    for (Rent r : rents) {
                        System.out.println(r);
                    }
                }
            }
            break;
            case 11:
                // TODO
                break;
            case 12:
                // TODO
                break;
        }
        return res;
    }

    private LocalDate dateInputAfter(LocalDate before, String prompt) {
        LocalDate res = before;
        do {
            res = dateInput(prompt);
        } while (res.isBefore(before) || res.isEqual(before));
        return res;
    }

    private LocalDate dateInput(String prompt) {
        System.out.println("Enter " + prompt + ", year then month then day:");
        LocalDate res = null;
        do {
            int year = ARR_userNumericInput(-5000, 5000, "the year");
            int month = ARR_userNumericInput(1, 12, "the month");
            int day = ARR_userNumericInput(1, 31, "the day");
            try {
                res = LocalDate.of(year, month, day);
            } catch (DateTimeException ex) {
                System.err.println(ex);
            }
        } while (res == null);
        return res;
    }

    private Person selectPerson() {
        List<Person> people = new ArrayList<>(myApp.getPeople());
        people.sort(new PeopleComparator());
        return (Person)selectFromCollection(new ArrayList<>(people));
    }

    /**
     * Prompts the user to select a property from the list of all properties.
     * @return the property selected by the user, or null.
     */
    private AbstractProperty selectProperty() {
        List<AbstractProperty> properties = new ArrayList<>();
        for (Set<AbstractProperty> set : myApp.getPropertiesByLocation().values()) {
            properties.addAll(set);
        }
        properties.sort(new PriceComparator());
        return null; // TODO
    }

    private Object selectFromCollection(Collection<Object> collection) {
        if (collection.isEmpty()) {
            System.out.println("Nothing.");
            return null;
        }
        List<String> strings = new ArrayList<>();
        Map<String, Object> withId = new HashMap<>();
        for (Object o : collection) {
            strings.add(o.toString());
            withId.put(o.toString(), o);
        }
        int choice = ARR_userNumericInput(0, strings.size() - 1, "your choice");
        return withId.get(strings.get(choice));
    }
}
