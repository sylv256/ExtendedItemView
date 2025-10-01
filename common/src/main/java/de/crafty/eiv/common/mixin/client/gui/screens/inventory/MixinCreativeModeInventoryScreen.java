package de.crafty.eiv.common.mixin.client.gui.screens.inventory;

import de.crafty.eiv.common.overlay.ItemViewOverlay;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class MixinCreativeModeInventoryScreen extends AbstractContainerScreen<CreativeModeInventoryScreen.ItemPickerMenu> {


    public MixinCreativeModeInventoryScreen(CreativeModeInventoryScreen.ItemPickerMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void injectSearchBar$0(CharacterEvent characterEvent, CallbackInfoReturnable<Boolean> cir){
        if(ItemViewOverlay.SEARCHBAR.isFocused())
            cir.setReturnValue(ItemViewOverlay.SEARCHBAR.charTyped(characterEvent));
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void injectSearchBar$1(KeyEvent keyEvent, CallbackInfoReturnable<Boolean> cir){
        if(ItemViewOverlay.SEARCHBAR.isFocused())
            cir.setReturnValue(ItemViewOverlay.SEARCHBAR.keyPressed(keyEvent) || super.keyPressed(keyEvent));
    }
}
