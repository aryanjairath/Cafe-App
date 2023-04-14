package cs213.project5;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class represents an order with a unique order number,
 * menu items in list format, and a price field which contains the
 * total price for a given order.
 * @author Aryan Jairath, Anis Chihoub
 */
public class Order {

    private int orderNumber;
    private ArrayList<String> menuItems;
    private double price;

    private static double TAXAMOUNT = 1.06625;
    private int DIGITS = 2;

    private int ZEROPRICE = 0;

    public Order(int orderNumber){
        this.orderNumber = orderNumber;
        menuItems = new ArrayList<String>();
        price = ZEROPRICE;
    }

    /**
     * This method adds a specified item to the array list
     * @param item A String representing the item to be added
     * to the array list
     */
    public void addItem(String item){
        menuItems.add(item);
    }


    /**
     * This method removed a specified item from the array list
     * @param item A String representing the item to be removed
     * to the array list
     */
    public void removeitem(String item){
        menuItems.remove(item);
    }

    /**
     * This method return all menu items in the order
     * @return An array list containing all of the menu items
     */
    public ArrayList<String> getMenuItems(){
        return menuItems;
    }


    /**
     * This method sets the price for a given order
     * @param price A double value representing the new price of the
     * order
     */
    public void setPrice(double price){
        this.price = price;
    }


    /**
     * This method gets the price for a given order
     * @return A double representing the price of the
     * order
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * This method gets the price for a given order with the
     * tax amounts included
     * @return A double representing the total price of the
     * order
     */
    public String totalPrice(){
        return round(getPrice() * TAXAMOUNT);
    }

    /**
     * This method returns a String representation of an order
     * @return A String representing the order fields.
     */
    public String toString(){
        return "Order number: " + getOrderNumber() + " "
                + this.getMenuItems().toString() + " Price: " + totalPrice();

    }


    /**
     * Round a double number to two digits
     * @param number The number to be rounded to two digits
     * @return The updated value for the total price rounded
     * to two digits
     */
    private String round(double number){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(DIGITS);
        df.setMinimumFractionDigits(DIGITS);
        return df.format(number);
    }

    /**
     * This method gets the order number for some order
     * @return An int representing the order number
     */
    public int getOrderNumber(){
        return this.orderNumber;
    }


    /**
     * This method sets the order for a given order number
     * @param orderNumber The order number to set for an order
     */
    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

}
