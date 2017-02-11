package delta.spigot.genesis.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;

import delta.spigot.genesis.GenesisPlugin;

public class PlayerQuitListener extends Listener
{
	public PlayerQuitListener(GenesisPlugin plugin) {
		super(plugin);
	}

	@EventHandler (priority = EventPriority.HIGH)
	public void onQuitEvent(PlayerQuitEvent event) {
		
	}
}