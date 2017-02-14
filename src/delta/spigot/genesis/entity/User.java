package delta.spigot.genesis.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		YamlConfiguration userFile = YamlConfiguration.loadConfiguration(Genesis.usersFile);
		if (isRegistered()) {
			userFile.set(this.getName(true) + ".LastPlayedIP", this.getIPAddress());
			userFile.set(this.getName(true) + ".Speed.Walk", (Math.sqrt(userFile.getDouble(this.getName(true) + ".SpeedTime"))/35000) + 0.10);
			if (userFile.getBoolean(this.getName(true) + ".Speed.Allow")) {
				this.setWalkSpeed((float) userFile.getDouble(this.getName(true) + ".Speed.Walk")); 
			}
			try {
				userFile.save(Genesis.usersFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			userFile.set(this.getName(true) + ".IP", this.getIPAddress());
			userFile.set(this.getName(true) + ".LastPlayedIP", this.getIPAddress());
			userFile.set(this.getName(true) + ".GameMode", false);
			userFile.set(this.getName(true) + ".FlyAllow", false);
			userFile.set(this.getName(true) + ".Speed.Allow", true);
			userFile.set(this.getName(true) + ".Speed.Walk", 0.1);
			userFile.set(this.getName(true) + ".SpeedTime", 0);
			userFile.set(this.getName(true) + ".Jumps", 0);
			userFile.set(this.getName(true) + ".JumpsTMP", 1);
			userFile.set(this.getName(true) + ".Energy", 0);
			try {
				userFile.save(Genesis.usersFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			YamlConfiguration permFile = YamlConfiguration.loadConfiguration(Genesis.permissionsFile);
			permFile.set("users." + this.getName(true) + ".permissions", YamlConfiguration.loadConfiguration(Genesis.config).get("defaultPermissions"));
			try {
				permFile.save(Genesis.permissionsFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isRegistered() {
		if (YamlConfiguration.loadConfiguration(Genesis.usersFile).contains(this.getName(true) + ".IP"))
			return true;
		else
			return false;
	}
	
	public YamlConfiguration getConfig() {
		return this.confFile;
	}
	
	public boolean isAuthorized(String permission) {
		YamlConfiguration file = YamlConfiguration.loadConfiguration(Genesis.permissionsFile);
		permission = permission.toLowerCase();
		
		if (file.getList("users." + this.getName(true) + ".permissions").contains(permission)) {
			return true;
		}
		
		List<String> groupsOfPlayer = new ArrayList<String>(file.getStringList("users." + this.getName().toLowerCase() + ".groups"));
		for (int i = 0; i < groupsOfPlayer.size(); i++) {
			if (getGroupPermissions(groupsOfPlayer.get(i)).contains(permission)) {
				return true;
			}
		}
		
		permission = permission.replaceAll(".\\*", "");
		if (permission.replaceAll(".", "").length() +1 <= permission.length()) {
			for (int i = permission.length()-1; i >= 0; i--) {
				if (permission.charAt(i) == '.') {
					return isAuthorized(permission.substring(0, i+1) + "*");
				}
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
	
	/*public void ban(User admin, String reason) {
		
	}
	
	public void ban(User admin, String reason/*, Time time*) {
		
	}*/
}
