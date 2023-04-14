//package cs213.project5;
//
//
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.ListView;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
///**
// * This class is the controller for the order menu
// * and manages the responses to any GUI interactions
// * for the order stage.
// * @author Anis Chihoub, Aryan Jariath
// */
//public class OrderMenuController {
//
//    @FXML
//    private ListView resultView;
//
//    @FXML
//    private TextField runningTotal;
//
//    private static int SIZEINDEX = 1;
//
//    private ObservableList<String> orderList;
//
//    private static int BEGININDEX = 0;
//    private static int EMPTY = 0;
//
//
//    private static int ENDINDEX = 1;
//
//
//    /**
//     * Constructor for Order Menu.
//     *
//     */
//    public void initialize(){
//        showAllOrders();
//    }
//
//
//    /**
//     * This method shows all the orders that were placed
//     * when the button is pressed.
//     */
//    @FXML
//    public void showAllOrders(){
//
//        ArrayList<Order> orderList = AllOrders.allStoreOrders();
////        if(orderList.size() == BEGININDEX){
////            String errorMessage = "No Orders to load!";
////            Alert coffeeFailure = new Alert(Alert.AlertType.ERROR);
////            coffeeFailure.setContentText(errorMessage);
////            coffeeFailure.show();
////            return;
////        }
//
//        if(orderList.size() != EMPTY){
//            ArrayList<String> list = orderList.get(orderList.size() - SIZEINDEX).getMenuItems();
//            this.orderList = FXCollections.observableArrayList();
//
//            for(int i = 0; i < orderList.size(); i++){
//                this.orderList.add(orderList.get(i).toString());
//            }
//        }else{
//            ArrayList<String> list = new ArrayList<String>();
//        }
//        this.resultView.setItems(this.orderList);
//
//    }
//
//
//    /**
//     * Rounds a given decimal to two decimal places
//     * @param amount a double representing a price.
//     * @return a rounded price.
//     */
//    private double round(double amount){
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(2);
//        df.setMinimumFractionDigits(2);
//        amount = Double.parseDouble(df.format(amount));
//        return amount;
//    }
//
//
//    /**
//     * Removes an item from the ordering list, given access to various element from the UI.
//     */
//    @FXML
//    private void removeItem(){
//        String order = ((String) this.resultView.getSelectionModel()
//                .getSelectedItem());
//        ArrayList<Order> orderList = AllOrders.allStoreOrders();
//        if(orderList.size() == BEGININDEX || this.orderList.size() == BEGININDEX){
//            String errorMessage = "No orders to remove!";
//            Alert coffeeFailure = new Alert(Alert.AlertType.ERROR);
//            coffeeFailure.setContentText(errorMessage);
//            coffeeFailure.show();
//            return;
//        }
//
//        if(order == null){
//            String errorMessage = "No orders to remove!";
//            Alert coffeeFailure = new Alert(Alert.AlertType.ERROR);
//            coffeeFailure.setContentText(errorMessage);
//            coffeeFailure.show();
//            return;
//        }
//
//        int orderNumber = Integer.parseInt(((String) this.resultView.getSelectionModel()
//                .getSelectedItem()).substring(order.indexOf(":") + ENDINDEX + ENDINDEX,
//                order.indexOf(":") + ENDINDEX + ENDINDEX + ENDINDEX)) - ENDINDEX;
//
//        AllOrders.removeItem(order);
//        for(int i = 0; i < this.orderList.size(); i++){
//            if(this.orderList.get(i).equals(order)){
//                this.orderList.remove(i);
//            }
//        }
//        this.resultView.setItems(this.orderList);
//        String errorMessage = "Orders removed!";
//        Alert coffeeFailure = new Alert(Alert.AlertType.CONFIRMATION);
//        coffeeFailure.setContentText(errorMessage);
//        coffeeFailure.show();
//    }
//
//
//    /**
//     * Writes orders to a text file
//     * @throws IOException in case the file is not found
//     */
//    @FXML
//    public void exportToTextFile() throws IOException {
//        ArrayList<Order> orderList = AllOrders.allStoreOrders();
//
//        if(orderList.size() == BEGININDEX || this.orderList.size() == BEGININDEX){
//            String errorMessage = "No Orders to save!";
//            Alert coffeeFailure = new Alert(Alert.AlertType.ERROR);
//            coffeeFailure.setContentText(errorMessage);
//            coffeeFailure.show();
//            return;
//        }
//        FileWriter writer = new FileWriter("output.txt");
//        for(String str: this.orderList) {
//            writer.write(str + System.lineSeparator());
//        }
//
//        String errorMessage = "Orders added to output.txt in main directory!";
//        Alert coffeeFailure = new Alert(Alert.AlertType.CONFIRMATION);
//        coffeeFailure.setContentText(errorMessage);
//        coffeeFailure.show();
//        writer.close();
//    }
//
//
//
//
//
//
//}
