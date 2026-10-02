package lei.greg.events

import lei.greg.GregUtils
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

fun interface EvokerPrepareSummonEvent {

    fun onChatMessage()

    companion object {
        val EVENT: Event<EvokerPrepareSummonEvent> = EventFactory.createArrayBacked(EvokerPrepareSummonEvent::class.java) { listeners ->
            EvokerPrepareSummonEvent {
                listeners.forEach { it.onChatMessage() }
            }
        }
    }
}