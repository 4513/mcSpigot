package delta.spigot.genesis.event.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.event.Listener;

public class StructureGrowEvent extends Listener
{
	public StructureGrowEvent(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onStructureGrow(org.bukkit.event.world.StructureGrowEvent event) {
		
	}
}