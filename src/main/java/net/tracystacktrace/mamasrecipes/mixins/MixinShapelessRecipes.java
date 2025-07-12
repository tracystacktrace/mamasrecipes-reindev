package net.tracystacktrace.mamasrecipes.mixins;

import net.minecraft.common.entity.inventory.InventoryCrafting;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.recipe.ShapelessRecipes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapelessRecipes.class)
public class MixinShapelessRecipes {
    @Shadow
    @Final
    private ItemStack output;

    @Inject(method = "getCraftingResult", at = @At("HEAD"), cancellable = true)
    private void mamasrecipes$injectDisplayName(
            InventoryCrafting craftingInventory,
            CallbackInfoReturnable<ItemStack> cir
    ) {
        cir.setReturnValue(this.output.copy());
    }
}
