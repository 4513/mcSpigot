package delta.spigot.genesis.entity;

import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import delta.spigot.genesis.Genesis;

public class User extends PlayerCharacter
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
			file.set(this.getName() + ".LastPlayedIP", this.getIPAddress());
		} else {
			file.set(this.getName() + ".IP", this.getIPAddress());
			file.set(this.getName() + ".LastPlayedIP", this.getIPAddress());
			file.set(this.getName() + ".GameMode", false);
			file.set(this.getName() + ".FlyAllow", false);
			file.set(this.getName() + ".Speed.Run", 3.5);
			file.set(this.getName() + ".Speed.Walk", 2.0);
			file.set(this.getName() + ".Speed.Sneak", 1.0);
			file.set(this.getName() + ".JumpPower", 1);
			file.set(this.getName() + ".Energy", "");
			try {
				file.save(Genesis.usersFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isRegistered() {
		YamlConfiguration file = YamlConfiguration.loadConfiguration(Genesis.usersFile);
		if (file.contains(this.getName() + ".IP")) return true;
		else return false;
	}
	
	public YamlConfiguration getConfig() {
		return this.confFile;
	}
}
