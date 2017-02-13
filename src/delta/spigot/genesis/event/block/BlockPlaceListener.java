package delta.spigot.genesis.event.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.event.Listener;

public class BlockPlaceListener extends Listener
{
	public BlockPlaceListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event) {
		User player = new User(event.getPlayer());
		
		if (!player.isAuthorized("genesis.modifyworld.place"))
			event.setCancelled(true);
		
		
	}
}