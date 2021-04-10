package ca.bcit.restoraunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class RestaurantDetailActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        displayRestaurantDetails();
        Button locationButton = findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RestaurantDetailActivity.this, GoogleMapsActivity.class);
                Bundle bundle = getIntent().getExtras();
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
    private void displayRestaurantDetails() {
        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("items");
        String restaurantName = restaurant.getName();


        TextView restaurant_name = findViewById(R.id.restaurantName);
        restaurant_name.setText(restaurantName);

        ImageView imageView = findViewById(R.id.restaurant_img_menu);
        Picasso.with(this).load(restaurant.getImgURL()).into((imageView));


    }

}