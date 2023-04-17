package cs213.project5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderingView extends AppCompatActivity {
    private ListView ordersView;

    private ArrayAdapter<String> adapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders_layout);
        ordersView = findViewById(R.id.orderList);
        ArrayList<Order> orderList = AllOrders.allStoreOrders();
        ArrayList<String> stringOrders = new ArrayList<String>();
        for(int i = 0; i < AllOrders.allStoreOrders().size(); i++){
            stringOrders.add(AllOrders.allStoreOrders().get(i).toString());
        }
        adapterView = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                stringOrders);
        ordersView.setAdapter(adapterView);

    }
}