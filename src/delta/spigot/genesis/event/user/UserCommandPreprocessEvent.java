package delta.spigot.genesis.event.user;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.event.Listener;

public class UserCommandPreprocessEvent extends Listener
{
	public UserCommandPreprocessEvent(GenesisPlugin plugin) {
		super(plugin);
	}

	@EventHandler (priority = EventPriority.HIGH)
	public void onCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		List<String> commands = new ArrayList<String>();
		for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins())
			for (String command : plugin.getDescription().getCommands().keySet())
				commands.add(command);
		
		if (!commands.contains(event.getMessage().replaceAll("/", ""))) {
			event.getPlayer().sendMessage("message");
			event.setCancelled(true);
		}
	}
}