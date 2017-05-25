package delta.spigot.genesis.event.user;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.event.Listener;

public class UserMoveEvent extends Listener
{
	public UserMoveEvent(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onMoveEvent(PlayerMoveEvent event) {
		
		User player = new User(event.getPlayer());
		
		// onSprint
		if (player.isSprinting()) {
			player.getConfig().set(player.getName(true) + ".SpeedTime", player.getConfig().getInt(player.getName(true) + ".SpeedTime") + 1);
		}
		
		// onJump
		if(event.getFrom().getY()<event.getTo().getY()){
			player.getConfig().set(player.getName(true) + ".JumpsTMP", player.getConfig().getInt(player.getName(true) + ".JumpsTMP") +1);
		}
		
		// checkJump
		if(player.getConfig().getInt(player.getName(true) + ".JumpsTMP")%7 == 0) {
			player.getConfig().set(player.getName(true) + ".JumpsTMP", 1);
			player.getConfig().set(player.getName(true) + ".Jumps", player.getConfig().getInt(player.getName() + ".Jumps")+1);
		}
		
		// onFlying
		if (player.isFlying() && !player.getAllowFlight()) {
			player.setFlying(false);
		}
		
		try {
    		player.getConfig().save(player.userFile);
  		} catch (IOException e) {
    		e.printStackTrace();
		}
		
	}
}