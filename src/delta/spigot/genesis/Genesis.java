package delta.spigot.genesis;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import delta.spigot.genesis.command.CommandSource;
import delta.spigot.genesis.entity.PlayerCharacter;
import delta.spigot.genesis.event.PlayerJoinListener;
import delta.spigot.genesis.event.PlayerLoginListener;
import delta.spigot.genesis.event.PlayerMoveListener;
import delta.spigot.genesis.event.PlayerPreLoginListener;
import static delta.spigot.genesis.log.I18n.tl;

public class Genesis extends JavaPlugin implements GenesisPlugin
{
	public final Logger logger = Logger.getLogger("Minecraft");
	public final PluginManager pm = getServer().getPluginManager();
	public final PluginDescriptionFile pdFile = this.getDescription();
	public GenesisPlugin plugin = this;
	public static final File pluginFolder = new File("plugins" + File.separator + "Genesis");
	public static final File usersFile = new File("plugins" + File.separator + "Genesis" + File.separator + "users.yml");
	public static List<PlayerCharacter> listOfPlayerCharacter;

	@Override
	public void onEnable()
	{
		logger.warning("[Genesis] Plugin " + pdFile.getName() + " v" + pdFile.getVersion() + " has been enabled.");
		getConfig().options().copyDefaults(true);
		saveConfig();
		registerFiles();
		registerListeners();
	}

	@Override
	public void onDisable()
	{
		
		logger.warning("[Genesis] Plugin " + pdFile.getName() + " v" + pdFile.getVersion() + " has been disabled.");
	}

	@Override
	public void reload()
	{
		logger.warning("[Genesis] Plugin " + pdFile.getName() + " v" + pdFile.getVersion() + " is reloading.");
		onDisable();
		onEnable();
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
		return onGenesisCommand(new CommandSource(commandSender), command, commandLabel, args, "genesis.", "delta.spigot.genesis.command");
	}

	@Override
	public boolean onGenesisCommand(CommandSource source, Command command, String label, String[] args, String prefix,
			String pathToCmd)
	{
		if (source.getSource() instanceof Player) {
		}
		return true;
	}

	@Override
	public void registerListeners()
	{
		pm.registerEvents(new PlayerPreLoginListener(this), this);
		pm.registerEvents(new PlayerLoginListener(this), this);
		pm.registerEvents(new PlayerJoinListener(this), this);
		pm.registerEvents(new PlayerMoveListener(this), this);
	}

	@Override
	public void registerFiles()
	{
		tl("usersFile");
		if (!usersFile.exists()) {
			tl("usersFileNotExists");
			try {
				usersFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
