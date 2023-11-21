package com.ms.recipeapp.Listeners;

import com.ms.recipeapp.Models.RecipeDetailResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailResponse response,String message);
    void didError(String message);
}
