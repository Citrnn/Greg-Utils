package lei.greg.features

import lei.greg.GregUtils
import lei.greg.Utils.notifyChat
import lei.greg.events.EvokerPrepareSummonEvent
import lei.greg.events.RaidFinishedEvent
import lei.greg.events.TnaBossEntered
import lei.greg.events.TridentThunderEvent
import net.minecraft.client.MinecraftClient

object WatchedVisualiser {
    var isInGreg: Boolean = false
    var beamCounter: Int = 0

    fun register() {
        EvokerPrepareSummonEvent.EVENT.register {
            if(!isInGreg) return@register
            notifyChat("§fBeam: " + currentBeam())
        }

        TridentThunderEvent.EVENT.register {
            if(!isInGreg) return@register
            var currbeam = currentBeam()
            notifyChat("§fBeam: " + currbeam + " BAANG!")

            if(currbeam != 3){
                notifyChat("BANGER BEAM OUT OF SYNC! RESETTING COUNTER")
                beamCounter = 0
            }
        }

        TnaBossEntered.EVENT.register {
            notifyChat("Entered boss")
            cleanup()
            isInGreg = true
        }

        RaidFinishedEvent.EVENT.register {
            notifyChat("Raid finished")
            cleanup()
            isInGreg = false
        }
    }

    private fun currentBeam(): Int{
        beamCounter = if (beamCounter == 3) 1 else beamCounter + 1
        return beamCounter
    }

    private fun cleanup(){
        beamCounter = 0
    }
}