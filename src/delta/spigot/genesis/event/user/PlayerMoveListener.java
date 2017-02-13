package delta.spigot.genesis.event.user;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.event.Listener;
import delta.spigot.genesis.Genesis;

public class PlayerMoveListener extends Listener
{
	public PlayerMoveListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onMoveEvent(PlayerMoveEvent event) {
		
		User player = new User(event.getPlayer());
		
		if (player.isSprinting()) {
			player.getConfig().set(player.getName() + ".SpeedTime", player.getConfig().getInt(player.getName() + ".SpeedTime") + 1);
		}
		
		int countJumps = player.getConfig().getInt(player.getName() + ".JumpsTMP");
		
		if(event.getFrom().getY()<event.getTo().getY()){
			countJumps = countJumps+1;
		}
		
		player.getConfig().set(player.getName() + ".JumpsTMP", countJumps);
		
		if(player.getConfig().getInt(player.getName() + ".JumpsTMP")%7 == 0) {
			player.getConfig().set(player.getName() + ".JumpsTMP", 1);
			player.getConfig().set(player.getName() + ".Jumps", player.getConfig().getInt(player.getName() + ".Jumps")+1);
		}
		
		try {
    			player.getConfig().save(Genesis.usersFile);
  		} catch (IOException e) {
    			e.printStackTrace();
		}
		
	}
}