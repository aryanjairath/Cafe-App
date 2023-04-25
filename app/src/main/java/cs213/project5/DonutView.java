package cs213.project5;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class is the donut activity where the user
 * can choose different donuts with different flavors,
 * and remove selections they no longer want
 * @author Aryan Jairath, Anis Chihoub
 */
public class DonutView extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static ArrayList<String> orders;
    public static ArrayList<String> flavors;
    private static TextView totalDonut;
    private static Spinner spinnerdrop;
    private static Spinner spinnerquantity;
    private RecyclerView recycleview;
    private RecyclerView donutTypes;

    private ImageView donutPic;
    public static double total;
    private Order order;
    private int uniqueOrder = ZERO;
    public static ItemsAdapter adapter;
    public static ItemsAdapterTwo typeadapter;
    public static boolean style;
    private LinearLayout constraintLayout;

    private static final int TWODIGITS = 2;
    private static final int OFFSETTWO = 2;
    private static final int OFFSETONE = 1;
    private static final int RED = 245;
    private static final int ZERO = 0;
    private static final int GREEN = 245;
    private static final int BLUE = 220;


    /**
     * Creates the view for the donut view
     * @param savedInstanceState a bundle object with the state of the view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_layout);
        orders = new ArrayList<>();
        flavors = new ArrayList<>(
                Arrays.asList("Yeast - Strawberry", "Yeast - Vanilla", "Yeast - Blueberry",
                        "Yeast - Apple", "Yeast - Grape", "Yeast - Passionfruit",
                        "Cake - Birthday Cake", "Cake - Chocolate Cake","Cake - Cheese Cake"));
        totalDonut = findViewById(R.id.totalDonut);
        spinnerdrop = findViewById(R.id.spinnerdrop);
        spinnerquantity = findViewById(R.id.spinnerquantity);
        recycleview = findViewById(R.id.recycleItems);
        donutTypes = findViewById(R.id.donutTypes);
        donutPic = findViewById(R.id.donutPics);
        total = ZERO;
        order = new Order(uniqueOrder);
        style = false;
        adapter = new ItemsAdapter(this, orders); //create the adapter
        recycleview.setAdapter(adapter); //bind the list of items to the RecyclerView
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        typeadapter = new ItemsAdapterTwo(this, flavors); //create the adapter
        style = true;
        donutTypes.setAdapter(typeadapter); //bind the list of items to the RecyclerView
        donutTypes.setLayoutManager(new LinearLayoutManager(this));
        constraintLayout = findViewById(R.id.relativelay);
        constraintLayout.setBackgroundColor(Color.rgb(RED, GREEN, BLUE));
        doit();
        spinnerdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                changeList(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    /**
     * Sets the orders for the donuts
     * @param orders An array list containing donuts
     * in String format.
     */
    public void setOrders(ArrayList<String> orders){
        this.orders = orders;
    }

    /**
     * This method sets a certain total for a donut order object
     * @param tot A double representing the total value for
     * a particular donut order
     */
    public static void setTotal(double tot) {
        total = tot;
    }

    /**
     * This method sets the spinners for the quantities and
     * types of donuts
     */
    public void doit(){
        flavors.add("Donut Hole - Original");
        flavors.add("Donut Hole - French");
        flavors.add("Donut Hole - Powder");
        typeadapter.notifyDataSetChanged();
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.donuts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge = findViewById(R.id.spinnerdrop);
        spinnerLangauge.setAdapter(adapter);
        //String text = spinnerdrop.getSelectedItem().toString();
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.yeast, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
        spinnerLangauge2.setAdapter(adapter2);*/

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.quantity, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge3 = findViewById(R.id.spinnerquantity);
        spinnerLangauge3.setAdapter(adapter3);

    }

    /**
     * This method alters the spinner contents and pictures based
     * on what type of donut is selected
     * @param type A String representing the type of donut selected
     */
    public void changeList(String type){
        if(type.equals("Yeast Donut")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.yeast, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            donutPic.setImageResource(R.drawable.yeast);
            spinnerLangauge2.setAdapter(adapter2);
        }
        if(type.equals("Cake Donut")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.cake, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            donutPic.setImageResource(R.drawable.cake);
            spinnerLangauge2.setAdapter(adapter2);
        }
        if(type.equals("Donut Hole")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.holes, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            donutPic.setImageResource(R.drawable.holes);
            spinnerLangauge2.setAdapter(adapter2);
        }
    }

    /**
     * This is the method you must implement when you write implements
     * AdapterView.OnItemClickListener in the class heading.
     * This is the event handler for the onItemClick event.
     * @param adapterView The adapter for the list view
     * @param view The view object being dealt with
     * @param i The index of the item
     * @param id A long value
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

    }

    /**
     * This method performs actions of adding individual
     * donuts when corresponding button in view is pressed
     */
    public static void onAdd(String donut) {
        String addDonut = donut.substring(donut.indexOf("-")+1);
        addDonut += "(" + spinnerquantity.getSelectedItem().toString() + ")";
        orders.add(addDonut);
        adapter.notifyDataSetChanged();
        int quantity = Integer.parseInt(spinnerquantity.getSelectedItem().toString());
        String donutType = donut.substring(0, donut.indexOf("-")-1);
        String flavor = donut.substring(donut.indexOf("-")+1);
        donutType = donutType.trim();
        if(donutType.equals("Yeast")){
            Yeast yeast = new Yeast(flavor);
            total += yeast.itemPrice() * quantity;
        }
        if(donutType.equals("Cake")){
            Cake cake = new Cake(flavor);
            total += cake.itemPrice() * quantity;
        }
        if(donutType.equals("Donut Hole")){
            DonutHole hole = new DonutHole(flavor);
            total += hole.itemPrice() * quantity;
        }
        round();
    }


    /**
     * Rounds a given decimal to two decimal places
     */
    private static void round(){
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(TWODIGITS);
        df.setMaximumFractionDigits(TWODIGITS);
        totalDonut.setText(df.format(total));
        total = Double.parseDouble(df.format(total));
    }

    /**
     * Sets the current price onto the text view
     */
    public static void putAmount(){
        round();
        totalDonut.setText(total+"");
    }

    /**
     * This method performs actions of adding all currently added
     * donuts when corresponding button in view is pressed
     * @param view The view that is being dealt with
     */
    public void onAddOrder(View view) {
        for (int i = 0; i < orders.size(); i++) {
            String type = orders.get(i);
            order.addItem(type);
        }
        AllOrders.runningTotal += total;
        order.setPrice(AllOrders.runningTotal);
        AllOrders.addOrder(order, uniqueOrder);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Order added to basket",
                Toast.LENGTH_SHORT).show();
    }
}