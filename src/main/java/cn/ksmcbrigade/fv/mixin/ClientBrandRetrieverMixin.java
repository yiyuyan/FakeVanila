package cn.ksmcbrigade.fv.mixin;

import cn.ksmcbrigade.fv.FakeVanilla;
import net.fabricmc.loader.impl.game.minecraft.Hooks;
import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMixin {
    @Inject(method = "getClientModName",at = @At("RETURN"),cancellable = true,remap = false)
    private static void name(CallbackInfoReturnable<String> cir){
        if(FakeVanilla.config.get()){
            cir.setReturnValue(Hooks.VANILLA);
        }
    }
}
