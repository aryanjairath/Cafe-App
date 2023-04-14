package cs213.project5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

//Testing app for android
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<String> orders = new ArrayList<>();
    private TextView totalDonut;
    private Spinner spinnerdrop;
    private Spinner spinnerquantity;
    private Spinner spinnerflavor;
    private RecyclerView recycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_layout);
        orders = new ArrayList<>();
        totalDonut = findViewById(R.id.totalDonut);
        spinnerdrop = findViewById(R.id.spinnerdrop);
        spinnerquantity = findViewById(R.id.spinnerquantity);
        spinnerflavor = findViewById(R.id.spinnerflavor);
        recycleview = findViewById(R.id.recycleItems);
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
            spinnerLangauge2.setAdapter(adapter2);
        }
        if(type.equals("Cake Donut")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.cake, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            spinnerLangauge2.setAdapter(adapter2);
        }
        if(type.equals("Donut Hole")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.holes, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            spinnerLangauge2.setAdapter(adapter2);
        }
    }
    public void donutView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onAdd(View view) {
        String addDonut = spinnerdrop.getSelectedItem().toString();
        addDonut += "("+spinnerquantity.getSelectedItem().toString()+")";
        orders.add(addDonut);
        //use the LinearLayout for the RecyclerView
        recycleview.setLayoutManager(new LinearLayoutManager(this));

    }
}