package net.tracystacktrace.mamasrecipes.game;

import com.google.gson.JsonObject;
import net.minecraft.common.item.Items;
import net.minecraft.common.recipe.CraftingManager;
import net.tracystacktrace.mamasrecipes.MamasRecipes;
import net.tracystacktrace.mamasrecipes.bridge.ILocalization;
import net.tracystacktrace.mamasrecipes.constructor.RecipeProcessException;
import net.tracystacktrace.mamasrecipes.constructor.item.KeyedItemDescription;
import net.tracystacktrace.mamasrecipes.constructor.recipe.IRecipeDescription;
import net.tracystacktrace.mamasrecipes.constructor.recipe.RecipeFurnace;
import net.tracystacktrace.mamasrecipes.constructor.recipe.RecipeShaped;
import net.tracystacktrace.mamasrecipes.constructor.recipe.RecipeShapeless;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class ReIndevLocalizer implements ILocalization {
    @Override
    public @Nullable Integer getIDFromName(@NotNull String name) {
        return Arrays.stream(Items.ITEMS_LIST).filter(Objects::nonNull).filter(i -> i.getItemName().equals(name)).map(i -> i.itemID).findFirst().orElse(null);
    }

    @Override
    public boolean isValidItemID(int id) {
        return id > 0 && id < Items.ITEMS_LIST.length;
    }

    @Override
    public void addRecipe(@NotNull IRecipeDescription recipe) {
        switch (recipe.getType()) {
            case "crafting_shaped": {
                final RecipeShaped recipeShaped = (RecipeShaped) recipe;
                Object[] collector = new Object[recipeShaped.getPattern().length + recipeShaped.getKeys().size() * 2];

                System.arraycopy(recipeShaped.getPattern(), 0, collector, 0, recipeShaped.getPattern().length);

                int index = recipeShaped.getPattern().length;
                for (Map.Entry<String, KeyedItemDescription> entry : recipeShaped.getKeys().entrySet()) {
                    collector[index++] = Character.valueOf(entry.getKey().charAt(0));
                    collector[index++] = MamasRecipes.convert(entry.getValue());
                }

                CraftingManager.getInstance().addRecipe(MamasRecipes.convert(recipe.getResult()), collector);
                return;
            }

            case "crafting_shapeless": {
                final RecipeShapeless recipeShapeless = (RecipeShapeless) recipe;

                final Object[] collector = new Object[recipeShapeless.getInput().length];

                for (int i = 0; i < collector.length; i++) {
                    collector[i] = MamasRecipes.convert(recipeShapeless.getInput()[i]);
                }

                CraftingManager.getInstance().addShapelessRecipe(MamasRecipes.convert(recipe.getResult()), collector);
                return;
            }
        }

        System.out.println(recipe.getType() + " -> " + recipe.getName());
    }

    @Override
    public boolean supportsAttribute(@NotNull String attribute) {
        return attribute.equals("displayName");
    }

    @Override
    public String[] getCustomRecipeTypes() {
        return new String[]{"forge", "refridgifreezer"};
    }

    @Override
    public IRecipeDescription processCustomRecipe(
            @NotNull String type,
            @NotNull JsonObject object
    ) throws RecipeProcessException {
        return switch (type) {
            case "forge" -> RecipeFurnace.fromJson(object, "forge");
            case "refridgifreezer" -> RecipeFurnace.fromJson(object, "refridgifreezer");
            default -> null;
        };
    }
}
