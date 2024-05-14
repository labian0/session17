package properties;

/**
 * Cabin class.
 */
public class Cabin extends AbstractProperty {    

    public Cabin(double price, String description) {
        super(price, description);
    }

    @Override
    public boolean isCabin() {
        return true;
    }
    
}
