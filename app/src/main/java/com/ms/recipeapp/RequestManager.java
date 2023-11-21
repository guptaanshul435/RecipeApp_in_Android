package com.ms.recipeapp;

import android.content.Context;

import com.ms.recipeapp.Listeners.InstructionListener;
import com.ms.recipeapp.Listeners.RandomRecipeResponseLsitener;
import com.ms.recipeapp.Listeners.RecipeDetailsListener;
import com.ms.recipeapp.Listeners.SimilarRecipesListener;
import com.ms.recipeapp.Models.InstructionResponse;
import com.ms.recipeapp.Models.RandomRecipeResponse;
import com.ms.recipeapp.Models.RecipeDetailResponse;
import com.ms.recipeapp.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseLsitener litener,List<String> tags){
        CallRandomRecipes callRandomRecipes=retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10",tags);
        call.enqueue(new Callback<RandomRecipeResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeResponse> call, Response<RandomRecipeResponse> response) {
                if(!response.isSuccessful()){
                    litener.didError(response.message());
                    return;
                }
                litener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeResponse> call, Throwable t) {
                   litener.didError(t.getMessage());
            }
        });
    }
    public void getRecipeDetails(RecipeDetailsListener listener,int id) {
        CallRecipeDetails callRecipeDetails=retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailResponse> call =callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
    public void getSimilarRecipes(SimilarRecipesListener listener,int id){
        CallSimilarRecipe callSimilarRecipe=retrofit.create(CallSimilarRecipe.class);
        Call<List<SimilarRecipeResponse>> call=callSimilarRecipe.callSimilarRecipe(id,"4",context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
               listener.didError(t.getMessage());
            }
        });
    }
    public void getInstructions(InstructionListener listener,int id){
        CallInstructios callInstructios=retrofit.create(CallInstructios.class);
        Call<List<InstructionResponse>> call= callInstructios.callInstructions(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionResponse>> call, Response<List<InstructionResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionResponse>> call, Throwable t) {
               listener.didError(t.getMessage());
            }
        });
    }
    private interface CallRandomRecipes{
        @GET("recipes/random")
      Call<RandomRecipeResponse> callRandomRecipe(
              @Query("apiKey") String apiKey,
              @Query("number") String number,
              @Query("tags") List<String> tags
      );
    }
    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailResponse> callRecipeDetails(
                @Path ("id") int id,
                @Query("apiKey") String apiKey
        );
        }
        private interface CallSimilarRecipe{
        @GET("recipes/{id}/similar")
            Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                    @Path("id") int id,
                    @Query("number") String number,
                    @Query("apiKey") String apiKey
        );
        }
        private interface CallInstructios{
        @GET("recipes/{id}/analyzedInstructions")
            Call<List<InstructionResponse>> callInstructions(
                    @Path("id") int id,
                    @Query("apiKey") String apiKey
        );
        }
    }



