package ca.bcit.restoraunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewMenuActivity extends AppCompatActivity {

    DatabaseReference databaseMenuItems;

    List<Food> foods;
    ListView listViewFoods;
    String[] foodCategories = {"Starter", "Entrees", "Dessert"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        foods = new ArrayList<Food>();
        //listViewFoods = findViewById(R.id.lvFoods);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseMenuItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot foodSnapshot: snapshot.getChildren()) {
                    Food food = foodSnapshot.getValue(Food.class);
                    foods.add(food);
                }
                ca.bcit.firebase2021.FoodListActivity foodListAdapter = new ca.bcit.firebase2021.FoodListActivity(ViewMenuActivity.this, foods);
                listViewFoods.setAdapter(foodListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}