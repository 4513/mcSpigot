package delta.spigot.genesis.event.npc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import delta.spigot.genesis.Genesis;
import delta.spigot.genesis.event.Listener;

public class NPCDeathEvent extends Listener
{
	public NPCDeathEvent(Genesis plugin) {
		super(plugin);
	}

	@EventHandler (priority = EventPriority.NORMAL)
	public void onDeathEvent(net.citizensnpcs.api.event.NPCDeathEvent event) {
		
	}
}