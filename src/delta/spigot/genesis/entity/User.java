package delta.spigot.genesis.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import delta.spigot.genesis.Genesis;
import delta.spigot.genesis.command.ICommandSource;

public class User extends PlayerCharacter implements ICommandSource
{
	private YamlConfiguration confFile = YamlConfiguration.loadConfiguration(Genesis.usersFile);
	
	public User(Player player) {
		super(player);
	}

	public static List<User> listOfUsers;
	
	public void sendMessage(String message) {
		delta.spigot.genesis.log.I18n.sendMessage(this, message);
	}
	
	public void register() {
		YamlConfiguration file = YamlConfiguration.loadConfiguration(Genesis.usersFile);
		if (isRegistered()) {
			file.set(this.getName(true) + ".LastPlayedIP", this.getIPAddress());
		} else {
			file.set(this.getName(true) + ".IP", this.getIPAddress());
			file.set(this.getName(true) + ".LastPlayedIP", this.getIPAddress());
			file.set(this.getName(true) + ".GameMode", false);
			file.set(this.getName(true) + ".FlyAllow", false);
			file.set(this.getName(true) + ".Speed.Run", 3.5);
			file.set(this.getName(true) + ".Speed.Walk", 2.0);
			file.set(this.getName(true) + ".Speed.Sneak", 1.0);
			file.set(this.getName(true) + ".JumpPower", 1);
			file.set(this.getName(true) + ".SpeedTime", 0);
			file.set(this.getName(true) + ".Jumps", 0);
			file.set(this.getName(true) + ".JumpsTMP", 1);
			file.set(this.getName(true) + ".Energy", "");
			try {
				file.save(Genesis.usersFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.setWalkSpeed((float) 0.0);
		
		if (isAuthorized("janeviemus")) {
			Logger.getLogger("Minecraft").info("isAuthorized();");
		}
	}
	
	public boolean isRegistered() {
		YamlConfiguration file = YamlConfiguration.loadConfiguration(Genesis.usersFile);
		if (file.contains(this.getName(true) + ".IP")) return true;
		else return false;
	}
	
	public YamlConfiguration getConfig() {
		return this.confFile;
	}
	
	public boolean isAuthorized(String permission) {
		YamlConfiguration file = YamlConfiguration.loadConfiguration(Genesis.permissionsFile);
		if (file.getList("users." + this.getName(true) + ".permissions").contains(permission)) {
			return true;
		}
		List<String> groupsOfPlayer = new ArrayList<String>(file.getStringList("users." + this.getName().toLowerCase() + ".groups"));
		for (int i = 0; i < groupsOfPlayer.size(); i++) {
			if (getGroupPermissions(groupsOfPlayer.get(i)).contains(permission)) {
				return true;
			}
		}
		return false;
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
	
	public void ban(User admin, String reason) {
		
	}
	
	public void ban(User admin, String reason/*, Time time*/) {
		
	}
}
