package delta.spigot.genesis.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerPreLoginEvent;

import delta.spigot.genesis.GenesisPlugin;

@SuppressWarnings("deprecation")
public class PlayerPreLoginListener extends Listener
{
	public PlayerPreLoginListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onPreLoginEvent(PlayerPreLoginEvent event) {
		
	}
}