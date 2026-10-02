package lei.greg.mixin;

import RandomUtils.Debouncer;
import lei.greg.events.*;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class SoundHandlerMixin {
    @Unique private final Debouncer watchedBeamDebounce = new Debouncer();


    @Inject(method = "onPlaySound", at = @At("HEAD"))
    private void onPlaySound(PlaySoundS2CPacket packet, CallbackInfo ci) {
        Identifier soundId = packet.getSound().value().id();

        if (soundId.toString().equals("minecraft:entity.evoker.prepare_summon") && watchedBeamDebounce.canFire()){
            EvokerPrepareSummonEvent.Companion.getEVENT().invoker().onChatMessage();
        }
        if(soundId.toString().equals("minecraft:item.trident.thunder") && watchedBeamDebounce.canFire()){
            TridentThunderEvent.Companion.getEVENT().invoker().onChatMessage();
        }
    }
}