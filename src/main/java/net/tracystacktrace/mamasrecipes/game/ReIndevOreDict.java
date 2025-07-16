package net.tracystacktrace.mamasrecipes.game;

import net.minecraft.common.recipe.TaggedIngredient;
import net.tracystacktrace.mamasrecipes.mixins.recipes.AccessorTaggedIngredients;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ReIndevOreDict {
    public static boolean validateOreDict(@Nullable String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return ((AccessorTaggedIngredients) null).getIngredients().containsKey(input);
    }

    public static @NotNull TaggedIngredient getFromList(@NotNull String input) {
        return ((AccessorTaggedIngredients) null).getIngredients().get(input);
    }
}
