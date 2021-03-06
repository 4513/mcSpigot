package delta.spigot.genesis.event.block;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

import delta.spigot.genesis.Genesis;
import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.event.Listener;
import delta.spigot.genesis.structure.Tree;

public class BlockPlaceListener extends Listener
{
	public BlockPlaceListener(GenesisPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event) {
		User player = new User(event.getPlayer());
		Material type = event.getBlock().getType();
		
		Logger.getLogger("Minecraft").info("si placol blok cujes");
		
		// check if player has permission first
		if (!player.isAuthorized("genesis.modifyworld.placeBlock")) {
			//event.setCancelled(true);
		} else {
			int x = event.getBlock().getX();
			int y = event.getBlock().getY();
			int z = event.getBlock().getZ();
			
			DateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");
			Date date = new Date();
			String currentTime = format.format(date).toString();
			
			YamlConfiguration file = YamlConfiguration.loadConfiguration(Genesis.rbFile);
			file.set("users.x" + x + "y" + y + "z" + z + "." + currentTime + "." + player.getName(), type.toString() + "%breakBlock");
			
			try {
	      		file.save(Genesis.rbFile);
	    	} catch (IOException e) {
	       		e.printStackTrace();
	  		}
			
			// register Tree
			if (event.getBlock().getType().equals(Material.SAPLING))
				new Tree(event.getBlock().getLocation(), ((org.bukkit.material.Tree) event.getBlock().getState().getData()).getSpecies());
		}
	}
}