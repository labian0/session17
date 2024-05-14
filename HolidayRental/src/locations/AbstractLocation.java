package locations;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import properties.AbstractProperty;

/**
 * Abstract location class, must be extended by classes providing the correct
 * predicate implementation (test returns true whenever the property is
 * INCOMPATIBLE with the location) and suitable value for "is" methods.
 */
public abstract class AbstractLocation implements Predicate<AbstractProperty> {
    private final String description;
    private final Set<AbstractProperty> properties;
    public AbstractLocation(String description) {
        this.description = description;
        properties = new HashSet<>();
    }

    public Set<AbstractProperty> getProperties() {
        return new HashSet<>(properties);
    }
    
    public boolean addProperty(AbstractProperty property) {
        return false; // TODO
    }
    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return false; // TODO
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String type = "";
        if (isBeach()) {
            type += "Beach ";
        }
        if (isCity()) {
            type += "City ";
        }
        if (isForest()) {
            type += "Forest ";
        }
        return type+"location: " + description;
    }
    
    public boolean isBeach() { return false; }
    public boolean isCity() { return false; }
    public boolean isForest() { return false; }
}