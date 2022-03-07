package co.il.dmobile.myapplication_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<recipesViewHolder> {

    private List<Recipes> Recipes;
    public RecipesAdapter(List<Recipes> recipes) { this.Recipes = recipes;}


    public void AddRec(Recipes recipes) {
        Recipes.add(recipes);
        notifyDataSetChanged();
        DataPersistencyHelper.StoreData(Recipes);
    }

    public void DeleteRes(int position) {
        Recipes.remove(position);
        notifyDataSetChanged();
        DataPersistencyHelper.StoreData(Recipes);
    }

    @NonNull
    @Override
    public recipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_catalogue, parent, false);
        recipesViewHolder vh = new recipesViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull recipesViewHolder holder, int position) {
        Recipes recipes = Recipes.get(position);
        if(recipes.getUri() == null)
            holder.rec_photo.setImageResource(recipes.getImage());
        else{
            holder.rec_photo.setImageURI(Uri.parse(recipes.getUri()));
        }
        System.out.println(recipes.getLevel());
        holder.Level.setText(recipes.getLevel());
        holder.Time.setText(recipes.getName());
        holder.Name.setText(recipes.getTime());

        {
            holder.Card.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), MainActivity2.class);
                    i.putExtra("Recipes", recipes);
                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),
                                    holder.rec_photo,
                                    "Recipes_photoTransition");
                    v.getContext().startActivity(i, options.toBundle());
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return Recipes.size();
    }
}