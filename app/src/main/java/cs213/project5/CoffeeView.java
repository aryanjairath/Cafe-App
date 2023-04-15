package cs213.project5;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class CoffeeView extends AppCompatActivity{

    private CheckBox sweetCream;

    private CheckBox frenchVanilla;

    private CheckBox irishCream;

    private CheckBox caramelBox;

    private CheckBox mochaBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_layout);
        //doit();
    }

    protected void doit(){
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.donuts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinnerLangauge = findViewById(R.id.spinner);
        spinnerLangauge.setAdapter(adapter);
    }

    public void doit(View view) {
    }



}
