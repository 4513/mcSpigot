package delta.spigot.genesis.event.user;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.event.Listener;

public class AsyncUserChatEvent extends Listener
{
	public AsyncUserChatEvent(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onAsyncChatEvent(AsyncPlayerChatEvent event) {
		User player = new User(event.getPlayer());
		
		if (!player.isEnabledChat()) {
			if (event.getMessage().equalsIgnoreCase("exit")) player.enableChat(true);
			
			player.sendMessage("asdasda");
			event.setCancelled(true);
		}
	}
}