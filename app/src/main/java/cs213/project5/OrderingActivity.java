package cs213.project5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class is the maiorderingn activity where the user
 * can look at all of the orders in the store and select
 * also remove orders altogether
 * @author Aryan Jairath, Anis Chihoub
 */
public class OrderingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView ordersView;

    private ArrayAdapter<String> adapter;

    private ArrayList<String> stringOrders;

    /**
     * To be executed when the ordering activity starts
     * @param savedInstanceState A bundle object with the state of the view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders_layout);
        ordersView = findViewById(R.id.orderList);
        ArrayList<Order> orderList = AllOrders.allStoreOrders();
        stringOrders = new ArrayList<String>();
        for(int i = 0; i < AllOrders.allStoreOrders().size(); i++){
            stringOrders.add(AllOrders.allStoreOrders().get(i).toString());
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                stringOrders);
        ordersView.setAdapter(adapter);
        ordersView.setOnItemClickListener(this);

    }



    /**
     * This is the method you must implement when you write implements
     * AdapterView.OnItemClickListener in the class heading.
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
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Item Removed!",
                        Toast.LENGTH_LONG).show();
                stringOrders.remove(adapterView.getAdapter().getItem(i).toString());
                onRemove(item,i);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Item not removed!",
                        Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    /**
     * Removes the order from the interal array lsits of orders and prices
     * @param item the item to be remove as  a string
     * @param index the index from the adapter view.
     */
    protected void onRemove(String item,int index){
        AllOrders.removeItem(item);
    }

}