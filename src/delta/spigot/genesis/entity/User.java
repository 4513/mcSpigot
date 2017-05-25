package delta.spigot.genesis.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import delta.spigot.genesis.Genesis;
import delta.spigot.genesis.command.ICommandSource;

public class User extends PlayerCharacter implements ICommandSource
{
	public File userFile = new File("plugins" + File.separator + "Genesis" + File.separator + "users" + File.separator + this.getName(true) + ".yml");
	private YamlConfiguration confFile = YamlConfiguration.loadConfiguration(userFile);
	public static List<User> users;
	private boolean enabledChat = true;
	private List<PlayerCharacter> chatWith = new ArrayList<PlayerCharacter>();
	
	public User(Player player) {
		super.player = player;
		super.nickName = player.getName();
	}

	public static List<User> listOfUsers;
	
	public void enableChat(boolean value) {
		this.enabledChat = value;
	}
	
	public boolean isEnabledChat() {
		return enabledChat;
	}
	
	public List<PlayerCharacter> getChatWith() {
		return this.chatWith;
	}
	
	public void setChatWith(List<PlayerCharacter> list) {
		this.chatWith = list;
	}
	
	public void sendMessage(String message) {
		delta.spigot.genesis.log.I18n.sendMessage(this, message);
	}
	
	public void register() throws IOException {
		if (isRegistered()) {
			getConfig().set("Location.LastIP", getIPAddress());
			getConfig().set("Move.Speed.Walk", ((Math.sqrt(getConfig().getDouble("Move.SpeedTime"))/45000) + Genesis.configFile.getDouble("UC.Move.Speed.Walk")) <= 0.99 ? ((Math.sqrt(getConfig().getDouble("Move.SpeedTime"))/45000) + Genesis.configFile.getDouble("UC.Move.Speed.Walk")) : 0.99);
			if (getConfig().getBoolean("Move.Speed.Allow"))
				setWalkSpeed((float) getConfig().getDouble("Move.Speed.Walk"));
		} else {
			userFile.createNewFile();
			
			getConfig().set("Location.FirstIP", getIPAddress());
			getConfig().set("Location.LastIP", getIPAddress());
			getConfig().set("EntityData.UUID", this.getUniqueId().toString());
			getConfig().set("EntityData.Passwd", "");
			getConfig().set("Mode.GameMode", Genesis.configFile.getBoolean("UC.Mode.GameMode"));
			getConfig().set("Mode.Fly", Genesis.configFile.getBoolean("UC.Mode.Fly"));
			getConfig().set("Move.Speed.Allow", Genesis.configFile.getBoolean("UC.Move.Speed.Allow"));
			getConfig().set("Move.Speed.Walk", Genesis.configFile.getDouble("UC.Move.Speed.Walk"));
			getConfig().set("Move.Jumps", 0);
			getConfig().set("Move.JumpsTMP", 1);
			getConfig().set("Move.Energy", 0);
			getConfig().set("Permissions", Genesis.configFile.getList("defaultPermissions"));
		}
		
		getConfig().save(userFile);
		
		this.player.setMaxHealth(20.0);
	}
	
	public boolean isRegistered() {
		if (!userFile.exists()) return false;
		else return true;
	}
	
	@Override
	public boolean getAllowFlight() {
		return getConfig().getBoolean("Mode.Fly");
	}
	
	@Override
	@Deprecated
	public GameMode getGameMode() {
		return super.getGameMode();
	}
	
	@SuppressWarnings("deprecation")
	public GameMode getGM() {
		return GameMode.getByValue(getConfig().getInt("Mode.GameMode"));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void setGameMode(GameMode gameMode) {
		super.setGameMode(gameMode);
		getConfig().set("Mode.GameMode", gameMode.getValue());
		try {
			getConfig().save(userFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setAllowFly(boolean value) throws IOException {
		super.setAllowFlight(value);
		getConfig().set("Mode.Fly", value);
		getConfig().save(userFile);
	}
	
	@Override
	@Deprecated
	public void setAllowFlight(boolean value) {
		try {
			setAllowFly(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public YamlConfiguration getConfig() {
		return this.confFile;
	}
	
	public boolean isAuthorized(String permission) {
		permission = permission.toLowerCase();
		
		if (getConfig().getList("Permissions").contains(permission))
			return true;
		
		List<String> groupsOfPlayer = new ArrayList<String>(getConfig().getStringList("Groups"));
		for (int i = 0; i < groupsOfPlayer.size(); i++)
			if (getGroupPermissions(groupsOfPlayer.get(i)).contains(permission)) 
				return true;
		
		String tmp = permission;
		permission = permission.replaceAll(".\\*", "");
		if (permission.replaceAll(".", "").length() +1 <= permission.length())
			for (int i = permission.length()-1; i >= 0; i--)
				if (permission.charAt(i) == '.')
					return isAuthorized(permission.substring(0, i+1) + "*");
		
		return (this.getPlayer().hasPermission(tmp) && !this.getPlayer().isOp());
	}
	
	private ArrayList<String> getGroupPermissions(String group) {
		YamlConfiguration file = YamlConfiguration.loadConfiguration(Genesis.permissionsFile);
		List<String> perms = new ArrayList<String>(file.getStringList("groups." + group + ".permissions"));
		List<String> inher = new ArrayList<String>(file.getStringList("groups." + group + ".inheritance"));
		for (int i = 0; i < inher.size(); i++) {
			List<String> tmp = new ArrayList<String>(getGroupPermissions(inher.get(i).toString()));
			perms.addAll(tmp);
		}
		return (ArrayList<String>) perms;
	}
	
	/*public void ban(User admin, String reason) {
		
	}
	
	public void ban(User admin, String reason/*, Time time*) {
		
	}*/
}
