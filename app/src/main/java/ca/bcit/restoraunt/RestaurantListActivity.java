package ca.bcit.restoraunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class RestaurantListActivity extends AppCompatActivity {
    private RestaurantAdapter adapter;
    private RecyclerView restaurantRecycler;
    private ArrayList<Restaurant> restaurants;
    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        restaurants = new ArrayList<>();
        //get database
        db = FirebaseDatabase.getInstance().getReference().child("Restaurants");

        restaurantRecycler = findViewById(R.id.restaurant_recycler);

        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        restaurantRecycler.setLayoutManager(lm);
    }

    @Override
    protected void onStart(){
        super.onStart();
        //Grab restaurants from database
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //restaurants.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Restaurant r = postSnapshot.getValue(Restaurant.class);
                    restaurants.add(r);
                }
                adapter = new RestaurantAdapter(restaurants);
                restaurantRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}