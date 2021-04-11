package ca.bcit.restoraunt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>
{
    private final ArrayList<Food> foodList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;

        public ViewHolder(CardView cView) {
            super(cView);
            cardView = cView;
        }
    }

    public FoodAdapter(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_menu_list, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        TextView textView = cardView.findViewById(R.id.food_name);
        textView.setText(foodList.get(position).getFoodName());
        TextView textView2 = cardView.findViewById(R.id.food_price);
        textView2.setText(foodList.get(position).getFoodPrice());

    }

    private Listener listener;
    interface Listener {
        void onClick(Restaurant foodName);
    }


    public void setListener(Listener listener) {
        this.listener = listener;
    }


}