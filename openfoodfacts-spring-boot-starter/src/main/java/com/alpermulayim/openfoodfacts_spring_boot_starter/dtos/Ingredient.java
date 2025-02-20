package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

import java.util.List;
import java.util.Map;

public record Ingredient(
        List<String> additivesTags,
        String allergens,
        String allergensLc,
        List<String> allergensHierarchy,
        List<String> allergensTags,
        List<Ingredient> ingredients,
        IngredientsAnalysis ingredientsAnalysis,
        List<String> ingredientsAnalysisTags,
        int ingredientsFromOrThatMayBeFromPalmOilN,
        int ingredientsFromPalmOilN,
        List<String> ingredientsFromPalmOilTags,
        List<String> ingredientsHierarchy,
        int ingredientsN,
        List<String> ingredientsNTags,
        List<String> ingredientsOriginalTags,
        int ingredientsPercentAnalysis,
        int ingredientsSweetenersN,
        int ingredientsNonNutritiveSweetenersN,
        List<String> ingredientsTags,
        String ingredientsLc,
        String ingredientsText,
        String ingredientsTextWithAllergens,
        int ingredientsThatMayBeFromPalmOilN,
        List<String> ingredientsThatMayBeFromPalmOilTags,
        int ingredientsWithSpecifiedPercentN,
        int ingredientsWithSpecifiedPercentSum,
        int ingredientsWithUnspecifiedPercentN,
        int ingredientsWithUnspecifiedPercentSum,
        int knownIngredientsN,
        String origins,
        List<String> originsHierarchy,
        String originsLc,
        List<String> originsTags,
        String traces,
        List<Object> tracesHierarchy, // Mixed types (empty object or string)
        String tracesLc,
        List<Object> tracesTags, // Mixed types (empty object or string)
        int unknownIngredientsN,
        Map<String, String> ingredientsTextByLanguage,
        Map<String, String> ingredientsTextWithAllergensByLanguage
) {}
