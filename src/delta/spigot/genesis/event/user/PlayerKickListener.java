package delta.spigot.genesis.event.user;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerKickEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.event.Listener;

public class PlayerKickListener extends Listener
{
	public PlayerKickListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerKick(PlayerKickEvent event) {
		
	}
}