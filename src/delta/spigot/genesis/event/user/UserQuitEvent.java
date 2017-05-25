package delta.spigot.genesis.event.user;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.event.Listener;

public class UserQuitEvent extends Listener
{
	public UserQuitEvent(GenesisPlugin plugin) {
		super(plugin);
	}

	@EventHandler (priority = EventPriority.HIGH)
	public void onQuitEvent(PlayerQuitEvent event) {
		
	}
}