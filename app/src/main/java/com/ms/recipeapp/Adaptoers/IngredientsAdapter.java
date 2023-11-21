package com.ms.recipeapp.Adaptoers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.recipeapp.Models.ExtendedIngredient;
import com.ms.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientViewHolder> {
    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredient,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
      holder.textView_ingredients_name.setText(list.get(position).name);
        holder.textView_ingredients_name.setSelected(true);
      holder.textView_ingredients_quentity.setText(list.get(position).original);
      holder.textView_ingredients_quentity.setSelected(true);

        Picasso.get().load(list.get(position).image).into(holder.imageView_ingredients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class IngredientViewHolder extends RecyclerView.ViewHolder{
    TextView textView_ingredients_quentity,textView_ingredients_name;
    ImageView imageView_ingredients;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_ingredients_quentity=itemView.findViewById(R.id.textView_ingredients_quentity);
        textView_ingredients_name=itemView.findViewById(R.id.textView_ingredients_name);
        imageView_ingredients=itemView.findViewById(R.id.imageView_ingredients);

    }
}
