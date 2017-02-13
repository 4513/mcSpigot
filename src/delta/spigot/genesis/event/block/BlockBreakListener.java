package delta.spigot.genesis.event.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import delta.spigot.genesis.GenesisPlugin;
import delta.spigot.genesis.entity.User;
import delta.spigot.genesis.event.Listener;

public class BlockBreakListener extends Listener
{
	public BlockBreakListener(GenesisPlugin plugin) {
		super(plugin);
	}

	@EventHandler (priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event) {
		User player = new User(event.getPlayer());
		
		if (!player.isAuthorized("genesis.modifyworld.break"))
			event.setCancelled(true);
		
		int x = event.getBlock().getX();
                int y = event.getBlock().getY();
                int z = event.getBlock().getZ();
                
                Material type = event.getBlock().getType();
    
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy - hh.mm.ss");
                Date date = new Date();
                
                String currentTime = format.format(date).toString();

                YamlConfiguration file = YamlConfiguration.loadConfiguration(Genesis.rbFile);
                file.set(x+","+y+","+z+"."+currentTime+"."+type, "removedBlock");

                try {
                        file.getConfig().save(Genesis.usersFile);
                } catch (IOException e) {
                        e.printStackTrace();
                }
		
	}
}
