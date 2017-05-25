package delta.spigot.genesis;

import static delta.spigot.genesis.log.I18n.debug;
import static delta.spigot.genesis.log.I18n.tl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import delta.spigot.genesis.command.CommandSource;
import delta.spigot.genesis.database.Database;
import delta.spigot.genesis.entity.NPC;
import delta.spigot.genesis.entity.PlayerCharacter;
import delta.spigot.genesis.event.block.BlockBreakListener;
import delta.spigot.genesis.event.block.BlockPlaceListener;
import delta.spigot.genesis.event.server.ServerCommandEvent;
import delta.spigot.genesis.event.user.AsyncUserChatEvent;
import delta.spigot.genesis.event.user.UserCommandPreprocessEvent;
import delta.spigot.genesis.event.user.UserInteractEntityEvent;
import delta.spigot.genesis.event.user.UserJoinEvent;
import delta.spigot.genesis.event.user.UserKickEvent;
import delta.spigot.genesis.event.user.UserLoginEvent;
import delta.spigot.genesis.event.user.UserMoveEvent;
import delta.spigot.genesis.event.user.UserPreLoginEvent;
import delta.spigot.genesis.event.user.UserQuitEvent;
import delta.spigot.genesis.structure.Tree;

public final class Genesis extends JavaPlugin implements GenesisPlugin
{
	public static final Logger logger = Logger.getLogger("Minecraft");
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
	public static List<NPC> listOfNPC = new ArrayList<NPC>();
	public static final File debug = new File("plugins" + File.separator + "Genesis" + File.separator + "debug.yml");
	public static boolean debugMode = false;
	public static Map<String, Database> databases = new HashMap<String, Database>();
	public static List<Tree> listOfTrees = new ArrayList<Tree>();

	@Override
	public final void onEnable() {
		getConfig().options().copyDefaults(true);
		debugMode = getConfig().getBoolean("DebugMode");
		saveConfig();
		try {
			if (!debug.exists()) debug.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			registerFiles();
		} catch (IOException e) {
			debug(tl("error_registerFiles"), e.getMessage());
		}
		registerListeners();
		
		GenesisRunnable gr = new GenesisRunnable(this, "registerTrees", null);
		gr.runTaskTimer(this, 1000, 5000);
		
		/*try {
			registerDatabases();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
		/*ProtocolLibrary.getProtocolManager().addPacketListener(
				new PacketAdapter(this, ConnectionSide.SERVER_SIDE, Packets.Server.CHAT) {
					@Override
					public void onPacketSending(PacketEvent event) {
						String message = event.getPacket().getStrings().read(0);
						if ("Unknown command. Type \"help\" for help.".equals(ChatColor
                                .stripColor(message))) {
							event.getPacket().getStrings().write(0, "Wrong idksadas");
						}
					}
				}
			);*/
		
		/*MinecraftServer mcServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer wServer = ((CraftWorld) Bukkit.getServer().getWorlds().get(0)).getHandle();
		npc = new EntityPlayer(mcServer, wServer, new GameProfile(UUID.randomUUID(), "TestNPC"),
				new PlayerInteractManager(wServer));*/
		
	}

	@Override
	public final void onDisable() {
		
	}

	@Override
	public final void reload() {
		logger.warning("[Genesis] Plugin " + pdFile.getName() + " v" + pdFile.getVersion() + " is reloading.");
		onDisable();
		onEnable();
	}

	@Override
	public final boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
		return onGenesisCommand(new CommandSource(commandSender), command, commandLabel, args, "genesis.", "delta.spigot.genesis.command.", this.getClass().getClassLoader());
	}

	@Override
	public final boolean onGenesisCommand(CommandSource source, Command command, String label, String[] args, String prefix,
			String commandPath, ClassLoader classLoader) {
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
	public final void registerListeners() {
		pm.registerEvents(new UserPreLoginEvent(this), this);
		pm.registerEvents(new UserLoginEvent(this), this);
		pm.registerEvents(new UserJoinEvent(this), this);
		pm.registerEvents(new UserQuitEvent(this), this);
		pm.registerEvents(new UserKickEvent(this), this);
		pm.registerEvents(new UserMoveEvent(this), this);
		pm.registerEvents(new UserCommandPreprocessEvent(this), this);
		pm.registerEvents(new AsyncUserChatEvent(this), this);
		pm.registerEvents(new UserInteractEntityEvent(this), this);
		
		pm.registerEvents(new BlockPlaceListener(this), this);
		pm.registerEvents(new BlockBreakListener(this), this);
		
		pm.registerEvents(new ServerCommandEvent(this), this);
	}

	@Override
	public final void registerFiles() throws IOException {
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
	
	public static final void registerDatabases() throws SQLException, ClassNotFoundException {
		databases.put("root", new Database("localhost", 3306, "GenesisDB", "genesis", "" + Math.random()*(Math.random()*9999)+51));
	}
}
