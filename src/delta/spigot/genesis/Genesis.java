package delta.spigot.genesis;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import delta.spigot.genesis.command.CommandSource;
import delta.spigot.genesis.entity.PlayerCharacter;
import delta.spigot.genesis.event.block.BlockBreakListener;
import delta.spigot.genesis.event.block.BlockPlaceListener;
import delta.spigot.genesis.event.user.UserJoinEvent;
import delta.spigot.genesis.event.user.UserKickEvent;
import delta.spigot.genesis.event.user.UserLoginEvent;
import delta.spigot.genesis.event.user.UserMoveEvent;
import delta.spigot.genesis.event.user.UserPreLoginEvent;
import delta.spigot.genesis.event.user.UserQuitEvent;

import static delta.spigot.genesis.log.I18n.tl;

public class Genesis extends JavaPlugin implements GenesisPlugin
{
	public final Logger logger = Logger.getLogger("Minecraft");
	public final PluginManager pm = getServer().getPluginManager();
	public final PluginDescriptionFile pdFile = this.getDescription();
	public GenesisPlugin plugin = this;
	public static final File pluginFolder = new File("plugins" + File.separator + "Genesis");
	public static final File config = new File("plugins" + File.separator + "Genesis" + File.separator + "config.yml");
	public static final YamlConfiguration configFile = YamlConfiguration.loadConfiguration(config);
	public static final File rbFile = new File("plugins" + File.separator + "Genesis" + File.separator + "rollback.yml");
	public static final File opFile = new File("plugins" + File.separator + "Genesis" + File.separator + "ops.yml");
	public static final File usersFolder = new File("plugins" + File.separator + "Genesis" + File.separator + "users");
	public static final File permissionsFile = new File("plugins" + File.separator + "Genesis" + File.separator + "permissions.yml");
	public static List<PlayerCharacter> listOfPlayerCharacter;

	@Override
	public void onEnable()
	{
		logger.warning("[Genesis] Plugin " + pdFile.getName() + " v" + pdFile.getVersion() + " has been enabled.");
		getConfig().options().copyDefaults(true);
		saveConfig();
		try {
			registerFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		return onGenesisCommand(new CommandSource(commandSender), command, commandLabel, args, "genesis.", "delta.spigot.genesis.command.", this.getClass().getClassLoader());
	}

	@Override
	public boolean onGenesisCommand(CommandSource source, Command command, String label, String[] args, String prefix,
			String commandPath, ClassLoader classLoader)
	{
		delta.spigot.genesis.command.Command cmd;
		
		try {
			cmd = (delta.spigot.genesis.command.Command) classLoader.loadClass(commandPath + command.getName().substring(0, 1).toUpperCase() + command.getName().substring(1).toLowerCase() + "Command").newInstance();
			cmd.run(source, command, label, args, prefix);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public void registerListeners()
	{
		pm.registerEvents(new UserPreLoginEvent(this), this);
		pm.registerEvents(new UserLoginEvent(this), this);
		pm.registerEvents(new UserJoinEvent(this), this);
		pm.registerEvents(new UserQuitEvent(this), this);
		pm.registerEvents(new UserKickEvent(this), this);
		pm.registerEvents(new UserMoveEvent(this), this);
		
		pm.registerEvents(new BlockPlaceListener(this), this);
		pm.registerEvents(new BlockBreakListener(this), this);
	}

	@Override
	public void registerFiles() throws IOException
	{
		tl("creatingFiles");
		if (!usersFolder.exists()) {
			tl("folderNotExists", "usersFile");
			usersFolder.mkdir();
		}
		if (!permissionsFile.exists()) {
			tl("fileNotExists", "permissionsFile");
			permissionsFile.createNewFile();
		}
		if (!opFile.exists()) {
			tl("fileNotExists", "opFile");
			opFile.createNewFile();
		}
		if (!rbFile.exists()) {
			tl("fileNotExists", "rbFile");
			rbFile.createNewFile();
		}
	}
}
