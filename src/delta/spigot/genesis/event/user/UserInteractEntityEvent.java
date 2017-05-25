package delta.spigot.genesis.event.user;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.event.Listener;

public class UserInteractEntityEvent extends Listener
{
	public UserInteractEntityEvent(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onInteractEntityEvent(PlayerInteractEntityEvent event) {
		User player = new User(event.getPlayer());
		if (event.getRightClicked() instanceof Player) {
			User target = new User((Player) event.getRightClicked());
			player.sendMessage("You have started dialog with: " + target.getName());
			target.sendMessage("Player " + player.getName() + " has started dialog with you");
			player.enableChat(false);
		}
	}
}