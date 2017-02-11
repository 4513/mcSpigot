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
	
		User player = new User(event.getPlayer());
		if(player.getLocation().getSubtract(0,1,0).getBlock().getType() != Material.AIR) {
		
			
		
		}
	}
}
