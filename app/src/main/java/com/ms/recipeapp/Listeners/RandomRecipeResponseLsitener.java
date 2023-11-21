package com.ms.recipeapp.Listeners;

import com.ms.recipeapp.Models.RandomRecipeResponse;

public interface RandomRecipeResponseLsitener {
    void didFetch(RandomRecipeResponse response,String message);
    void didError(String message);
}
