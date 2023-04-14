package cs213.project5;

/**
 * This class is used to represent a donut hole with
 * three potential flavors as selected by the GUI.
 * @author Aryan Jairath, Anis Chihoub
 */
public class DonutHole extends Donut{

    private static final double PRICE = 0.39;


    /**
     * Constructor for a donut hole object
     * @param flavor A String representing the flavor
     * of the donut hole that is selected
     */
    public DonutHole(String flavor) {
        super(flavor);
    }


    /**
     * This method is a getter method for the price of
     * a donut hole object
     * @return A double representing the price of a
     * donut hole
     */
    @Override
    public double itemPrice(){
        return PRICE;
    }
}
