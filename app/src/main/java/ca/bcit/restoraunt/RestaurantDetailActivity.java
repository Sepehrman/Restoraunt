package ca.bcit.restoraunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class RestaurantDetailActivity extends AppCompatActivity {


    DatabaseReference restaurantItemsDB;

    Restaurant restaurant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        restaurantItemsDB = FirebaseDatabase.getInstance().getReference("Restaurants");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);


        displayRestaurantDetails();
        Button locationButton = findViewById(R.id.locationButton);
        Button addItemBtn = findViewById(R.id.addItemBtn);
        Button listViewBtn = findViewById(R.id.listViewAct);



        listViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RestaurantDetailActivity.this, ShowMenuActivity.class);
                Bundle bundle = getIntent().getExtras();
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem();
            }
        });

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
        restaurant = (Restaurant) getIntent().getSerializableExtra("items");
        String restaurantName = restaurant.getName();


        TextView restaurant_name = findViewById(R.id.restaurantName);
        restaurant_name.setText(restaurantName);

        ImageView imageView = findViewById(R.id.restaurant_img_menu);
        Picasso.with(this).load(restaurant.getImgURL()).into((imageView));

    }

    private void addMenuItem() {

        restaurant = (Restaurant) getIntent().getSerializableExtra("items");

        EditText editTextMenuItemName = findViewById(R.id.editTextMenuItem);
        EditText editTextMenuItemPrice = findViewById(R.id.editTextMenuItemPrice);
        String foodName = editTextMenuItemName.getText().toString().trim();
        String foodPrice = '$' + editTextMenuItemPrice.getText().toString().trim();


//
        if (TextUtils.isEmpty(foodName)) {
            Toast.makeText(this, "You must enter a Menu item's name", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(foodPrice)) {
            Toast.makeText(this, "You must enter a Menu item's pricing.", Toast.LENGTH_LONG).show();
            return;
        }

        String currentRestaurantName = restaurant.getName();
////        String currentRestaurantName = restaurant.getName();
//
        Food foodObj = new Food(foodName, foodPrice);

        String id = restaurantItemsDB.push().getKey();
        Task setTaskRestaurant = restaurantItemsDB.child(currentRestaurantName).child("Menu").
                child(foodName).setValue(foodObj);


        //
        setTaskRestaurant.addOnSuccessListener(o -> {
            Toast.makeText(RestaurantDetailActivity.this,"Item and pricing added.",Toast.LENGTH_LONG).show();

            editTextMenuItemName.setText("");
            editTextMenuItemPrice.setText("");
        });
//
//        setTaskRestaurant.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(RestaurantDetailActivity.this,
//                        "something went wrong.\n" + e.toString(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}



