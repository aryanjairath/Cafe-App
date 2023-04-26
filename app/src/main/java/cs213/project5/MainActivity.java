package cs213.project5;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * This class is the main activity where the user
 * can select between donut view, ordering basket, coffee
 * and store orders
 * @author Aryan Jairath, Anis Chihoub
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton donutButton;
    private ImageButton coffeeButton;
    private ImageButton orderButton;
    private ImageButton storeButton;
    private ConstraintLayout constraintLayout;
    private static int RED = 196;
    private static int GREEN = 164;
    private static int BLUE = 132;

    /**
     * To be executed when the main activity starts
     * @param savedInstanceState A bundle object with the state of the view.
     */
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

    /**
     * This method starts the donut activity
     * @param view The current view being dealt with
     */
    public void DonutActivity(View view){
        Intent donutIntent = new Intent(this, DonutActivity.class);
        startActivity(donutIntent);
    }

    /**
     * This method starts the coffee activity
     * @param view The current view being dealt with
     */
    public void CoffeeActivity(View view){
        Intent donutIntent = new Intent(this, CoffeeActivity.class);
        startActivity(donutIntent);
    }

    /**
     * This method starts the ordering activity
     * @param view The current view being dealt with
     */
    public void OrderingActivity(View view){
        Intent donutIntent = new Intent(this, OrderingActivity.class);
        startActivity(donutIntent);
    }

    /**
     * This method starts the basket activity
     * @param view The current view being dealt with
     */
    public void BasketActivity(View view){
        Intent donutIntent = new Intent(this, BasketActivity.class);
        startActivity(donutIntent);
    }


}