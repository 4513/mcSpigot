package delta.spigot.genesis.event;

import java.io.IOException;

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
		
		int countJumps = player.getConfig().getInt(player.getName() + ".JumpsTMP");
		
		if(event.getFrom().getY()<event.getTo().getY()){
			countJumps = countJumps+1;
		}
		
		player.getConfig().set(player.getName() + ".JumpsTMP", countJumps);
		
		if(player.getConfig().getInt(player.getName() + ".JumpsTMP")%6 == 0) {
			player.getConfig().set(player.getName() + ".JumpsTMP", 0);
			player.getConfig().set(player.getName() + ".Jumps", player.getConfig().getInt(player.getName() + ".Jumps")+1);
		}
		
		try {
    			player.getConfig().save(Genesis.usersFile);
  		} catch (IOException e) {
    			e.printStackTrace();
		}
		
	}
}
