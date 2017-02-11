package delta.spigot.genesis.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;

import delta.spigot.genesis.GenesisPlugin;

public class PlayerLoginListener extends Listener
{
	public PlayerLoginListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onLoginEvent(PlayerLoginEvent event) {
		
	}
}