package com.ms.recipeapp.Adaptoers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.recipeapp.Models.Equipment;
import com.ms.recipeapp.Models.Ingredient;
import com.ms.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEquepmentAdapter extends RecyclerView.Adapter<InstructionEquepmentViewHolder>{
    Context context;
    List<Equipment> list;

    public InstructionEquepmentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquepmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquepmentViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquepmentViewHolder holder, int position) {
        holder.textView_instruction_step_item.setText(list.get(position).name);
        holder.textView_instruction_step_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image).into(holder.imageView_instruction_step_item);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionEquepmentViewHolder extends RecyclerView.ViewHolder {
    TextView textView_instruction_step_item;
    ImageView imageView_instruction_step_item;
    public InstructionEquepmentViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_instruction_step_item=itemView.findViewById(R.id.textView_instruction_step_item);
        imageView_instruction_step_item=itemView.findViewById(R.id.imageView_instruction_step_item);
    }
}