package delta.spigot.genesis.event.user;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerPreLoginEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.event.Listener;

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