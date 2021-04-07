package ca.bcit.restoraunt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>
{
    private final Restaurant[] restaurants;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;

        public ViewHolder(CardView cView) {
            super(cView);
            cardView = cView;
        }
    }

    public RestaurantAdapter(Restaurant[] restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public int getItemCount() {
        return restaurants.length;
    }

    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_restaurant_list, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        TextView textView = cardView.findViewById(R.id.restaurant_name);
        textView.setText(restaurants[position].getName());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(restaurants[position]);
                }
            }
        });


    }

    private Listener listener;
    interface Listener {
        void onClick(Restaurant foodName);
    }


    public void setListener(Listener listener) {
        this.listener = listener;
    }


}