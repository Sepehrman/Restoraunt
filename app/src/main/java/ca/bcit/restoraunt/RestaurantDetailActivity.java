package ca.bcit.restoraunt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class RestaurantDetailActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        displayRestaurantDetails();
    }
    private void displayRestaurantDetails() {
        //Restaurant restaurant = (Restaurant) getIntent().getExtras().get("items");
        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("items");
        String restaurantName = restaurant.getName();


        TextView restaurant_name = findViewById(R.id.restaurantName);
        restaurant_name.setText(restaurantName);

        ImageView imageView = findViewById(R.id.restaurant_img_menu);
        Picasso.with(this).load(restaurant.getImgURL()).into((imageView));

    }
}