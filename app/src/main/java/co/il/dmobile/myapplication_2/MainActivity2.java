package co.il.dmobile.myapplication_2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle b = getIntent().getExtras();
        Recipes Recipes = (Recipes) b.getSerializable("Recipes");

        ImageView car_photo = findViewById(R.id.rec_photo);
        TextView Brand = findViewById(R.id.Level);
        TextView Model = findViewById(R.id.Time);
        TextView Year = findViewById(R.id.Name);
        TextView Price = findViewById(R.id.Ingredients);
        TextView Cooking = findViewById(R.id.Cooking);

        if(Recipes.getUri() == null)
            car_photo.setImageResource(Recipes.getImage());
        else
            car_photo.setImageURI(Uri.parse(Recipes.getUri()));
        Brand.setText(Recipes.getLevel());
        Model.setText(Recipes.getName());
        Year.setText(Recipes.getTime());
        Price.setText(Recipes.getIngredients());
        Cooking.setText(Recipes.getCooking());
    }
}