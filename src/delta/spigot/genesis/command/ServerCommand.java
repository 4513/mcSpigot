package delta.spigot.genesis.command;

import static delta.spigot.genesis.log.I18n.messageHead;
import static delta.spigot.genesis.log.I18n.messageCol;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import delta.spigot.genesis.entity.User;

public final class ServerCommand extends Command
{
	private final String allowOptions = "sSrRi";
	@SuppressWarnings("unused")
	private final String optionsWA = "";
	//private final String optionsWO = "SR";
	private Map<String, String> mapp = _INVALID_mapp();
	
	@Override
	public final void run(final CommandSource commandSource, final org.bukkit.command.Command cmd, final String label, final String[] args, final String prefix) {
		super.run(commandSource, cmd, label, args, prefix);
		String[] tmp = new String[allowOptions.length()];
		for (int i = 0; i < allowOptions.length(); i++)
			tmp[i] = allowOptions.charAt(i) + "";
		Object[] o = options.keySet().toArray();
		String[] opt = new String[o.length];
		for (int i = 0; i < o.length; i++)
			opt[i] = o[i].toString();
		for (int i = 0; i < opt.length; i++) {
			boolean found = false;
			for (int j = 0; j < tmp.length; j++) {
				if (opt[i] == tmp[j]) found = true;
				if (!found && (j == tmp.length))
					unexpected = true;
			}
			if (unexpected) break;
		}
		if (commandSource instanceof Player) {
			User player = new User((Player) commandSource);
			for (int i = 0; i < opt.length; i++)
				if (!player.isAuthorized(mapp.get(opt[i])))
					noperm = true;
		}
		if (unexpected) commandSource.getSource().sendMessage("Unexpected option!");
		else if (noperm) commandSource.getSource().sendMessage("No perm option!");
		else run();
	}
	
	@Override
	public final void run() {
		getSource().getSource().sendMessage(messageHead("Server", 50));
		// Help
		if (options.keySet().isEmpty()) {
			for (String msg : messageCol(mapp, 5, 35, ChatColor.YELLOW, " "))
				getSource().getSource().sendMessage(msg);
		}
		// Status
		if (options.keySet().contains("s")) {
			
		}
		// Info
		if (options.keySet().contains("i")) {
			
		}
		// Reload
		if (options.keySet().contains("r")) {
			getSource().getSource().sendMessage("Reload Server");
			Bukkit.getServer().reload();
		}
		// Restart
		if (options.keySet().contains("R")) {
			String message = args.size() > 0 ? args.get(0) : "";
			for (int i = 1; i < args.size(); i++)
				message = message + " " + args.get(i);
			getSource().getSource().sendMessage("Kicking Players");
			for (Player player : Bukkit.getServer().getOnlinePlayers())
				if ((getSource().getSource() instanceof Player) && !(player.getName().equalsIgnoreCase(getSource().getSource().getName())))
					player.kickPlayer(message);
			getSource().getSource().sendMessage("Restarting Server");
			if (getSource().getSource() instanceof Player) {
				Player player = (Player) getSource().getSource();
				player.kickPlayer(message);
			}
			Bukkit.getServer().reload();
		}
		// Stop
		if (options.keySet().contains("S")) {
			String message = args.size() > 0 ? args.get(0) : "";
			for (int i = 1; i < args.size(); i++)
				message = message + " " + args.get(i);
			getSource().getSource().sendMessage("Kicking Players");
			for (Player player : Bukkit.getServer().getOnlinePlayers())
				if ((getSource().getSource() instanceof Player) && !(player.getName().equalsIgnoreCase(getSource().getSource().getName())))
					player.kickPlayer(message);
			getSource().getSource().sendMessage("Stopping Server");
			if (getSource().getSource() instanceof Player) {
				Player player = (Player) getSource().getSource();
				player.kickPlayer(message);
			}
			Bukkit.getServer().shutdown();
		}
	}
	
	@Deprecated
	private final Map<String, String> _INVALID_mapp() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("s", "genesis.server.status");
		m.put("S", "genesis.server.stop");
		m.put("r", "genesis.server.reload");
		m.put("R", "genesis.server.restart");
		m.put("i", "genesis.server.info");
		return m;
	}
}