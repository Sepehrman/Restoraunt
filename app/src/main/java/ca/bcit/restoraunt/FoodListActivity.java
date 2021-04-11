package ca.bcit.firebase2021;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ca.bcit.restoraunt.Food;
import ca.bcit.restoraunt.R;

public class FoodListActivity extends ArrayAdapter<Food> {
    private Activity context;
    private List<Food> foodsList;

    public FoodListActivity(Activity context, List<Food> foodList) {
        super(context, R.layout.activity_list_layout, foodList);
        this.context = context;
        this.foodsList = foodList;
    }

    public FoodListActivity(Context context, int resource, List<Food> objects, Activity context1, List<Food> studentList) {
        super(context, resource, objects);
        this.context = context1;
        this.foodsList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_list_layout, null, true);

        TextView tvName = listViewItem.findViewById(R.id.textViewName);
        TextView tvSchool = listViewItem.findViewById(R.id.textViewFood);

        Food food = foodsList.get(position);
        tvName.setText(food.getFoodName()
                + " " + food.getFoodPrice());
        tvSchool.setText(food.getFoodName());
        return listViewItem;
    }

}
