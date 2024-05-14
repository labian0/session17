package locations;

import properties.AbstractProperty;

/**
 * A city area: cannot contain cabins.
 */
public class CityArea extends AbstractLocation {

    public CityArea(String description) {
        super(description);
    }

    @Override
    public boolean test(AbstractProperty t) {
        return t.isCabin();
    }

    @Override
    public boolean isCity() {
        return true;
    }
    
}
