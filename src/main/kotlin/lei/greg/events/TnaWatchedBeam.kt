package lei.greg.events

import lei.greg.GregUtils
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

fun interface TnaWatchedBeam {

    fun onChatMessage(beamNr: Int)

    companion object {
        val EVENT: Event<TnaWatchedBeam> = EventFactory.createArrayBacked(TnaWatchedBeam::class.java) { listeners ->
            TnaWatchedBeam { beamNr -> listeners.forEach {
                it.onChatMessage(beamNr)
            } }
        }
    }
}