package cn.ksmcbrigade.fv.mixin;

import cn.ksmcbrigade.fv.FakeVanilla;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(MultiplayerScreen.class)
public abstract class ServerListScreenMixin extends Screen {

    @Unique
    private ButtonWidget buttonWidget;

    protected ServerListScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init",at = @At("HEAD"))
    public void init(CallbackInfo ci){
        buttonWidget = ButtonWidget.builder(Text.literal("Vanilla"),(button)->{
            try {
                FakeVanilla.config.set(!FakeVanilla.config.get());
            } catch (IOException e) {
                FakeVanilla.LOGGER.error("Failed to save the config file.",e);
            }
            this.update();
        }).position(this.width-54,10).size(50,20).build();
        this.addDrawableChild(buttonWidget);
        this.update();
    }

    @Unique
    private void update(){
        if(this.buttonWidget!=null){
            this.buttonWidget.setMessage(Text.of(FakeVanilla.config.get()?"Fabric":"Vanilla"));
        }
    }
}
