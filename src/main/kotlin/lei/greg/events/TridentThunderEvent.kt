package lei.greg.events

import lei.greg.GregUtils
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

fun interface TridentThunderEvent {

    fun onChatMessage()

    companion object {
        val EVENT: Event<TridentThunderEvent> = EventFactory.createArrayBacked(TridentThunderEvent::class.java) { listeners ->
            TridentThunderEvent {
                listeners.forEach { it.onChatMessage() }
            }
        }
    }
}