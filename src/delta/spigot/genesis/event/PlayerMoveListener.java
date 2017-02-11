
package delta.spigot.genesis.event;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.Genesis;

public class PlayerMoveListener extends Listener
{
	public PlayerMoveListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onMoveEvent(PlayerMoveEvent event) {
		
		User player = new User(event.getPlayer());
		
		final double STILL = -0.0784000015258789;
		int countJumps = 0;
		
		Player player = event.getPlayer();
  		boolean isJumping = player.getVelocity().getY() > STILL;
		
		if(player.getVelocity().getY() > STILL) {
			countJumps++;
		}
		
		try {
    			player.getConfig().save(Genesis.usersFile);
  		} catch (IOException e) {
    			e.printStackTrace();
		}
	}
}
