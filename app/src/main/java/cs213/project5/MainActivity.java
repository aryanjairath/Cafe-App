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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_layout);
        totalDonut = findViewById(R.id.totalDonut);
        spinnerdrop = findViewById(R.id.spinnerdrop);
        doit();
    }
    public void doit(){
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.donuts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge = findViewById(R.id.spinnerdrop);
        spinnerLangauge.setAdapter(adapter);
        //String text = spinnerdrop.getSelectedItem().toString();
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.yeast, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
        spinnerLangauge2.setAdapter(adapter2);

    }

    public void donutView(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    protected void doit(View view) {
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.donuts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge = findViewById(R.id.spinnerdrop);
        spinnerLangauge.setAdapter(adapter);
        String text = spinnerLangauge.getSelectedItem().toString();
        if(text.equals("Yeast")) {
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.yeast, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            spinnerLangauge2.setAdapter(adapter2);
        }
        if(text.equals("Cake")){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.cake, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            Spinner spinnerLangauge2 = findViewById(R.id.spinnerflavor);
            spinnerLangauge2.setAdapter(adapter2);
        }
    }


}