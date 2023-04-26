package cs213.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter
 * for the RecyclerView.
 * @author Aryan Jairath, Anis Chihoub
 */
public class ItemsAdapter extends
        RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private ArrayList<String> mContacts;
    private Context context;
    public static String removeVal;
    private static final int OFFSETONE = 1;
    private static final int OFFSETTWO = 2;

    // Pass in the contact array into the constructor
    public ItemsAdapter(Context context, ArrayList<String> contacts) {
        this.context = context;
        mContacts = contacts;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent The parent ViewGroup which will be set
     * @param i The type of ViewHolder being dealt with
     * @return An ItemsAdapter representing the current ViewHolder
     */
    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recycleview,
                parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    /**
     * Assign data values for each row according to their
     * "position" (index) when the item becomesvisible on the screen.
     * @param viewHolder the instance of ItemsHolder
     * @param i the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder viewHolder, int i) {
        String place = mContacts.get(i);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(place);
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    /**
     * Get the views from the row layout file
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        private ConstraintLayout parentLayout; //this is the row layout
        private Button remove;


        /**
         * Constructor for the ViewHolder class
         * @param itemView An itemView that has a button
         * to remove items from the list
         */
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.order_content);
            remove = itemView.findViewById(R.id.removebutton);
            setAddButtonOnClick(itemView);


        }

        /**
         * Adds an action listener when the remove icon is clicked
         * within the recycler view
         * @param itemView The View that is currently being dealt with
         */
        private void setAddButtonOnClick(View itemView) {
            remove.setOnClickListener(new View.OnClickListener() {

                /**
                 * This method sets an alert when the minus icon is selected
                 * @param view The View that is currently being dealt with
                 */
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Remove from order?");
                    alert.setMessage(nameTextView.getText().toString());
                    String value = nameTextView.getText().toString();
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /**
                         * Handling the yes response to the prompt
                         * @param dialog The current dialog being used
                         * @param which The current index which is an int
                         */
                        public void onClick(DialogInterface dialog, int which) {
                            doRemoving(value);
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /**
                         * Handling the no response to the prompt
                         * @param dialog A DialogInterface currently being used
                         * @param which The current index which is an int
                         */
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    nameTextView.getText().toString() + " not added.",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }

        /**
         * This method removes donuts and coffee from the basket
         * @param value The value that is being removed from basket
         */
        private void doRemoving(String value) {
            DonutActivity.orders.remove(nameTextView.getText().toString());
            DonutActivity.adapter.notifyDataSetChanged();
            int quantity;
            if(value.contains("Strawberry") || value.contains("Vanilla")
                    || value.contains("Blueberry") || value.contains("Apple")
                    || value.contains("Grape") || value.contains("Passionfruit")){
                quantity = Integer.parseInt(value.substring(value.length() -
                        OFFSETTWO,value.length() - OFFSETONE));
                Yeast yeast = new Yeast("Any");
                Toast.makeText(itemView.getContext(),
                        nameTextView.getText().toString() + " sucessfully " +
                                "removed.", Toast.LENGTH_LONG).show();
                DonutActivity.total -= yeast.itemPrice() * quantity;
            }
            if(value.contains("French") || value.contains("Original")
                    || value.contains("Powder")){
                quantity = Integer.parseInt(value.substring(value.length() -
                        OFFSETTWO,value.length() - OFFSETONE));
                DonutHole hole = new DonutHole("Any");
                DonutActivity.total -= hole.itemPrice() * quantity;
            }
            if(value.contains("Birthday Cake") || value.contains("Chocolate Cake")
                    || value.contains("Cheese Cake")){
                quantity = Integer.parseInt(value.substring(value.length() -
                        OFFSETTWO,value.length() - OFFSETONE));
                Cake cake = new Cake("Any");
                DonutActivity.total -= cake.itemPrice() * quantity;
            }
            DonutActivity.putAmount();
            Toast.makeText(itemView.getContext(),
                    nameTextView.getText().toString() + " sucessfully removed.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
