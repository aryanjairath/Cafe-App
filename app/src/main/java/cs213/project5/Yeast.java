package cs213.project5;


/**
 * This class is used to represent a yeast donut with
 * six potential flavors as selected by the GUI.
 * @author Aryan Jairath, Anis Chihoub
 */
public class Yeast extends Donut{

    private static final double PRICE = 1.59;

    /**
     * Constructor for a yeast donut object
     * @param flavor A String representing the flavor
     * of the yeast donut that is selected
     */
    public Yeast(String flavor) {
        super(flavor);
    }

    /**
     * This method is a getter method for the price of
     * a yeast donut
     * @return A double representing the price of a yeast
     * donut
     */
    @Override
    public double itemPrice(){
        return PRICE;
    }
}
