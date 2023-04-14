package cs213.project5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends
        RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<String> mContacts;
    private Context context;
    // Pass in the contact array into the constructor
    public ItemsAdapter(Context context, List<String> contacts) {
        this.context=context;
        mContacts = contacts;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycleview, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder viewHolder, int i) {
        String place = mContacts.get(i);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(place);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.order_content);
        }
    }
}
