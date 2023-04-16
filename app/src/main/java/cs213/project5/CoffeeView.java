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

    private TextView cost;

    private ArrayList<String> coffeeOrders;

    private ListView currentOrders;

    private double total;

    private static double EMPTY = 0;

    private ArrayAdapter<String> adapterCoffee;



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
        total = 0;
        loadView();
        currentOrders.setOnItemClickListener(this);
    }



    public void setOrders(ArrayList<String> orders){
        this.orders = orders;
    }

    private void loadView(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Coffee_Quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterTwo = ArrayAdapter.createFromResource(this, R.array.Sizes, android.R.layout.simple_spinner_item);
        adapterCoffee = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                coffeeOrders);
        sizeSpinner.setAdapter(adapterTwo);
        quantSpinner.setAdapter(adapter);
        currentOrders.setAdapter(adapterCoffee);
    }

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

    public void onAdd(View view){
        int quantityOfCoffee = Integer.parseInt(quantSpinner.getSelectedItem().toString());
        String size = sizeSpinner.getSelectedItem().toString();
        Coffee orderedCoffee = new Coffee(size);
        ArrayList<String> addOnCust = getAddons();
        orderedCoffee.addaddIn(addOnCust);
        if(addOnCust.size() == EMPTY)
            coffeeOrders.add(size + "(" + quantityOfCoffee + ").");
        else
            coffeeOrders.add(size + "(" + quantityOfCoffee + ")" + " Addons: " +  addOnCust.toString()+ ".");
        adapterCoffee.notifyDataSetChanged();
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
        System.out.println("Hit");
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove the selected item?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        String item = adapterView.getAdapter().getItem(i).toString();
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Item Removed!", Toast.LENGTH_LONG).show();
                coffeeOrders.remove(adapterView.getAdapter().getItem(i).toString());
                onRemove(item);
                adapterCoffee.notifyDataSetChanged();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Item not removed!", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    protected void onRemove(String item){
        for(int i = 0; i < coffeeOrders.size(); i++){
            if(coffeeOrders.get(i).equals(item)){
                coffeeOrders.remove(i);
            }
        }

    }


    public void onAddOrder(View view){
        int quantityOfCoffee = Integer.parseInt(quantSpinner.getSelectedItem().toString());
        String size = sizeSpinner.getSelectedItem().toString();
        Coffee orderedCoffee = new Coffee(size);
        ArrayList<String> addOnCust = getAddons();
        orderedCoffee.addaddIn(addOnCust);
        if(addOnCust.size() == EMPTY)
            coffeeOrders.add(size + "(" + quantityOfCoffee + ").");
        else
            coffeeOrders.add(size + "(" + quantityOfCoffee + ")" + " Addons: " +  addOnCust.toString()+ ".");
        adapterCoffee.notifyDataSetChanged();
    }


}
