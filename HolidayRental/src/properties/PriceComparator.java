/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package properties;

import java.util.Comparator;

/**
 *
 * @author brumery
 */
public class PriceComparator implements Comparator<AbstractProperty>{

    @Override
    public int compare(AbstractProperty p1, AbstractProperty p2) {
        return (int) (p1.getPrice()-p2.getPrice())*100;
    }
    
}
