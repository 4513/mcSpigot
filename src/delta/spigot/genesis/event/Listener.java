package delta.spigot.genesis.event;

import delta.spigot.genesis.GenesisPlugin;

public class Listener implements org.bukkit.event.Listener
{
	protected GenesisPlugin plugin;
	
	public Listener(GenesisPlugin plugin) {
		this.plugin = plugin;
	}
}