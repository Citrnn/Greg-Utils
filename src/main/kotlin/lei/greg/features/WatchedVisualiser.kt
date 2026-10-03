package lei.greg.features

import lei.greg.GregUtils
import lei.greg.Utils.notifyChat
import lei.greg.Utils.notifyTitle
import lei.greg.config.ConfigManager
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
            if (!ConfigManager.getBool("master toggle") || !ConfigManager.getBool("watched visualiser")) return@register
            var currbeam = currentBeam()
            if(ConfigManager.getBool("watched chat")) {
                notifyChat("§fBeam " + currbeam)
            }

            if(ConfigManager.getBool("watched title")) {
                if ( currbeam == 1)
                    notifyTitle(title = "§a.§7...§a.", stayTicks = 30)
                else if (currbeam == 2)
                    notifyTitle(title = "§7.§e.§7.§e.§7.", stayTicks = 30)
            }

        }

        TridentThunderEvent.EVENT.register {
            if(!isInGreg) return@register
            if (!ConfigManager.getBool("master toggle") || !ConfigManager.getBool("watched visualiser")) return@register
            var currbeam = currentBeam()
            if(ConfigManager.getBool("watched chat")) {
                notifyChat("§fBeam " + currbeam + " BANG!")
//                if (currbeam != 3) {
//                    notifyChat("BANGER BEAM OUT OF SYNC! RESETTING COUNTER")
//                }
            }

            if(ConfigManager.getBool("watched title"))  {
                notifyTitle(title = "§c!", stayTicks = 5, fadeOutTicks = 10)
            }
            cleanup()
        }

        TnaBossEntered.EVENT.register {
//            notifyChat("Entered boss")
            cleanup()
            isInGreg = true
        }

        RaidFinishedEvent.EVENT.register {
//            notifyChat("Raid finished")
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