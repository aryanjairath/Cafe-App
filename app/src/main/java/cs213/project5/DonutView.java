package cs213.project5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

//Testing app for android
public class DonutView extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static ArrayList<String> orders = new ArrayList<>();
    private TextView totalDonut;
    private Spinner spinnerdrop;
    private Spinner spinnerquantity;
    private Spinner spinnerflavor;
    private RecyclerView recycleview;
    private ImageView donutPic;
    public static double total;
    private Order order;
    private int uniqueOrder = 0;
    private ItemsAdapter adapter;
    private static final int TWODIGITS = 2;
    private static final int OFFSETTWO = 2;
    private static final int OFFSETONE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_layout);
        orders = new ArrayList<>();
        totalDonut = findViewById(R.id.totalDonut);
        spinnerdrop = findViewById(R.id.spinnerdrop);
        spinnerquantity = findViewById(R.id.spinnerquantity);
        spinnerflavor = findViewById(R.id.spinnerflavor);
        recycleview = findViewById(R.id.recycleItems);
        donutPic = findViewById(R.id.donutPics);
        total = 0;
        order = new Order(uniqueOrder);
        adapter = new ItemsAdapter(this, orders); //create the adapter
        recycleview.setAdapter(adapter); //bind the list of items to the RecyclerView
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        doit();
        spinnerdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeList(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


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

    public void doit(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.donuts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge = findViewById(R.id.spinnerdrop);
        spinnerLangauge.setAdapter(adapter);
        //String text = spinnerdrop.getSelectedItem().toString();
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.yeast, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
        spinnerLangauge2.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.quantity, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge3 = findViewById(R.id.spinnerquantity);
        spinnerLangauge3.setAdapter(adapter3);

    }
    public void changeList(String type){
        if(type.equals("Yeast Donut")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.yeast, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            donutPic.setImageResource(R.drawable.yeast);
            spinnerLangauge2.setAdapter(adapter2);
        }
        if(type.equals("Cake Donut")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.cake, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            donutPic.setImageResource(R.drawable.cake);
            spinnerLangauge2.setAdapter(adapter2);
        }
        if(type.equals("Donut Hole")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.holes, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            donutPic.setImageResource(R.drawable.holes);
            spinnerLangauge2.setAdapter(adapter2);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onAdd(View view) {
        String addDonut = spinnerflavor.getSelectedItem().toString();
        addDonut += "("+spinnerquantity.getSelectedItem().toString()+")";
        orders.add(addDonut);
        adapter.notifyDataSetChanged();
        int quantity = Integer.parseInt(spinnerquantity.getSelectedItem().toString());
        String donutType = spinnerdrop.getSelectedItem().toString();
        String flavor = spinnerflavor.getSelectedItem().toString();
        if(donutType.equals("Yeast Donut")){
            Yeast yeast = new Yeast(flavor);
            total += yeast.itemPrice() * quantity;
        }
        if(donutType.equals("Cake Donut")){
            Cake cake = new Cake(flavor);
            total += cake.itemPrice() * quantity;
        }
        if(donutType.equals("Donut Hole")){
            DonutHole hole = new DonutHole(flavor);
            total += hole.itemPrice() * quantity;
        }
        round();
    }


    public void onRemove(String value) {
        orders.remove(value);
        adapter.notifyDataSetChanged();
        int quantity;
        if(value.contains("Strawberry") || value.contains("Vanilla")
                || value.contains("Blueberry") || value.contains("Apple")
                || value.contains("Grape") || value.contains("Passionfruit")){
            quantity = Integer.parseInt(value.substring(value.length() -
                    OFFSETTWO,value.length() - OFFSETONE));
            Yeast yeast = new Yeast("Any");
            total -= yeast.itemPrice() * quantity;
        }
        if(value.contains("French") || value.contains("Original")
                || value.contains("Powder")){
            quantity = Integer.parseInt(value.substring(value.length() -
                    OFFSETTWO,value.length() - OFFSETONE));
            DonutHole hole = new DonutHole("Any");
            total -= hole.itemPrice() * quantity;
        }
        if(value.contains("Birthday Cake") || value.contains("Chocolate Cake")
                || value.contains("Cheese Cake")){
            quantity = Integer.parseInt(value.substring(value.length() -
                    OFFSETTWO,value.length() - OFFSETONE));
            Cake cake = new Cake("Any");
            total -= cake.itemPrice() * quantity;
        }
        //round();
    }

    /**
     * Rounds a given decimal to two decimal places
     */
    private void round(){
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(TWODIGITS);
        df.setMaximumFractionDigits(TWODIGITS);
        totalDonut.setText(df.format(total));
        total = Double.parseDouble(df.format(total));
    }

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
        Toast.makeText(getApplicationContext(), "Order added to basket", Toast.LENGTH_SHORT).show();
    }

}