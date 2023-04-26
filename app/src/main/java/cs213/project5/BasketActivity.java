package cs213.project5;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class is the basket view where all items
 * in the shopping bag can be viewed and edited
 * @author Aryan Jairath, Anis Chihoub
 */
public class BasketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final int ZEROADDONS = 0;
    private static final int TWODIGITS = 2;

    private static final int ONEADDON = 1;
    private ListView listview;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> alldonuts;
    private TextView subtotal;
    private TextView tax;
    private TextView due;
    private static double TAXRATE = .06625;

    private static int SIZEINDEX = 1;
    private static int ZERO = 0;
    private static int OFFSETINDEX = 1;
    private static final int OFFSETTWO = 2;
    private static final int OFFSETONE = 1;


    /**
     * Initial setup for the Views and the adapter for the ListView
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alldonuts = new ArrayList<>();
        setContentView(R.layout.basket_layout);
        ArrayList<Order> orders = AllOrders.allOrderR();
        ArrayList<String> order;
        if (orders.size() != ZERO) {
            order = orders.get(orders.size() - SIZEINDEX).getMenuItems();
            for (int i = 0; i < order.size(); i++)
                alldonuts.add(order.get(i));
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, alldonuts);
        listview = findViewById(R.id.listvie);
        subtotal = findViewById(R.id.subtotal);
        tax = findViewById(R.id.tax);
        due = findViewById(R.id.totalamount);
        listview.setOnItemClickListener(this); //register the listener for an OnItemClick event.
        listview.setAdapter(adapter);
        ArrayList<Order> list = AllOrders.allOrderR();
        if(list.size() != ZERO)
            recalculate();
    }

    /**
     * This method recalculates the pricing for the
     * basket after getting the items
     */
    private void recalculate() {
        int quantity;
        double total = ZERO;
        for(int i = 0; i < alldonuts.size(); i++) {
            String value = alldonuts.get(i);
                if (value.contains("Strawberry") || (value.contains("Vanilla") &&
                        !value.contains("French Vanilla")) || value.contains("Blueberry") ||
                        value.contains("Apple") || value.contains("Grape") ||
                        value.contains("Passionfruit")) {
                    quantity = Integer.parseInt(value.substring(value.length() -
                            OFFSETTWO, value.length() - OFFSETONE));
                    Yeast yeast = new Yeast("Any");
                    total += yeast.itemPrice() * quantity;
                }
                else if ((value.contains("French") && !value.contains("French Vanilla"))
                        || value.contains("Original") || value.contains("Powder")) {
                    quantity = Integer.parseInt(value.substring(value.length() -
                            OFFSETTWO, value.length() - OFFSETONE));
                    DonutHole hole = new DonutHole("Any");
                    total += hole.itemPrice() * quantity;
                }
                else if (value.contains("Birthday Cake") || value.contains("Chocolate Cake")
                        || value.contains("Cheese Cake")) {
                    quantity = Integer.parseInt(value.substring(value.length() -
                            OFFSETTWO, value.length() - OFFSETONE));
                    Cake cake = new Cake("Any");
                    total += cake.itemPrice() * quantity;
                }
                else{
                String sizeOfCoffee = value.substring(ZERO, value.indexOf("("));
                quantity = Integer.parseInt(value.substring(
                        value.indexOf("(") + OFFSETINDEX, value.indexOf(")")));
                Coffee tempCoffee = getCoffeeObject(value, sizeOfCoffee);
                total += tempCoffee.itemPrice() * quantity;
            }
        }
        subtotal.setText(round(total)+"");
        double taxAmt = total * TAXRATE;
        tax.setText(round(taxAmt));
        due.setText(round(taxAmt + total)+"");
        AllOrders.allOrder.get(ZERO).setPrice(total);

    }


    /**
     * This method rounds a decimal number to two digits
     * @param amount The value to round to two decimals
     * @return The rounded double value
     */
    private String round(double amount){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(TWODIGITS);
        df.setMinimumFractionDigits(TWODIGITS);
        String val = df.format(amount);
        amount = Double.parseDouble(df.format(amount));
        return val;
    }

    /**
     * This is the method you must implement when you write implements
     * AdapterView.OnItemClickListener in the class heading.
     * This is the event handler for the onItemClick event.
     * @param adapterView The adapter for the list view
     * @param view The view object being dealt with
     * @param i The index of the item
     * @param l A long value representing data
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove the selected item?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        String item = adapterView.getAdapter().getItem(i).toString();
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_SHORT).show();
                alldonuts.remove(adapterView.getAdapter().getItem(i).toString());
                onRemove(item);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Item Not Removed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Removes the selected item from the list view and
     * changes the pricing accordingly
     * @param value The item to remove from the basket
     */
    protected void onRemove(String value){
        ArrayList<Order> list = AllOrders.allOrderR();
        list.get(list.size() - SIZEINDEX).getMenuItems().remove(value);
        double amt = ZERO;
        int quantity1 = Integer.parseInt(value.substring(value.indexOf('(')
                + OFFSETINDEX, value.indexOf(')')));
        if (value.contains("Strawberry") || (value.contains("Vanilla") &&
                !value.contains("French Vanilla"))
                || value.contains("Blueberry") || value.contains("Apple")
                || value.contains("Grape") || value.contains("Passionfruit")) {
                Yeast yeast = new Yeast("Any");
                amt = Double.parseDouble(subtotal.getText() + "") -
                        yeast.itemPrice() * quantity1;
            }
            else if ((value.contains("French") && !value.contains("French Vanilla"))
                || value.contains("Original") || value.contains("Powder")) {
                DonutHole hole = new DonutHole("Any");
                amt = Double.parseDouble(subtotal.getText() + "") -
                        hole.itemPrice() * quantity1;
            }
            else if (value.contains("Birthday Cake") || value.contains("Chocolate Cake")
                    || value.contains("Cheese Cake")) {
                Cake cake = new Cake("Any");
                amt = Double.parseDouble(subtotal.getText() + "") -
                        cake.itemPrice() * quantity1;
        }else{
            String sizeOfCoffee = value.substring(ZERO, value.indexOf("("));
            quantity1 = Integer.parseInt(value.substring(
                    value.indexOf("(") + OFFSETINDEX, value.indexOf(")")));
            Coffee tempCoffee = getCoffeeObject(value, sizeOfCoffee);
            amt = Double.parseDouble(subtotal.getText() + "") -
                    tempCoffee.itemPrice() * quantity1;
        }
        AllOrders.allOrder.get(list.size() - SIZEINDEX).setPrice(amt);
        revealPricing();
    }

    /**
     * Returns a coffee object, given the selected size and the order string
     * @param value the order string to extract information from.
     * @param sizeOfCoffee a string containing the coffee size
     * @return a coffee object with the given parameters.
     */
    private Coffee getCoffeeObject(String value, String sizeOfCoffee){
        int numberOfAddons = numberOfAddons(value);
        Coffee tempCoffee = new Coffee(sizeOfCoffee);
        ArrayList<String> addons = new ArrayList<>();
        for(int i = 0; i < numberOfAddons; i++){
            addons.add("");
        }
        tempCoffee.addaddIn(addons);
        return tempCoffee;
    }

    /**
     * This method calculates the total number of addons
     * for a given coffee sequence
     * @param value The coffee sequence in String format
     * @return The number of addons for the coffee
     */
    public int numberOfAddons(String value){
        int numberOfAddons = ZEROADDONS;
        if(!value.contains("["))
            numberOfAddons = ZEROADDONS;
        else{
            numberOfAddons += ONEADDON;
            for(int i = 0 ; i < value.length(); i++){
                if(value.charAt(i) == ',')
                    numberOfAddons++;
            }
        }
        return numberOfAddons;
    }

    private void revealPricing(){
        ArrayList<Order> list = AllOrders.allOrderR();
        subtotal.setText(round(list.get(list.size() - SIZEINDEX).getPrice()));
        double taxAmt = list.get(list.size() - SIZEINDEX).getPrice() * TAXRATE;
        tax.setText(round(taxAmt));
        due.setText(round(taxAmt +
                list.get(list.size() - SIZEINDEX).getPrice()) + "");
    }

    /**
     * Checks if a donut item has a particular flavor.
     * @param value a string containing the order in question.
     * @return a boolean if the donut order has any of the possible flavors.
     */
    public boolean checkFlavor(String value){
        return value.contains("Strawberry") || value.contains("Vanilla")
                || value.contains("Blueberry") || value.contains("Apple")
                || value.contains("Grape") || value.contains("Passionfruit");
    }

    /**
     * Perform certain actions when place order is pressed
     * @param view The view object being dealt with
     */
    public void onPlace(View view) {
        if(alldonuts.size() == ZERO) {
            Toast.makeText(getApplicationContext(), "No items" +
                    " in basket: add some to place order", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Order order = new Order(AllOrders.getUniqueNumber());
        for(int i = 0; i < alldonuts.size(); i++){
            order.addItem(alldonuts.get(i));
        }

        AllOrders.addStoreOrder(order.getOrderNumber());
        AllOrders.allOrder = new ArrayList<>();
        AllOrders.runningTotal = ZERO;
        DonutActivity.setTotal(ZERO);
        CoffeeActivity.setTotal(ZERO);
        AllOrders.incrementUnique();
        Toast.makeText(getApplicationContext(), "Order placed",
                Toast.LENGTH_SHORT).show();
    }
}
