package properties;

/**
 * Appartment class.
 */
public class Appartment extends AbstractProperty {    

    public Appartment(double price, String description) {
        super(price, description);
    }

    @Override
    public boolean isAppartment() {
        return true;
    }
    
}
