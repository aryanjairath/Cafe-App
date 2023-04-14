//package cs213.project5;
//
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//
///**
// * This class is the controller for the ordering basket and
// * manages all UI interactions for this view.
// * @author Aryan Jairath, Anis Chihoub
// */
//public class OrderingBasketController {
//
//    @FXML
//    private TextField subtotal;
//
//    @FXML
//    private TextField tax;
//
//    @FXML
//    private TextField amountdue;
//
//    @FXML
//    private ListView items;
//    private static double TAXRATE = .06625;
//    private static double ZEROTOTAL = 0;
//
//    private static int SIZEINDEX = 1;
//    private static int OFFSETINDEX = 1;
//
//    private static int TWODIGITS = 2;
//    private static int START = 0;
//    private static int EMPTY = 0;
//
//    private static int ZEROADDONS = 0;
//
//    private static int ONEADDON = 1;
//
//    private ObservableList<String> donuts;
//
//
//    /**
//     * This method displays the current order's pricing
//     * in the ordering basket for viewing.
//     */
//    @FXML
//    public void revealPricing(){
//        ArrayList<Order> list = AllOrders.allOrderR();
//        donuts = FXCollections.observableArrayList();
//        if(list.size() == EMPTY){
//            return;
//        }
//        subtotal.setText(round(list.get(list.size() - SIZEINDEX).getPrice()));
//        double taxAmt = list.get(list.size() - SIZEINDEX).getPrice() * TAXRATE;
//        tax.setText(round(taxAmt));
//        amountdue.setText(round(taxAmt + list.get(list.size() - SIZEINDEX).
//                getPrice()) + "");
//        ArrayList<Order> orders = AllOrders.allOrderR();
//        ArrayList<String> order = orders.get(orders.size() - SIZEINDEX).getMenuItems();
//        for(int i  = 0; i < order.size(); i++)
//            donuts.add(order.get(i));
//        items.setItems(donuts);
//    }
//
//
//    /**
//     * This method rounds a decimal number to two digits
//     * @param amount The value to round to two decimals
//     * @return The rounded double value
//     */
//    private String round(double amount){
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(TWODIGITS);
//        df.setMinimumFractionDigits(TWODIGITS);
//        String val = df.format(amount);
//        amount = Double.parseDouble(df.format(amount));
//        return val;
//    }
//
//    /**
//     * Checks if we can remove the item from the order.
//     * @param value a string containing the order in questin.
//     * @return a boolean if the order item can be removed.
//     */
//
//    public boolean checkRemove(String value){
//        if(value == null){
//            String errorMessage = "No items selected!";
//            Alert orderFailure = new Alert(Alert.AlertType.ERROR);
//            orderFailure.setContentText(errorMessage);
//            orderFailure.show();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Checks if a coffee item has a particular value.
//     * @param value a string containing the order in question.
//     * @return a boolean if the coffee order has any of the following values.
//     */
//    public boolean checkCoffee(String value){
//        return value.contains("Short") || value.contains("Tall") ||
//                value.contains("Grande") || value.contains("Venti");
//    }
//
//    /**
//     * Checks if a donut item has a particular flavor.
//     * @param value a string containing the order in question.
//     * @return a boolean if the donut order has any of the possible flavors.
//     */
//    public boolean checkFlavor(String value){
//        return value.contains("Strawberry") || value.contains("Vanilla")
//                || value.contains("Blueberry") || value.contains("Apple")
//                || value.contains("Grape") || value.contains("Passionfruit");
//    }
//
//    /**
//     * Counts the number of addons in a coffee order.
//     * @param value a string containing the coffee order
//     * @return a integer showcasing the number of coffee add ons.
//     */
//
//    public int numberOfAddons(String value){
//        int numberOfAddons = ZEROADDONS;
//        if(!value.contains("["))
//            numberOfAddons = ZEROADDONS;
//        else{
//            numberOfAddons += ONEADDON;
//            for(int i = 0 ; i < value.length(); i++){
//                if(value.charAt(i) == ',')
//                    numberOfAddons++;
//            }
//        }
//        return numberOfAddons;
//    }
//
//
//    /**
//     * Returns a coffee object, given the selected size and the order string
//     * @param value the order string to extract information from.
//     * @param sizeOfCoffee a string containing the coffee size
//     * @return a coffee object with the given parameters.
//     */
//    private Coffee getCoffeeObject(String value, String sizeOfCoffee){
//        int numberOfAddons = numberOfAddons(value);
//        Coffee tempCoffee = new Coffee(sizeOfCoffee);
//        ArrayList<String> addons = new ArrayList<String>();
//        for(int i = 0; i < numberOfAddons; i++){
//            addons.add("");
//        }
//        tempCoffee.addaddIn(addons);
//        return tempCoffee;
//    }
//
//    /**
//     * This method removes an item from the arraylist of
//     * all orders and also reflects the corresponding
//     * updated pricing
//     */
//    @FXML
//    protected void onRemove(){
//        String value = (String) items.getSelectionModel().getSelectedItem();
//        if(!checkRemove(value))
//            return;
//        ArrayList<Order> list = AllOrders.allOrderR();
//        list.get(list.size() - SIZEINDEX).getMenuItems().remove(value);
//        int quantity;
//        double amt = ZEROTOTAL;
//        int quantity1 = Integer.parseInt(value.substring(value.indexOf('(')
//                + OFFSETINDEX, value.indexOf(')')));
//        if(checkFlavor(value)){
//            quantity = quantity1;
//            Yeast yeast = new Yeast("Any");
//            amt = Double.parseDouble(subtotal.getText()) - yeast.itemPrice() * quantity;
//        }
//        if(value.contains("French") || value.contains("Original")
//                || value.contains("Powder")){
//            quantity = quantity1;
//            DonutHole hole = new DonutHole("Any");
//            amt = Double.parseDouble(subtotal.getText()) - hole.itemPrice() * quantity;
//        }
//        if(value.contains("Birthday Cake") || value.contains("Chocolate Cake")
//                || value.contains("Cheese Cake")){
//            quantity = quantity1;
//            Cake cake = new Cake("Any");
//            amt = Double.parseDouble(subtotal.getText()) - cake.itemPrice() * quantity;
//        }
//        if(checkCoffee(value)){
//            String sizeOfCoffee = value.substring(START,value.indexOf("("));
//            quantity = Integer.parseInt(value.substring(
//                    value.indexOf("(") + OFFSETINDEX, value.indexOf(")")));
//            Coffee tempCoffee = getCoffeeObject(value, sizeOfCoffee);
//            amt = Double.parseDouble(subtotal.getText()) -
//                    tempCoffee.itemPrice() * quantity;
//        }
//        list.get(list.size() - SIZEINDEX)
//                .setPrice(Double.parseDouble(round(amt)));
//        revealPricing();
//    }
//
//    /**
//     * This method initializes the pricing for the Ordering
//     * basket
//     */
//    public void initialize(){
//        revealPricing();
//    }
//
//    /**
//     * This method adds the current order to the
//     * order arraylist and increments the order number
//     */
//    @FXML
//    protected void onPlaceOrder(){
//        Order order = new Order(AllOrders.getUniqueNumber());
//        if(donuts.size() == EMPTY){
//            String errorMessage = "No items in basket!";
//            Alert orderFailure = new Alert(Alert.AlertType.ERROR);
//            orderFailure.setContentText(errorMessage);
//            orderFailure.show();
//            return;
//        }
//        for(int i = 0; i < donuts.size(); i++){
//            order.addItem(donuts.get(i));
//        }
//        AllOrders.addStoreOrder(order.getOrderNumber());
//        AllOrders.allOrder = new ArrayList<>();
//        AllOrders.runningTotal = 0;
//        DonutViewController.setTotal(ZEROTOTAL);
//        CoffeeViewController.setTotal(ZEROTOTAL);
//        AllOrders.incrementUnique();
//        String orderPlaced = "Order placed!";
//        Alert orderSuccess = new Alert(Alert.AlertType.INFORMATION);
//        orderSuccess.setContentText(orderPlaced);
//        orderSuccess.show();
//        reset();
//
//    }
//
//    /**
//     * This method resets all fields after the add to order
//     * button is pressed
//     */
//    private void reset(){
//        subtotal.setText("");
//        tax.setText("");
//        amountdue.setText("");
//        donuts = FXCollections.observableArrayList();
//        items.setItems(donuts);
//
//    }
//}
