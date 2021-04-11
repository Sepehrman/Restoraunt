package ca.bcit.restoraunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    EditText editTextMenuItemName;
    EditText editTextMenuItemPrice;
    Restaurant restaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        restaurantItemsDB = FirebaseDatabase.getInstance().getReference("Restaurants");
        editTextMenuItemName = findViewById(R.id.editTextMenuItem);
        editTextMenuItemPrice = findViewById(R.id.editTextMenuItemPrice);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        displayRestaurantDetails();
        Button locationButton = findViewById(R.id.locationButton);
        Button addItemBtn = findViewById(R.id.addItemBtn);

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
        String itemName = editTextMenuItemName.getText().toString().trim();
        String itemPrice = "$" + editTextMenuItemPrice.getText().toString().trim();

        if (TextUtils.isEmpty(itemName)) {
            Toast.makeText(this, "You must enter a Menu item's name", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(itemPrice)) {
            Toast.makeText(this, "You must enter a Menu item's pricing.", Toast.LENGTH_LONG).show();
            return;
        }

        String currentRestaurantName = restaurantItemsDB.push().getKey();
        
        Task setTaskRestaurant = restaurantItemsDB.child(currentRestaurantName).setValue(itemName, itemPrice);

        setTaskRestaurant.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(RestaurantDetailActivity.this,"Item and pricing added.",Toast.LENGTH_LONG).show();

                editTextMenuItemName.setText("");
                editTextMenuItemPrice.setText("");
            }
        });

        setTaskRestaurant.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RestaurantDetailActivity.this,
                        "something went wrong.\n" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}



