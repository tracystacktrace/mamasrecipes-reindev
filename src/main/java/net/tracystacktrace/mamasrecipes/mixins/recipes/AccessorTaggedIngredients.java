package net.tracystacktrace.mamasrecipes.mixins.recipes;

import net.minecraft.common.recipe.TaggedIngredient;
import net.minecraft.common.recipe.TaggedIngredients;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.HashMap;

@Mixin(TaggedIngredients.class)
public interface AccessorTaggedIngredients {
    @Accessor("INGREDIENTS")
    HashMap<String, TaggedIngredient> getIngredients();
}
