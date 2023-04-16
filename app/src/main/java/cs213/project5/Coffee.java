package cs213.project5;


import java.util.ArrayList;

/**
 * This class is used to represent a coffee with
 * different sizes as selected by the GUI.
 * @author Aryan Jairath, Anis Chihoub
 */
public class Coffee extends MenuItem{
    String cupsize;
    ArrayList<String> addIns;
    private final double THIRTYPERCENT = 0.30;
    private final int ZEROBALANCE = 0;
    private final double SHORT = 1.89;
    private final double TALL = 2.29;
    private final double GRANDE = 2.69;
    private final double VENTI = 1.89;



    /**
     * Constructor for the coffee class,
     * @param cupsize a String representing the size of the cup.
     */
    public Coffee(String cupsize){
        super(cupsize);
        this.cupsize = cupsize;
        addIns = new ArrayList<>();
    }

    /**
     * Returns the cup size to the user.
     * @return a string representing the cup size.
     */
    public String getCupSize(){
        return cupsize;
    }

    /**
     * Sets the size of the coffee, based on an input string.
     * @param cupsize a string representing the size of the cup.
     */
    public void setCupsize(String cupsize){
        this.cupsize = cupsize;
    }

    /***
     * Adds a particular add in for this coffee.
     * @param addIn a string representing an add in.
     */

    public void addaddIn(String addIn){
        addIns.add(addIn);
    }


    /***
     * Adds a particular add in for this coffee.
     * @param addIn a string representing an add in.
     */

    public void addaddIn(ArrayList addIn){
        addIns.addAll(addIn);
    }

    /**
     * Attempts to remove an addin from the addin list.
     * Returns false if it is not present.
     * @param addIn a String representing an addin in quesiton.
     * @return a boolean representing if the operation was successful.
     */


    /**
     * Returns the item price for the given coffee based on
     * @return a double representing the price.
     */
    @Override
    public double itemPrice() {
        double balance = ZEROBALANCE;
        if(this.cupsize.equals("Short")){
            balance += SHORT + addIns.size() * THIRTYPERCENT;
        }else if(this.cupsize.equals("Tall")){
            balance += TALL + addIns.size() * THIRTYPERCENT;
        }else if(this.cupsize.equals("Grande")){
            balance += GRANDE + addIns.size() * THIRTYPERCENT;
        }else if(this.cupsize.equals("Venti")){
            balance += VENTI + addIns.size() * THIRTYPERCENT;
        }
        return balance;
    }


    /**
     * A string representation of a coffee object's fields
     * @param quantity The number of coffees
     * @return A String representation of the coffee object
     */
    public  String toString(int quantity){
        String output = getCupSize() + " black coffee. \n";
        output += "Add Ons below: \n";
        for (String addOn : this.addIns)
        {
            output += addOn + "\t";
        }
        output += "____________";
        output += "Total Price: " + itemPrice()*quantity;

        return output;
    }
}
