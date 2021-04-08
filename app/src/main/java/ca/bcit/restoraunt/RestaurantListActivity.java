package ca.bcit.restoraunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;

public class RestaurantListActivity extends AppCompatActivity {
    private RestaurantAdapter adapter;
    private RecyclerView restaurantRecycler;
    private Restaurant[] restaurants;
    private DatabaseReference df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        restaurantRecycler = findViewById(R.id.restaurant_recycler);
        restaurants = Restaurant.getAllRestaurants();

        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        restaurantRecycler.setLayoutManager(lm);
    }

    @Override
    protected void onStart(){
        super.onStart();
        //Grab restaurants from database


        adapter = new RestaurantAdapter(restaurants);
        restaurantRecycler.setAdapter(adapter);
    }
}