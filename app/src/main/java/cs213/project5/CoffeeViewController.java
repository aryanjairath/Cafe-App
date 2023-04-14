//package cs213.project5;
//
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.image.ImageView;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
///**
// * This class is the controller for the coffee view
// * and manages the responses to any GUI interactions
// * for the coffee view stage.
// * @author Anis Chihoub, Aryan Jariath
// */
//public class CoffeeViewController {
//    ObservableList<String> donutList =
//            FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
//
//    private static int ONE = 1;
//
//
//    private static int TWO = 2;
//
//    private static int THREE = 3;
//
//    private static int FOUR = 4;
//
//    private static int FIVE = 5;
//
//    ObservableList<Integer> quantityList =
//            FXCollections.observableArrayList(ONE, TWO, THREE, FOUR, FIVE);
//
//
//    ObservableList<String> addOnList =
//            FXCollections.observableArrayList("Sweet Cream", "French Vanilla", "Irish Cream" +
//                    "Caramel", "Mocha");
//    ObservableList<String> coffee;
//
//    public static double total;
//    private static final int uniqueOrder = 0;
//    private static final int INITIALSELECT = 0;
//    private static final int DECIDIGITS = 2;
//
//
//    private Order order;
//
//    private static final int BEGININDEX = 0;
//    private static final int ZEROTOTAL = 0;
//    private static final int EMPTY = 0;
//
//
//    private static final int OFFSETINDEX = 1;
//    private static final int QUANTITYONE = 1;
//
//    private static final int FIRSTINDEX = 0;
//
//
//    @FXML
//    private ComboBox comboBox;
//
//    @FXML
//    private ComboBox quantitycomboBox;
//
//    @FXML
//    private ListView flavors;
//
//    @FXML
//    private ListView result;
//
//    @FXML
//    private ImageView donutImage;
//
//    @FXML
//    private TextField runningTotal;
//
//    @FXML
//    private TextField orders;
//
//
//    @FXML
//    private CheckBox sweetCream;
//
//    @FXML
//    private CheckBox frenchVanilla;
//
//    @FXML
//    private CheckBox irishCream;
//
//
//    @FXML
//    private CheckBox caramelBox;
//
//    @FXML
//    private CheckBox mochaBox;
//
//    /**
//     * Constructor for the CoffeeViewController, which initializes several
//     * important instance variables.
//     */
//    public CoffeeViewController(){
//        comboBox = new ComboBox();
//        quantitycomboBox = new ComboBox();
//        result = new ListView();
//        total = ZEROTOTAL;
//        coffee = FXCollections.observableArrayList();
//        order = new Order(uniqueOrder);
//        initialize();
//    }
//
//    /**
//     * Initializes the UI with given values.
//     */
//    @FXML
//    protected void initialize(){
//        comboBox.setValue("Coffee Size");
//        comboBox.setItems(donutList);
//        comboBox.setValue(donutList.get(FIRSTINDEX));
//
//        quantitycomboBox.setValue(QUANTITYONE);
//        quantitycomboBox.setItems(quantityList);
//    }
//
//    /**
//     * Gets the addons from the check boxes in the ui.
//     * @return an array list containing which addons were selected.
//     */
//
//    private ArrayList<String> getAddons(){
//        ArrayList<String> addList = new ArrayList<>();
//        if(sweetCream.isSelected()){
//            addList.add("Sweet Cream");
//        }
//
//        if(frenchVanilla.isSelected()){
//            addList.add("French Vanilla");
//
//        }
//
//        if(mochaBox.isSelected()){
//            addList.add("Mocha");
//        }
//
//        if(irishCream.isSelected()){
//            addList.add("Irish Cream");
//
//        }
//
//        if(caramelBox.isSelected()){
//            addList.add("Caramel");
//        }
//
//        return addList;
//    }
//
//
//    /**
//     * Checks if a given order can be added to the orders list
//     * @return a boolean indicating if the above is possible.
//     */
//
//    private boolean checkOrder(){
//        if(((String)comboBox.getSelectionModel().getSelectedItem()).equals(
//                "Coffee Size")){
//            return false;
//        }
//        return true;
//    }
//    /**
//     * Adds a coffee order, given parameters from the UI.
//     */
//
//    @FXML
//    protected void addCoffee(){
//        if(!checkOrder()){
//            String errorMessage = "Must select coffee size!";
//            Alert coffeeFailure = new Alert(Alert.AlertType.ERROR);
//            coffeeFailure.setContentText(errorMessage);
//            coffeeFailure.show();
//            return;
//        }
//        String size = (String)comboBox.getSelectionModel().getSelectedItem();
//        Coffee coffeeOrder = new Coffee(size);
//        ArrayList<String> addOns = getAddons();
//        coffeeOrder.addaddIn(addOns);
//        int quantity = (int)quantitycomboBox.getSelectionModel().getSelectedItem();
//        if(addOns.size() == EMPTY)
//            coffee.add(size + "(" + quantity + ").");
//        else
//            coffee.add(size + "(" + quantity + ")" + " Addons: " +  addOns.toString()+ ".");
//        result.setItems(coffee);
//        total += coffeeOrder.itemPrice()*quantity;
//        round();
//    }
//
//    /**
//     * This method checks if coffee object can be removed or not
//     * @return A boolean representing if coffee can be removed
//     */
//    private boolean canRemoveCoffee(){
//        if((String) result.getSelectionModel().getSelectedItem() == null){
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * This method sets a certain total for a coffee order object
//     * @param tot A double representing the total value for
//     * a particular coffee order
//     */
//    public static void setTotal(double tot){
//        total = tot;
//    }
//
//
//    /**
//     * Removes a coffee order, given parameters from the UI.
//     */
//    @FXML
//    protected void removeCoffee(){
//        if(!canRemoveCoffee()){
//            String errorMessage = "Must select a valid coffee!";
//            Alert coffeeFailure = new Alert(Alert.AlertType.ERROR);
//            coffeeFailure.setContentText(errorMessage);
//            coffeeFailure.show();
//            return;
//        }
//
//        String value = (String)result.getSelectionModel().getSelectedItem();
//        String sizeOfCoffee = value.substring(BEGININDEX,value.indexOf("("));
//        int quantity = Integer.parseInt(value.substring(
//                value.indexOf("(") + OFFSETINDEX, value.indexOf(")")));
//        ArrayList<String> addons = new ArrayList<String>();
//        Coffee tempCoffee = new Coffee(sizeOfCoffee);
//        if(value.indexOf("[") != BEGININDEX - OFFSETINDEX){
//            String addOns = value.substring(value.indexOf("[") + OFFSETINDEX,
//                    value.indexOf("]"));
//            String[] addOnFromOrder = addOns.split(",");
//            for(int i = 0; i < addOnFromOrder.length; i++){
//                addons.add(addOnFromOrder[i]);
//            }
//        }
//        tempCoffee.addaddIn(addons);
//        total -= (tempCoffee.itemPrice()) * quantity;
//        round();
//        coffee.remove(value);
//    }
//
//
//    /**
//     * Adds an order, based on the UI parameters.
//     */
//    @FXML
//    protected void addOrder(){
//        if(!checkOrder()){
//            String errorMessage = "Must select a valid coffee!";
//            Alert coffeeFailure = new Alert(Alert.AlertType.ERROR);
//            coffeeFailure.setContentText(errorMessage);
//            coffeeFailure.show();
//            return;
//        }
//        if(coffee.size() == BEGININDEX) {
//            String errorMessage = "No Coffee to add!";
//            Alert coffeeFailure = new Alert(Alert.AlertType.ERROR);
//            coffeeFailure.setContentText(errorMessage);
//            coffeeFailure.show();
//            return;
//        }
//        for(int i = 0; i < coffee.size(); i++) {
//            String type = coffee.get(i);
//            order.addItem(type);
//        }
//        AllOrders.runningTotal += total;
//        order.setPrice(AllOrders.runningTotal);
//        AllOrders.addOrder(order, uniqueOrder);
//        reset();
//        String errorMessage = "Order added!";
//        Alert coffeeFailure = new Alert(Alert.AlertType.CONFIRMATION);
//        coffeeFailure.setContentText(errorMessage);
//        coffeeFailure.show();
//    }
//
//    /**
//     * Resets parameters in the UI after an order is submitted.
//     */
//    private void reset(){
//        initialize();
//        order = new Order(uniqueOrder);
//        coffee = FXCollections.observableArrayList();
//        result.setItems(coffee);
//        quantitycomboBox.setValue(QUANTITYONE);
//        runningTotal.setText("");
//        result.setItems(coffee);
//        irishCream.setSelected(false);
//        caramelBox.setSelected(false);
//        frenchVanilla.setSelected(false);
//        mochaBox.setSelected(false);
//        sweetCream.setSelected(false);
//    }
//
//    /**
//     * Rounds a decimal number to two digits.
//     */
//    private void round(){
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(DECIDIGITS);
//        df.setMinimumFractionDigits(DECIDIGITS);
//        total = Double.parseDouble(df.format(total));
//        runningTotal.setText(df.format(total));
//    }
//}