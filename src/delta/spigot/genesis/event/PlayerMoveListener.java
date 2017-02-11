package delta.spigot.genesis.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.Genesis;

public class PlayerMoveListener extends Listener
{
	public PlayerMoveListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onMoveEvent(PlayerMoveEvent event) {
	
		User player = new User(event.getPlayer());
		
		int countJumps = 0;
		
		if(player.getLocation().getSubtract(0,1,0).getBlock().getType() != Material.AIR) {
		
			countJumps = player.getConfig().getInt(player.getName() + ".Jumps");
			player.getConfig().set(player.getName() + ".Jumps", countJumps+1);
			
			try {
				player.getConfig().save(Genesis.usersFile);
	   		} catch (IOException e) {
				e.printStackTrace();
	   		}
	   		 
		}
	}
}
