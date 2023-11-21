package com.ms.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.recipeapp.Adaptoers.IngredientsAdapter;
import com.ms.recipeapp.Adaptoers.InstructionAdapter;
import com.ms.recipeapp.Adaptoers.SimilarRecipeAdapter;
import com.ms.recipeapp.Listeners.InstructionListener;
import com.ms.recipeapp.Listeners.RecipeClickListener;
import com.ms.recipeapp.Listeners.RecipeDetailsListener;
import com.ms.recipeapp.Listeners.SimilarRecipesListener;
import com.ms.recipeapp.Models.InstructionResponse;
import com.ms.recipeapp.Models.RecipeDetailResponse;
import com.ms.recipeapp.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {
    int id;
    TextView textView_meal_name,textView_meal_sourse,textView_meal_sumamary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients,recycler_meal_similar,recycler_meal_instuction;

    RequestManager manager;
    ProgressDialog dialog;
    SimilarRecipeAdapter similarRecipeAdapter;
    IngredientsAdapter ingredientsAdapter;
    InstructionAdapter instructionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        findViews();
        id=Integer.parseInt(getIntent().getStringExtra("id"));
        manager=new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        manager.getSimilarRecipes(similarRecipesListener,id);
        manager.getInstructions(instructionListener,id);
        dialog= new ProgressDialog(this);
        dialog.setTitle("Loding Details...");
        dialog.show();
    }

    private void findViews() {
      textView_meal_name=findViewById(R.id.textView_meal_name);
        textView_meal_sourse=findViewById(R.id.textView_meal_sourse);
        textView_meal_sumamary=findViewById(R.id.textView_meal_sumamary);
        imageView_meal_image=findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients=findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_similar=findViewById(R.id.recycler_meal_similar);
        recycler_meal_instuction=findViewById(R.id.recycler_meal_instuction);
    }
    private final RecipeDetailsListener recipeDetailsListener=new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_sourse.setText(response.sourceName);
            textView_meal_sumamary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);
            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter =new IngredientsAdapter(RecipeDetailActivity.this,response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    };
    private final SimilarRecipesListener similarRecipesListener=new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
         recycler_meal_similar.setHasFixedSize(true);
         recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
         similarRecipeAdapter=new SimilarRecipeAdapter(RecipeDetailActivity.this,response,recipeClickListener);
         recycler_meal_similar.setAdapter(similarRecipeAdapter);

        }

        @Override
        public void didError(String Message) {
         Toast.makeText(RecipeDetailActivity.this, Message,Toast.LENGTH_SHORT).show();
        }
    };
    private final RecipeClickListener recipeClickListener=new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetailActivity.this,RecipeDetailActivity.class)
                    .putExtra("id",id));

        }
    };
    private final InstructionListener instructionListener =new InstructionListener() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
          recycler_meal_instuction.setHasFixedSize(true);
          recycler_meal_instuction.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,LinearLayoutManager.VERTICAL,false));
          instructionAdapter=new InstructionAdapter(RecipeDetailActivity.this,response);
          recycler_meal_ingredients.setAdapter(instructionAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}