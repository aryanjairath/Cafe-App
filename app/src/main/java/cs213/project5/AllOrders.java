package cs213.project5;


import java.util.ArrayList;

/**
 * This class contains all the orders placed in
 * list format and can be removed and added
 * if needed.
 * @author Aryan Jairath, Anis Chihoub
 */
public class AllOrders {
    public static ArrayList<Order> allOrder = new ArrayList<>();
    public static ArrayList<Order> storeOrders = new ArrayList<>();

    public static int uniqueNumber = 1;
    public static double runningTotal = 0;

    public static final int INDEX = 0;

    /**
     * Adds an order to either a specified order in the list or
     * appends it to the end
     * @param order The order being dealt with
     * @param index The index at which to update the order or add
     */
    public static void addOrder(Order order, int index){
        if(allOrder.size() == index) {
            allOrder.add(order);
        }else {
            ArrayList<String> list = allOrder.get(index).getMenuItems();
            ArrayList<String> list2 = order.getMenuItems();
            for(int i = 0; i < list2.size(); i++){
                list.add(list2.get(i));
            }
            Order ord = new Order(index);
            for(int i = 0; i < list.size(); i++){
                ord.addItem(list.get(i));
            }
            ord.setPrice(order.getPrice());
            allOrder.set(index, ord);
        }
    }

    /**
     * This method adds a store order to the orders list
     * @param index The place at which the order is inserted
     */
    public static void addStoreOrder(int index){
        Order orderToAdd = allOrder.get(INDEX);
        orderToAdd.setOrderNumber(AllOrders.getUniqueNumber());
        storeOrders.add(orderToAdd);
    }

    /**
     * Returns a temporary arraylist where both coffee and donut items
     * are combined
     * @return An Array List which contains a combined order of coffee
     * and donut
     */
    public static ArrayList<Order> allOrderR(){
        return allOrder;
    }

    /**
     * Returns the unique order number
     * @return An integer representing the order number
     * that is currently being processed.
     */
    public static int getUniqueNumber(){
        return uniqueNumber;
    }

    /**
     * Increments the unique order number for an order
     */
    public static void incrementUnique(){
        uniqueNumber++;
    }

    /**
     * A getter method for all the stores orders
     * @return An array list containing all orders
     * that the store has
     */
    public static ArrayList<Order> allStoreOrders(){
        return storeOrders;
    }

    /**
     * Removes an item from the store orders array list
     * @param index The index from which to remove
     * a given order
     */
    public static void removeOrderedItem(int index){
        storeOrders.remove(index);
    }

    /**
     * Removes an item from the store orders array list
     * @param index The string containing an index from which to remove
     * a given order
     */
    public static void removeItem(String index){
        for(int i = 0; i < storeOrders.size(); i++ ){
            if(storeOrders.get(i).toString().equals(index)){
                storeOrders.remove(i);
                break;
            }
        }
    }

}
