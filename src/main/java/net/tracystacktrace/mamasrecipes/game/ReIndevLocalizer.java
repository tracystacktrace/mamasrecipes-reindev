package net.tracystacktrace.mamasrecipes.game;

import com.fox2code.foxloader.registry.GameRegistry;
import com.google.gson.JsonObject;
import net.minecraft.common.item.Item;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.minecraft.common.recipe.BlastFurnaceRecipes;
import net.minecraft.common.recipe.CraftingManager;
import net.minecraft.common.recipe.FurnaceRecipes;
import net.minecraft.common.recipe.RefridgifreezerRecipes;
import net.tracystacktrace.mamasrecipes.MamasRecipes;
import net.tracystacktrace.mamasrecipes.bridge.IEnvironment;
import net.tracystacktrace.mamasrecipes.constructor.RecipeProcessException;
import net.tracystacktrace.mamasrecipes.constructor.item.ItemDescription;
import net.tracystacktrace.mamasrecipes.constructor.item.KeyedItemDescription;
import net.tracystacktrace.mamasrecipes.constructor.recipe.IRecipeDescription;
import net.tracystacktrace.mamasrecipes.constructor.recipe.RecipeDirectProcessing;
import net.tracystacktrace.mamasrecipes.constructor.recipe.RecipeShaped;
import net.tracystacktrace.mamasrecipes.constructor.recipe.RecipeShapeless;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class ReIndevLocalizer implements IEnvironment {

    private @Nullable Integer processIntegerName(@NotNull String name) {
        try {
            final int rawID = Integer.parseInt(name);
            if (rawID < 1) {
                return null;
            }
            final Item targetItem = Items.ITEMS_LIST[rawID];
            return targetItem != null ? targetItem.itemID : null;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
            return null;
        }
    }

    private @Nullable Integer processVanillaName(@NotNull String name, int meta, int count) {
        return Arrays.stream(Items.ITEMS_LIST)
                .filter(Objects::nonNull)
                .filter(i -> {
                    try {
                        return name.equals(i.getItemNameIS(new ItemStack(i.itemID, meta, count))) || name.equals(i.getItemName());
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .map(i -> i.itemID)
                .orElse(null);
    }

    private @Nullable Integer processPrefixedName(@NotNull String name) {
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
    public @Nullable String getItemIDFromName(@NotNull String name, int meta, int count) {
        //check for clear int ID
        if (name.matches("\\d+")) {
            final Integer processed = this.processIntegerName(name);
            if (processed != null) {
                return String.valueOf(processed.intValue());
            }
            return null;
        }

        //ore dict detection
        if (name.startsWith("reindev:")) {
            final String data = name.split(":")[1];
            if (ReIndevOreDict.validateOreDict(data)) {
                return "$oreDict:" + data;
            }
        }

        //foxloader prefix based search
        if (name.contains(":")) {
            final Integer processed = this.processPrefixedName(name);
            if (processed != null) {
                return String.valueOf(processed.intValue());
            }
            return null;
        }

        //rind naming based
        if (name.startsWith("tile.") || name.startsWith("item.")) {
            final Integer processed = this.processVanillaName(name, meta, count);
            if (processed != null) {
                return String.valueOf(processed.intValue());
            }
            return null;
        }

        return null;
    }

    @Override
    public @Nullable String getItemID(int id) {
        if (id < 1 || id >= Items.ITEMS_LIST.length) {
            return null;
        }
        try {
            final Item item = Items.ITEMS_LIST[id];
            return String.valueOf(item.itemID);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
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
                    collector[index++] = MamasRecipes.convertLoose(entry.getValue());
                }

                CraftingManager.getInstance().addRecipe(MamasRecipes.convertStrict(recipe.getResult()), collector);
                return;
            }

            case "crafting_shapeless": {
                final RecipeShapeless recipeShapeless = (RecipeShapeless) recipe;

                final Object[] collector = new Object[recipeShapeless.getInput().length];

                for (int i = 0; i < collector.length; i++) {
                    collector[i] = MamasRecipes.convertLoose(recipeShapeless.getInput()[i]);
                }

                CraftingManager.getInstance().addShapelessRecipe(MamasRecipes.convertStrict(recipe.getResult()), collector);
                return;
            }

            case "furnace": {
                final RecipeDirectProcessing recipeFurnace = (RecipeDirectProcessing) recipe;

                final ItemStack input = MamasRecipes.convertStrict(recipeFurnace.getInput());
                final ItemStack output = MamasRecipes.convertStrict(recipeFurnace.getResult());

                FurnaceRecipes.instance.addSmelting(FurnaceRecipes.instance.construct(input.getItemID(), input.getItemDamage()), output);
                return;
            }

            case "forge": {
                final RecipeDirectProcessing recipeFurnace = (RecipeDirectProcessing) recipe;

                final ItemStack input = MamasRecipes.convertStrict(recipeFurnace.getInput());
                final ItemStack output = MamasRecipes.convertStrict(recipeFurnace.getResult());

                BlastFurnaceRecipes.instance.addSmelting(BlastFurnaceRecipes.instance.construct(input.getItemID(), input.getItemDamage()), output);
                return;
            }

            case "refridgifreezer": {
                final RecipeDirectProcessing recipeFurnace = (RecipeDirectProcessing) recipe;

                final ItemStack input = MamasRecipes.convertStrict(recipeFurnace.getInput());
                final ItemStack output = MamasRecipes.convertStrict(recipeFurnace.getResult());

                RefridgifreezerRecipes.instance.addSmelting(RefridgifreezerRecipes.instance.construct(input.getItemID(), input.getItemDamage()), output);
                return;
            }
        }

        System.out.println("UNKNOWN " + recipe.getType() + " -> " + recipe.getName());
    }

    @Override
    public String[] getCustomItemAttributes() {
        return new String[]{"displayName"};
    }

    @Override
    public void processCustomItemAttribute(@NotNull ItemDescription target, @NotNull String attribute, @Nullable Object value) {
        if (attribute.equals("displayName")) {
            target.setDisplayName((value instanceof String) ? ((String) value) : String.valueOf(value));
        }
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
            case "forge" -> RecipeDirectProcessing.fromJson(this, object, "forge");
            case "refridgifreezer" -> RecipeDirectProcessing.fromJson(this, object, "refridgifreezer");
            default -> null;
        };
    }
}
