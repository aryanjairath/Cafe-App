package cs213.project5;

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

public class BasketView extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listview;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> alldonuts;
    private TextView subtotal;
    private TextView tax;
    private TextView due;
    private static double TAXRATE = .06625;

    private static int SIZEINDEX = 1;

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
        ArrayList<String> order = orders.get(orders.size() - SIZEINDEX).getMenuItems();
        for(int i  = 0; i < order.size(); i++)
            alldonuts.add(order.get(i));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alldonuts );
        listview = findViewById(R.id.listvie);
        subtotal = findViewById(R.id.subtotal);
        tax = findViewById(R.id.tax);
        due = findViewById(R.id.totalamount);
        listview.setOnItemClickListener(this); //register the listener for an OnItemClick event.
        listview.setAdapter(adapter);
        ArrayList<Order> list = AllOrders.allOrderR();
        if(list.size() == 0){
            return;
        }
        subtotal.setText(round(list.get(list.size() - SIZEINDEX).getPrice()));
        double taxAmt = list.get(list.size() - SIZEINDEX).getPrice() * TAXRATE;
        tax.setText(round(taxAmt));
        due.setText(round(taxAmt + list.get(list.size() - SIZEINDEX).
                getPrice()) + "");

    }
    /**
     * This method rounds a decimal number to two digits
     * @param amount The value to round to two decimals
     * @return The rounded double value
     */
    private String round(double amount){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        String val = df.format(amount);
        amount = Double.parseDouble(df.format(amount));
        return val;
    }

    /**
     * This is the method you must implement when you write implements AdapterView.OnItemClickListener
     * in the class heading.
     * This is the event handler for the onItemClick event.
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        /*AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Demo the alert dialog.");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "YES", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    */}
}
