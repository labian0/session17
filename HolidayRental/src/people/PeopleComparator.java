package people;

import java.util.Comparator;

/**
 * A simple comparator putting null people at the end of lists and ordering
 * non-null people by name.
 */
public class PeopleComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        if(p1 == p2){
            return 0;
        }
        if(p1 == null) return 1;
        if(p2 == null) return -1;

        return p1.getName().compareTo(p2.getName());
    }
}
