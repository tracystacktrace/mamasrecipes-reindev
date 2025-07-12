package net.tracystacktrace.mamasrecipes.game;

import com.fox2code.foxloader.registry.GameRegistry;
import com.google.gson.JsonObject;
import net.minecraft.common.item.Item;
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

import java.util.Map;

public class ReIndevLocalizer implements ILocalization {
    @Override
    public @Nullable Integer getIDFromName(@NotNull String name) {
        //this is a protection from those who would push an int id as string
        if(name.matches("\\d+")) {
            try {
                final int rawID = Integer.parseInt(name);
                if(rawID < 1) {
                    return null;
                }
                final Item targetItem = Items.ITEMS_LIST[rawID];
                return targetItem != null ? targetItem.itemID : null;
            } catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                return null;
            }
        }

        //main code
        String prefix = "reindev"; //default
        String suffix;

        if (name.contains(":")) {
            final String[] split_1 = name.trim().split(":");
            prefix = split_1[0];
            suffix = split_1[1];
        } else {
            suffix = name.trim();
        }

        if (suffix.startsWith("item.") || suffix.startsWith("tile.")) {
            suffix = suffix.substring(5);
        }

        final Item result = GameRegistry.getRegisteredItem(prefix + ":" + suffix);
        return result != null ? result.itemID : null;
    }

    @Override
    public boolean isValidItemID(int id) {
        if(id < 1 || id >= Items.ITEMS_LIST.length) {
            return false;
        }

        try {
            return Items.ITEMS_LIST[id] != null;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return false;
        }
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
