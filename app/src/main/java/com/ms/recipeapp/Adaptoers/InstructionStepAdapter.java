package com.ms.recipeapp.Adaptoers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.recipeapp.Models.Step;
import com.ms.recipeapp.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionstepViewHolder> {
    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionstepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionstepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionstepViewHolder holder, int position) {
     holder.text_instruction_step_number.setText(String.valueOf(list.get(position).number));
     holder.text_instruction_step_title.setText(list.get(position).step);
     holder.recycler_instruction_ingredients.setHasFixedSize(true);
     holder.recycler_instruction_ingredients.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
     InstructionIngredientAdapter instructionIngredientAdapter=new InstructionIngredientAdapter(context,list.get(position).ingredients);
     holder.recycler_instruction_ingredients.setAdapter(instructionIngredientAdapter);
     holder.recycler_instruction_equipment.setHasFixedSize(true);
     holder.recycler_instruction_equipment.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
     InstructionEquepmentAdapter instructionEquepmentAdapter=new InstructionEquepmentAdapter(context,list.get(position).equipment);
     holder.recycler_instruction_equipment.setAdapter(instructionEquepmentAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionstepViewHolder extends RecyclerView.ViewHolder {
    TextView text_instruction_step_number,text_instruction_step_title;
    RecyclerView recycler_instruction_equipment,recycler_instruction_ingredients;
    public InstructionstepViewHolder(@NonNull View itemView) {
        super(itemView);
        text_instruction_step_number=itemView.findViewById(R.id.text_instruction_step_number);
        text_instruction_step_title=itemView.findViewById(R.id.text_instruction_step_title);
        recycler_instruction_equipment=itemView.findViewById(R.id.recycler_instruction_equipment);
        recycler_instruction_ingredients=itemView.findViewById(R.id.recycler_instruction_ingredients);

    }
}