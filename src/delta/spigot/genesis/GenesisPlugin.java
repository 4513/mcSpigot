package delta.spigot.genesis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import delta.spigot.genesis.command.CommandSource;

public interface GenesisPlugin
{
	void onEnable();
	void onDisable();
	void reload();
	
	boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args);
	boolean onGenesisCommand(CommandSource source, Command command, String label, String[] args, String prefix, String pathToCmd);
	
	void registerListeners();
	void registerFiles();
}