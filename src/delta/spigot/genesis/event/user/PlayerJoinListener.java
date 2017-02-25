package delta.spigot.genesis.event.user;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.event.Listener;

public class UserJoinEvent extends Listener
{
	public UserJoinEvent(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onJoinEvent(PlayerJoinEvent event) throws IOException {
		User player = new User(event.getPlayer());
		
		// First Join
		if (player.isRegistered()) {
			
		} else {
			
		}
		
		player.register();
	}
}
