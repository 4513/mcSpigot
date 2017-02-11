package delta.spigot.genesis.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;

public class PlayerJoinListener extends Listener
{
	public PlayerJoinListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onJoinEvent(PlayerJoinEvent event) {
		User player = new User(event.getPlayer());
		player.register();
	}
}