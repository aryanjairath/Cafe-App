package cs213.project5;


/**
 * This class is used to represent a donut where
 * the flavors are decided based on what type of
 * donut is selected.
 * @author Aryan Jairath, Anis Chihoub
 */
public class Donut extends MenuItem{
    String flavor;
    private static final int NOPRICE = 0;

    /**
     * Constructor for Donut. Takes a string represetning the flavor.
     * @param flavor a String containing the flavor type.
     */
    public Donut(String flavor){
        super(flavor);
        this.flavor = flavor;
    }

    /**
     * This method is a getter method for the price of a donut
     * @return A double representing the price of a donut
     */
    @Override
    public double itemPrice() {
        return NOPRICE;
    }

    /**
     * A getter for the flavor of a certain donut object
     * @return A String representing donut flavor
     */
    public String getFlavor(){
        return this.flavor;
    }
}
