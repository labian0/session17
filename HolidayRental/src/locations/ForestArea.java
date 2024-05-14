package locations;

import properties.AbstractProperty;

/**
 *  A forest area: cannot contain appartments.
 */
public class ForestArea extends AbstractLocation {

    public ForestArea(String description) {
        super(description);
    }

    @Override
    public boolean test(AbstractProperty t) {
        return t.isAppartment();
    }

    @Override
    public boolean isForest() {
        return true;
    }
    
}
