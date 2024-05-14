package holidayrental;

import java.util.function.Predicate;

/**
 *  Predicate identifying finished rents. negate() provides active rents.
 */
public class Finished implements Predicate<Rent> {

    /**
     * Test method override.
     * 
     * @param t any rent
     * @return true iff the rent is finished
     */
    @Override
    public boolean test(Rent t) {
        return false; // TODO
    }
    
}
