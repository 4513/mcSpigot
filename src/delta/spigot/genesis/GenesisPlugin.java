package delta.spigot.genesis;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import delta.spigot.genesis.command.CommandSource;

public interface GenesisPlugin
{
	void onEnable();
	void onDisable();
	void reload();
	
	boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args);
	boolean onGenesisCommand(CommandSource source, Command command, String label, String[] args, String prefix, String commandPath, ClassLoader classLoader);
	
	void registerListeners();
	void registerFiles() throws IOException;
}