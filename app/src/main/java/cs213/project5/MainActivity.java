package cs213.project5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//Testing app for android
public class MainActivity extends AppCompatActivity {
    private TextView totalDonut;
    private Spinner spinnerdrop;
    public static int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_layout);
        totalDonut = findViewById(R.id.totalDonut);
        spinnerdrop = findViewById(R.id.spinnerdrop);
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
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);

    }
}