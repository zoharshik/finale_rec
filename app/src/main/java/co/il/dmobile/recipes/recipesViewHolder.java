package co.il.dmobile.recipes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class recipesViewHolder extends RecyclerView.ViewHolder {

    public CardView Card;
    public ImageView rec_photo;
    public TextView Level;
    public TextView Time;
    public TextView Name;

    public recipesViewHolder(@NonNull View itemView) {
        super(itemView);

        Card = itemView.findViewById(R.id.card);
        rec_photo = itemView.findViewById(R.id.rec_photo);
        Level = itemView.findViewById(R.id.Level);
        Time = itemView.findViewById(R.id.Time);
        Name = itemView.findViewById(R.id.Name);

    }
}
