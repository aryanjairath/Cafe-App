package cs213.project5;

/**
 * This abstract class represents a menu item where
 * other classes extend it such as donuts and coffee. Every
 * menu item has a type of item and our GUI manages donuts/coffee.
 * @author Aryan Jairath, Anis Chihoub
 */
public abstract class MenuItem {

    /**
     * Abstract method representing the item price. To be implemented
     * in subclasses
     * @return A double representing the prce of a given menu item
     */
    public abstract double itemPrice();

    private String item;

    /**
     * Creates a MenuItem with a given String as input
     * @param item A String represnting the type of MenuItem
     */
    public MenuItem(String item){
        this.item = item;
    }

}
