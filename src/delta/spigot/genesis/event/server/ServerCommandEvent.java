package delta.spigot.genesis.event.server;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.event.Listener;

public class ServerCommandEvent extends Listener
{
	public ServerCommandEvent(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onCommandEvent(org.bukkit.event.server.ServerCommandEvent event) {
		List<String> commands = new ArrayList<String>();
		for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins())
			for (String command : plugin.getDescription().getCommands().keySet())
				commands.add(command);
		
		String commandName = "";
		for (int i = 0; i < event.getCommand().length(); i++) {
			if (event.getCommand().charAt(i) == ' ') break;
			else commandName = commandName + event.getCommand().charAt(i);
		}
		if (!commands.contains(commandName.replaceAll("/", ""))) {
			event.getSender().sendMessage("message");
			event.setCancelled(true);
		}
	}
}