package cs213.project5;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton donutButton;
    private ImageButton coffeeButton;
    private ImageButton orderButton;
    private ImageButton storeButton;
    private ConstraintLayout constraintLayout;
    private static int RED = 196;
    private static int GREEN = 164;
    private static int BLUE = 132;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donutButton = findViewById(R.id.donutButton);
        coffeeButton = findViewById(R.id.coffeeButton);
        orderButton = findViewById(R.id.bagButton);
        storeButton = findViewById(R.id.storeButton);
        constraintLayout = findViewById(R.id.relativeLayout);
        constraintLayout.setBackgroundColor(Color.rgb(RED, GREEN, BLUE));
    }

    public void DonutActivity(View view){
        Intent donutIntent = new Intent(this, DonutView.class);
        startActivity(donutIntent);
    }


    public void CoffeeActivity(View view){
        Intent donutIntent = new Intent(this, CoffeeView.class);
        startActivity(donutIntent);
    }

    public void OrderingActivity(View view){
        Intent donutIntent = new Intent(this, OrderingView.class);
        startActivity(donutIntent);
    }

    public void BasketActivity(View view){
        Intent donutIntent = new Intent(this, BasketView.class);
        startActivity(donutIntent);
    }


}