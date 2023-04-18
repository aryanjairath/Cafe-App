package cs213.project5;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CoffeeView extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private CheckBox sweetCream;

    private CheckBox frenchVanilla;

    private CheckBox irishCream;

    private CheckBox caramelBox;

    private CheckBox mochaBox;

    private ArrayAdapter<String> adapter;

    private static ArrayList<String> orders = new ArrayList<>();

    private Spinner quantSpinner;

    private Spinner sizeSpinner;

    private TextView totalPrice;

    private TextView subTotal;

    private ArrayList<String> coffeeOrders;

    private ListView currentOrders;

    public static double total;

    private static double EMPTY = 0;

    private static int ZEROINDEX = 0;

    private static int DECIMALS = 2;

    private static int ONE = 1;

    private ArrayAdapter<String> adapterCoffee;

    private ArrayList<Double> pricesOfOrders;

    private Coffee calculatorCoffee;

    private final double SHORT = 1.89;
    private final double TALL = 2.29;
    private final double GRANDE = 2.69;
    private final double VENTI = 3.09;
    private final double ADDONPRICE = 0.30;

    private double tempPrice;

    private double currQuant;

    private String currSize;
    /**
     * Creates the view for the application
     * @param savedInstanceState a bundle object with the state of the view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_layout);
        sweetCream = findViewById(R.id.swbox);
        frenchVanilla = findViewById(R.id.fVanilla);
        irishCream = findViewById(R.id.icream);
        caramelBox = findViewById(R.id.caramel);
        mochaBox = findViewById(R.id.mochaBox);
        quantSpinner = findViewById(R.id.spinnerQuantity);
        sizeSpinner = findViewById(R.id.spinnerSize);
        currentOrders = findViewById(R.id.coffeeList);
        coffeeOrders = new ArrayList<String>();
        pricesOfOrders = new ArrayList<Double>();
        totalPrice = findViewById(R.id.textView6);
        subTotal = findViewById(R.id.textViewSub);
        setCheckBoxes();
        setCheckBoxesAgain();
//        subTotal.setText(tempPrice + "");
        total = EMPTY;
        currQuant = ONE;
        loadView();
        currentOrders.setOnItemClickListener(this);
    }

    /**
     * Sets the value of the checkboxes
     */
    private void setCheckBoxes(){
        sweetCream.setOnCheckedChangeListener(new
                                                      CompoundButton.OnCheckedChangeListener() {
                                                          /**
                                                           * Checks if the box for the addon has been changed
                                                           * @param compoundButton the compound button (checkbox) to change
                                                           * @param b a boolean for if the button is checked
                                                           */
                                                          @Override
                                                          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                              boolean sweetPresent = compoundButton.isChecked();
                                                              if(sweetPresent){
                                                                  tempPrice +=  ADDONPRICE;
                                                              }else{
                                                                  tempPrice -= ADDONPRICE;
                                                              }
                                                              subTotal.setText(round(tempPrice));
                                                          }
                                                      });
        irishCream.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    /**
                     * Checks if the box for the addon has been changed
                     * @param compoundButton the compound button (checkbox) to change
                     * @param b a boolean for if the button is checked
                     */
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        boolean sweetPresent = compoundButton.isChecked();
                        if(sweetPresent){
                            tempPrice +=  ADDONPRICE;
                        }else{
                            tempPrice -= ADDONPRICE;
                        }
                        subTotal.setText(round(tempPrice));
                    }
                });
        setVanilla();
    }

    /**
     * Sets the listener for the vanilla checkbox
     */
    private void setVanilla(){
        frenchVanilla.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    /**
                     * Checks if the box for the addon has been changed
                     * @param compoundButton the compound button (checkbox) to change
                     * @param b a boolean for if the button is checked
                     */
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        boolean sweetPresent = compoundButton.isChecked();
                        if(sweetPresent){
                            tempPrice +=  ADDONPRICE;
                        }else{
                            tempPrice -= ADDONPRICE;
                        }
                        subTotal.setText(round(tempPrice));
                    }
                });
    }

    private void setCheckBoxesAgain() {
        mochaBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks if the box for the addon has been changed
             * @param compoundButton the compound button (checkbox) to change
             * @param b a boolean for if the button is checked
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean sweetPresent = compoundButton.isChecked();
                if (sweetPresent) {
                    tempPrice += ADDONPRICE;
                } else {
                    tempPrice -= ADDONPRICE;
                }
                subTotal.setText(round(tempPrice));

            }
        });
        caramelBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks if the box for the addon has been changed
             * @param compoundButton the compound button (checkbox) to change
             * @param b a boolean for if the button is checked
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean sweetPresent = compoundButton.isChecked();
                if (sweetPresent) {
                    tempPrice += ADDONPRICE;
                } else {
                    tempPrice -= ADDONPRICE;
                }
                subTotal.setText(round(tempPrice));
            }
        });
    }



    /**
     * Sets the array of orders with new elements
     * @param orders an array list with strings containing orders
     */
    public void setOrders(ArrayList<String> orders){
        this.orders = orders;
    }

    /**
     * Loads elements of the coffee view with selected elements.
     */
    private void loadView(){
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.Coffee_Quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterTwo =
                ArrayAdapter.createFromResource(this, R.array.Sizes, android.R.layout.simple_spinner_item);
        adapterCoffee = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                coffeeOrders);
        sizeSpinner.setAdapter(adapterTwo);
        quantSpinner.setAdapter(adapter);
        currentOrders.setAdapter(adapterCoffee);
        calculatorCoffee = new Coffee("Short");
        subTotal.setText(round(calculatorCoffee.itemPrice())+ "");
        total = Double.parseDouble(round(calculatorCoffee.itemPrice()));
        tempPrice = total;
        currSize = "Short";
        total = EMPTY;
        totalPrice.setText(total + "");
        setSpinnerOne();
        setSpinnerTwo();
    }

    /**
     *     Sets the size spinner's item selection listener
     */
    public void setSpinnerOne(){
        /**
         * async method to handle the listner
         */
        quantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Method for spinner reaction to a change in item
             * @param parentView the parent view object we want to modify
             * @param selectedItemView the selected item from the spinner
             * @param position the position of the selected item in the spinner
             * @param id element id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                double quantity =
                        Integer.parseInt(parentView.getSelectedItem().toString());
                tempPrice = tempPrice * ( quantity / currQuant);
                currQuant = quantity;
                subTotal.setText(round(tempPrice));
            }

            /**
             * Blank method for when nothing is selected.
             * @param parentView the parent view object we wish to modify.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    /**
     * Sets the size spinner's item selection listener
     */
    public void setSpinnerTwo(){
        /**
         * async method to handle the listner
         */
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Method for spinner reaction to a change in item
             * @param parentView the parent view object we want to modify
             * @param selectedItemView the selected item from the spinner
             * @param position the position of the selected item in the spinner
             * @param id element id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                String size = parentView.getSelectedItem().toString();
                double originalPrice = getSizePrice(currSize);
                double newPrice = getSizePrice(size);
                tempPrice = tempPrice * ( newPrice / originalPrice);
                subTotal.setText(round(tempPrice));
                currSize = size;
            }
            /**
             * Blank method for when nothing is selected.
             * @param parentView the parent view object we wish to modify.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    /**
     * Gets the selected addons from the given check boxes.
     * @return an array list of strings with addons.
     */

    private ArrayList<String> getAddons(){
        ArrayList<String> addList = new ArrayList<>();
        if(sweetCream.isChecked()){
            addList.add("Sweet Cream");
        }

        if(frenchVanilla.isChecked()){
            addList.add("French Vanilla");

        }

        if(mochaBox.isChecked()){
            addList.add("Mocha");
        }

        if(irishCream.isChecked()){
            addList.add("Irish Cream");

        }

        if(caramelBox.isChecked()){
            addList.add("Caramel");
        }

        return addList;
    }

    /**
     * Adds a coffee item to the current order
     * @param view a view object that we are currently looking at
     */

    public void onAdd(View view){
        int quantityOfCoffee = Integer.parseInt(
                quantSpinner.getSelectedItem().toString());
        String size = sizeSpinner.getSelectedItem().toString();
        Coffee orderedCoffee = new Coffee(size);
        ArrayList<String> addOnCust = getAddons();
        orderedCoffee.addaddIn(addOnCust);
        if(addOnCust.size() == EMPTY)
            coffeeOrders.add(size + "(" + quantityOfCoffee + ").");
        else
            coffeeOrders.add(size + "(" + quantityOfCoffee + ")" +
                    " Addons: " +  addOnCust.toString()+ ".");
        pricesOfOrders.add(Double.parseDouble(round(
                orderedCoffee.itemPrice() * quantityOfCoffee)));
        total += Double.parseDouble(round(
                orderedCoffee.itemPrice() * quantityOfCoffee));
        calculatorCoffee = new Coffee(size);
        ArrayList<String> addons = getAddons();
        calculatorCoffee.addaddIn(addons);
        tempPrice = Double.parseDouble(round(
                calculatorCoffee.itemPrice() * quantityOfCoffee));
        totalPrice.setText(round(total) + "");
        subTotal.setText(tempPrice + "");
        adapterCoffee.notifyDataSetChanged();
    }

    /**
     * Gets the price of each size.
     * @param size the size of the coffee as a string
     * @return the price per size as a double.
     */
    private double getSizePrice(String size){
        double price = EMPTY;
        switch (size){
            case "Short":
                price = SHORT;
                break;
            case "Tall":
                price = TALL;
                break;
            case "Grande":
                price = GRANDE;
                break;
            case "Venti":
                price = VENTI;
                break;
        }
        return price;
    }


    /**
     * This is the method you must implement when you write implements AdapterView.OnItemClickListener
     * in the class heading.
     * This is the event handler for the onItemClick event.
     * @param adapterView the adapter view we want
     * @param view the current view object
     * @param i the index of the element clicked
     * @param l a long for internal android computation
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove the selected item?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        String item = adapterView.getAdapter().getItem(i).toString();
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            /**
             * Method to handle clicked objects.
             * @param dialog the dialog interface object to send a message
             * @param which an integer representing an id
             */
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Item Removed!", Toast.LENGTH_LONG).show();
                coffeeOrders.remove(adapterView.getAdapter().getItem(i).toString());
                onRemove(item,i);
                adapterCoffee.notifyDataSetChanged();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            /**
             * Method to handle clicked objects.
             * @param dialog the dialog interface object to send a message
             * @param which an integer representing an id
             */
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Item not removed!", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * This method rounds a decimal number to two digits
     * @param amount The value to round to two decimals
     * @return The rounded double value
     */
    private String round(double amount){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(DECIMALS);
        df.setMinimumFractionDigits(DECIMALS);
        String val = df.format(amount);
        amount = Double.parseDouble(df.format(amount));
        return val;
    }

    /**
     * Removes the order from the interal array lsits of orders and prices
     * @param item the item to be remove as  a string
     * @param index the index from the adapter view.
     */
    protected void onRemove(String item,int index){
        total -= pricesOfOrders.get(index);
        totalPrice.setText(round(total));
        pricesOfOrders.remove(index);
    }



    /**
     * Adds the orders to all orders
     * @param view a view object that we are looking at.
     */
    public void onAddOrder(View view){
        if(coffeeOrders.size() == EMPTY){
            Toast.makeText(getApplicationContext(), "No Coffee Orders to submit", Toast.LENGTH_SHORT).show();
            return;
        }
        Order coffeeOrderToAdd = new Order(
                AllOrders.getUniqueNumber());
        for(int i = 0; i < coffeeOrders.size(); i++){
            coffeeOrderToAdd.addItem(coffeeOrders.get(i));
        }
        AllOrders.runningTotal += total;
        coffeeOrderToAdd.setPrice(total);
        AllOrders.addOrder(coffeeOrderToAdd, ZEROINDEX);
        reset();
        Intent intent = new Intent(this,
                MainActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(getApplicationContext(),
                "Order added to basket", Toast.LENGTH_SHORT).show();
    }



    /**
     * Unchecks all boxes on the ui.
     */
    private void uncheckBoxes(){

        if(sweetCream.isChecked()){
            sweetCream.toggle();
        }

        if(frenchVanilla.isChecked()){
            frenchVanilla.toggle();

        }

        if(mochaBox.isChecked()){
            mochaBox.toggle();
        }

        if(irishCream.isChecked()){
            irishCream.toggle();
        }

        if(caramelBox.isChecked()){
            caramelBox.toggle();
        }

    }

    /**
     * Resets the coffee view on exit.
     */
    private void reset(){
        quantSpinner.setSelection((int)EMPTY);
        sizeSpinner.setSelection((int)EMPTY);
        coffeeOrders.clear();
        adapterCoffee.notifyDataSetChanged();
        pricesOfOrders.clear();
        uncheckBoxes();
        total = 0.0;
        tempPrice = 0.0;
        calculatorCoffee = new Coffee("Short");
        tempPrice = calculatorCoffee.itemPrice();
        totalPrice.setText(round(total));
        subTotal.setText(round(tempPrice));
    }

    /**
     * Sets the total price in the class
     * @param tot a double that we wish to set to the double
     */
    public static void setTotal(double tot) {
        total = tot;
    }

}