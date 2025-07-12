package net.tracystacktrace.mamasrecipes.mixins.recipes;

import net.minecraft.common.entity.inventory.InventoryCrafting;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.recipe.ShapedRecipes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipes.class)
public class MixinShapedRecipes {
    @Shadow
    @Final
    private ItemStack output;

    @Inject(method = "getCraftingResult", at = @At("HEAD"), cancellable = true)
    private void mamasrecipes$injectAddDisplayTag(
            InventoryCrafting craftingInventory,
            CallbackInfoReturnable<ItemStack> cir
    ) {
        cir.setReturnValue(this.output.copy());
    }
}
