package co.il.dmobile.recipes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataPersistencyHelper {

    public static Context Context;

    public static void StoreData(List<Recipes> cars)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Context);
        SharedPreferences.Editor editor = sp.edit();
        String json = new Gson().toJson(cars);
        editor.putString("recipes", json);
        editor.commit();
    }

    public static List<Recipes> LoadData()
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Context);
        String json = sp.getString("Recipes",null);
        if (json != null)
        {
            Type type = new TypeToken<List<Recipes>>(){}.getType();
            return new Gson().fromJson(json,type);
        }
        else
        {
            List<Recipes> recipes = new ArrayList<Recipes>();
            recipes.add(new Recipes(R.drawable.pizza, "pizza", "easy", "10 minutes ", "cheese, flour, tomato sauce", " "));
            recipes.add(new Recipes(R.drawable.burger, "burger","easy", "15 minutes", "patty, tomato, burger, lettece, cheese", " "));
            recipes.add(new Recipes(R.drawable.pasta, "pasta","easy","15 minutes", "pasta, tomato sauce", " "));
            recipes.add(new Recipes(R.drawable.fries,"french fries","easy","20 minutes", "potatos, oil", " "));
            recipes.add(new Recipes(R.drawable.shnizel,"shnizel","easy","20 minutes", "chiken breast, eggs, bread crumps, oil", " "));
            recipes.add(new Recipes(R.drawable.bread,"bread","easy","20 minutes", "flour, water, oil", " "));
            recipes.add(new Recipes(R.drawable.soup,"Soup","easy", "20 minutes","water, sweet potato, cream", " "));
            recipes.add(new Recipes(R.drawable.cake,"cake","easy", "20 minutes", "flour, eggs, chocolate, chocolate powder,sugar", " "));
            return recipes;
        }
    }

}
