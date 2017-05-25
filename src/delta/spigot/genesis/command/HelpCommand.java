package delta.spigot.genesis.command;

import static delta.spigot.genesis.log.I18n.messageDescription;
import static delta.spigot.genesis.log.I18n.messageHead;
import static delta.spigot.genesis.log.I18n.messageCol;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import delta.spigot.genesis.entity.User;

public class HelpCommand extends Command
{
	public void run(CommandSource commandSource, org.bukkit.command.Command cmd, String label, String[] args, String prefix) {
		super.run(commandSource, cmd, label, args, prefix);
		
		CommandSender sender = getSource().getSource();
		
		// No Argument
		if (args.length == 0) {
			sender.sendMessage(messageHead("Help Index", 50));
			sender.sendMessage(messageDescription("idk", 50));
			
			// Show Plugins
			Map<String, String> plugins = new HashMap<String, String>();
			Map<String, String> tmp = new HashMap<String, String>();
			for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins())
				tmp.put(plugin.getName(), "info about " + plugin.getName());
			SortedSet<String> keys = new TreeSet<String>(tmp.keySet());
			for (String key : keys)
				plugins.put(key, tmp.get(key));
			for (String message : messageCol(plugins, 15, 35, ChatColor.YELLOW, " "))
				sender.sendMessage(message);
			// Show Commands
			Map<String, String> commands = new HashMap<String, String>();
			tmp = new HashMap<String, String>();
			for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins())
				for (String command : plugin.getDescription().getCommands().keySet())
					for (String description : plugin.getDescription().getCommands().get(command).keySet())
						if (description.equalsIgnoreCase("description"))
							if (plugin.getName().equalsIgnoreCase("Genesis"))
								tmp.put(command + "..genesis", (String) plugin.getDescription().getCommands().get(command).get(description));
							else
								tmp.put(command, (String) plugin.getDescription().getCommands().get(command).get(description));
			keys = new TreeSet<String>(tmp.keySet());
			if (sender instanceof Player) {
				User user = new User((Player) sender);
				for (String key : keys)
					if (key.replace("..genesis", "").length() < key.length()) {
						if (user.isAuthorized("genesis." + key.replace("..genesis", "")))
							commands.put(key.replace("..genesis", ""), tmp.get(key));
					}
					else
						commands.put(key, tmp.get(key));
			}
			else
				for (String key : keys)
					commands.put(key.replace("..genesis", ""), tmp.get(key));
			
			for (String message : messageCol(commands, 17, 33, ChatColor.YELLOW, " /"))
				sender.sendMessage(message);
		}
		else if (args.length == 1) {
			// Check If Arg = Plugin
			boolean isPlugin = false;
			Plugin pl = null;
			for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
				if (plugin.getName().equalsIgnoreCase(args[0])) {
					isPlugin = true;
					pl = plugin;
					break;
				}
			}
			if (isPlugin) {
				sender.sendMessage(messageHead("Plugin " + pl.getName(), 50));
				sender.sendMessage(messageDescription(pl.getDescription().getDescription(), 50));
				Map<String, String> info = new HashMap<String, String>();
				if (pl.getDescription().getVersion() != null) info.put("Version", pl.getDescription().getVersion());
				if (pl.getDescription().getAuthors() != null) info.put("Authors", pl.getDescription().getAuthors().toString().replace("]", "").replace("[", ""));
				if (pl.getDescription().getPrefix() != null) info.put("Prefix", pl.getDescription().getPrefix());
				if (pl.getDescription().getWebsite() != null) info.put("WebSite", pl.getDescription().getWebsite());
				if (pl.getDescription().isDatabaseEnabled()) info.put("Database", "True");
				for (String message : messageCol(info, 10, 40, ChatColor.GREEN, " "))
					sender.sendMessage(message);
				sender.sendMessage(messageDescription("Commands of Plugin:", 50));
				info = new HashMap<String, String>();
				Map<String, String> tmp = new HashMap<String, String>();
				for (String command : pl.getDescription().getCommands().keySet())
					for (String description : pl.getDescription().getCommands().get(command).keySet())
						if (description.equalsIgnoreCase("description"))
							tmp.put(command, description);
				SortedSet<String> keys = new TreeSet<String>(tmp.keySet());
				for (String key : keys)
					info.put(key, tmp.get(key));
				for (String message : messageCol(info, 17, 33, ChatColor.YELLOW, " /"))
					sender.sendMessage(message);
			}
			else {
				
			}
		}
		/*
		for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
			Map<String, String> plugins = new HashMap<String, String>();
			plugins.put(plugin.getName(), "info about plugin");
			for (String msg : messageCol(plugins, 15, 35, ChatColor.GOLD, " "))
				s.sendMessage(msg);
		}
		for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
			Map<String, String> commands = new HashMap<String, String>();
			for (String command : plugin.getDescription().getCommands().keySet()) {
				for (String desc : plugin.getDescription().getCommands().get(command).keySet()) {
					if (desc.equalsIgnoreCase("description"))
						commands.put(command, (String) plugin.getDescription().getCommands().get(command).get(desc));
				}
			}
			for (String msg : messageCol(commands, 15, 35, ChatColor.GOLD, " /"))
				s.sendMessage(msg);
			
			/*Map<String, String> p2;
			for (int i = 0; i < p1.size(); i++) {
				s.sendMessage(p1.);
			}
			s.sendMessage(messageCol(plugin.getDescription().getCommands(), 20, 30));*/
		/*}
		
		if (s instanceof Player) {
			/*User u = new User((Player) s);
			for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
				Map<String, Map<String, Object>> commands = plugin.getDescription().getCommands();
				List<String> cmds = new ArrayList<String>(commands.keySet());
				
			}*/
		//}
		/*for (HelpTopic cmdLabel : Bukkit.getServer().getHelpMap().getHelpTopics()) {
			s.sendMessage(cmdLabel.getName() + "             " + cmdLabel.getShortText());
		}*/
	}
}