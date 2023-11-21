package com.ms.recipeapp.Listeners;

import com.ms.recipeapp.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> response,String message);
    void didError(String Message);
}
