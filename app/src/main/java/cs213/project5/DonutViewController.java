//package cs213.project5;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//
///**
// * This class is the controller for the donut view
// * and manages the responses to any GUI interactions
// * for the donut view stage.
// * @author Anis Chihoub, Aryan Jariath
// */
//public class DonutViewController {
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
//    private static int SIX = 6;
//
//    private static int SEVEN = 7;
//
//    private static int EIGHT = 8;
//
//    private static int NINE = 9;
//
//    private static int TEN = 10;
//
//
//
//
//
//    ObservableList<String> donutList =
//            FXCollections.observableArrayList("Yeast Donut", "Cake Donut", "Hole Donut");
//    ObservableList<Integer> quantityList =
//            FXCollections.observableArrayList(ONE, TWO, THREE, FOUR, FIVE,
//                    SIX, SEVEN, EIGHT, NINE, TEN);
//    ObservableList<String> yeastFlavors =
//            FXCollections.observableArrayList("Strawberry", "Vanilla", "Blueberry",
//                    "Apple", "Grape","Passionfruit");
//    ObservableList<String> cakeFlavors =
//            FXCollections.observableArrayList("Birthday Cake", "Chocolate Cake",
//                    "Cheese Cake");
//    ObservableList<String> donutHoles =
//            FXCollections.observableArrayList("French", "Original",
//                    "Powder");
//    ObservableList<String> donuts;
//    private static double total;
//    private final int ZEROTOTAL = 0;
//    private final int INITIALSELECT = 1;
//    private final int OFFSETTEN = 10;
//    private final int OFFSETNINE = 9;
//    private final int OFFSETTWO = 2;
//    private final int TWODIGITS = 2;
//    private final int EMPTY = 0;
//
//    private final int OFFSETONE = 1;
//
//    private final int STARTINDEX = 0;
//
//    private int uniqueOrder = 0;
//    private Order order;
//    @FXML
//    private ComboBox comboBox;
//    @FXML
//    private ComboBox quantitycomboBox;
//    @FXML
//    private ListView flavors;
//    @FXML
//    private ListView result;
//    @FXML
//    private ImageView donutImage;
//    @FXML
//    private TextField runningTotal;
//
//
//    /**
//     * Constructor for the donut view controller which sets the combo
//     * boxes and a couple pre-defined images
//     */
//    public DonutViewController(){
//        comboBox = new ComboBox();
//        quantitycomboBox = new ComboBox();
//        flavors = new ListView();
//        total = ZEROTOTAL;
//        donuts = FXCollections.observableArrayList();
//        order = new Order(uniqueOrder);
//        donutImage = new ImageView();
//        initialize();
//    }
//
//    /**
//     * Performs tasks and sets pre-defined values as soon
//     * as the view is opened.
//     */
//    @FXML
//    protected void initialize(){
//        comboBox.setValue("Yeast Donut");
//        comboBox.setItems(donutList);
//        quantitycomboBox.setValue(INITIALSELECT);
//        quantitycomboBox.setItems(quantityList);
//        flavors.setItems(yeastFlavors);
//    }
//
//    /**
//     * Finds the selected value and a corresponding image for it
//     * @throws IOException Thrown in case there is no image found
//     * for the given selected value
//     */
//    @FXML
//    protected void selectedValue() throws IOException {
//        if(comboBox.getValue().equals("Yeast Donut")){
//            flavors.setItems(yeastFlavors);
//            InputStream inputStream =
//                    new FileInputStream("src/main/resources/projectfour/yeast.jpg");
//            Image img = new Image(inputStream);
//            donutImage.setImage(img);
//        }
//        if(comboBox.getValue().equals("Cake Donut")) {
//            flavors.setItems(cakeFlavors);
//            InputStream inputStream =
//                    new FileInputStream("src/main/resources/projectfour/cake.jpg");
//            Image img = new Image(inputStream);
//            donutImage.setImage(img);
//        }
//        if(comboBox.getValue().equals("Hole Donut")) {
//            flavors.setItems(donutHoles);
//            InputStream inputStream =
//                    new FileInputStream("src/main/resources/projectfour/holes.jpg");
//            Image img = new Image(inputStream);
//            donutImage.setImage(img);
//        }
//    }
//
//    /**
//     * Performs actions when the add donut button is pressed
//     * to add donuts to temporary list view.
//     */
//    @FXML
//    protected void addDonut(){
//        String donutType = (String)comboBox.getSelectionModel().
//                getSelectedItem();
//        String flavor = (String)flavors.getSelectionModel().
//                getSelectedItem();
//        if(flavor == null){
//            String errorMessage = "Must select a donut flavor!";
//            Alert donutFailure = new Alert(Alert.AlertType.ERROR);
//            donutFailure.setContentText(errorMessage);
//            donutFailure.show();
//            return;
//        }
//        int quantity = (int) quantitycomboBox.getSelectionModel().
//                getSelectedItem();
//        if(donutType.equals("Yeast Donut")){
//            Yeast yeast = new Yeast(flavor);
//            total += yeast.itemPrice() * quantity;
//        }
//        if(donutType.equals("Cake Donut")){
//            Cake cake = new Cake(flavor);
//            total += cake.itemPrice() * quantity;
//        }
//        if(donutType.equals("Hole Donut")){
//            DonutHole hole = new DonutHole(flavor);
//            total += hole.itemPrice() * quantity;
//        }
//        String output = "";
//        if(flavor != null)
//            output = (flavor + "(" + quantity + ")");
//        else
//            return;
//        round();
//        donuts.add(output);
//        result.setItems(donuts);
//    }
//
//
//    /**
//     * Actions performed when the remove button is pressed
//     * in the GUI.
//     */
//    @FXML
//    protected void onRemove(){
//        String value = (String)result.getSelectionModel().
//                getSelectedItem();
//        if(value == null) {
//            String errorMessage = "No Donut Selected!";
//            Alert donutFailure = new Alert(Alert.AlertType.ERROR);
//            donutFailure.setContentText(errorMessage);
//            donutFailure.show();
//            return;
//        }
//        donuts.remove(value);
//        int quantity;
//        if(value.contains("Strawberry") || value.contains("Vanilla")
//                || value.contains("Blueberry") || value.contains("Apple")
//                || value.contains("Grape") || value.contains("Passionfruit")){
//            quantity = Integer.parseInt(value.substring(value.length() -
//                    OFFSETTWO,value.length() - OFFSETONE));
//            Yeast yeast = new Yeast("Any");
//            total -= yeast.itemPrice() * quantity;
//        }
//        if(value.contains("French") || value.contains("Original")
//                || value.contains("Powder")){
//            quantity = Integer.parseInt(value.substring(value.length() -
//                    OFFSETTWO,value.length() - OFFSETONE));
//            DonutHole hole = new DonutHole("Any");
//            total -= hole.itemPrice() * quantity;
//        }
//        if(value.contains("Birthday Cake") || value.contains("Chocolate Cake")
//                || value.contains("Cheese Cake")){
//            quantity = Integer.parseInt(value.substring(value.length() -
//                    OFFSETTWO,value.length() - OFFSETONE));
//            Cake cake = new Cake("Any");
//            total -= cake.itemPrice() * quantity;
//        }
//        round();
//    }
//
//
//    /**
//     * Rounds a given decimal to two decimal places
//     */
//    private void round(){
//        DecimalFormat df = new DecimalFormat();
//        df.setMinimumFractionDigits(TWODIGITS);
//        df.setMaximumFractionDigits(TWODIGITS);
//        runningTotal.setText(df.format(total));
//        total = Double.parseDouble(df.format(total));
//    }
//
//
//    /**
//     * This method sets a certain total for a donut order object
//     * @param tot A double representing the total value for
//     * a particular donut order
//     */
//    public static void setTotal(double tot){
//        total = tot;
//    }
//
//
//    /**
//     * This method performs actions when the add to order button
//     * is pressed; checks if donut can be added at all.
//     */
//    @FXML
//    protected void addOrder(){
//        if(donuts.size() == EMPTY){
//            String errorMessage = "No donuts are selected!";
//            Alert donutFailure = new Alert(Alert.AlertType.ERROR);
//            donutFailure.setContentText(errorMessage);
//            donutFailure.show();
//            return;
//        }
//        for(int i = 0; i < donuts.size(); i++) {
//            String type = donuts.get(i);
//            order.addItem(type);
//        }
//        String orderPlaced = "Donuts added to order!";
//        Alert orderSuccess = new Alert(Alert.AlertType.INFORMATION);
//        orderSuccess.setContentText(orderPlaced);
//        orderSuccess.show();
//        AllOrders.runningTotal += total;
//        order.setPrice(AllOrders.runningTotal);
//        AllOrders.addOrder(order, uniqueOrder);
//        reset();
//    }
//
//    /**
//     * This method resets all fields after the add to order
//     * button is pressed
//     */
//    private void reset(){
//        initialize();
//        order = new Order(uniqueOrder);
//        donuts = FXCollections.observableArrayList();
//        result.setItems(donuts);
//        quantitycomboBox.setValue(INITIALSELECT);
//        runningTotal.setText("");
//    }
//}
