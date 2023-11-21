package com.ms.recipeapp.Listeners;

import com.ms.recipeapp.Models.InstructionResponse;

import java.util.List;

public interface InstructionListener {
    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);
}
