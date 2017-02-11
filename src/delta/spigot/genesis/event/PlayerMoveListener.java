package delta.spigot.genesis.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import delta.spigot.genesis.GenesisPlugin;

public class PlayerMoveListener extends Listener
{
	public PlayerMoveListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onMoveEvent(PlayerMoveEvent event) {
		
	}
}