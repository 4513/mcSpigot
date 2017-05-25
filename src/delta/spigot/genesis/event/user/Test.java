package delta.spigot.genesis.event.user;

import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.event.Listener;

public class Test extends Listener
{
	public Test(GenesisPlugin plugin) {
		super(plugin);
	}
	
	public void onNameTag(AsyncPlayerReceiveNameTagEvent event) {
		if (event.getPlayer().toString().equalsIgnoreCase("sir")) {
			event.setTag("6i012");
		}
	}
}