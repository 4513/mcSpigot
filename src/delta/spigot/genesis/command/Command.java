package delta.spigot.genesis.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import delta.spigot.genesis.entity.User;

public class Command
{
	private CommandSource commandSource;
	private org.bukkit.command.Command command;
	private String commandLabel;
	protected List<String> args = new ArrayList<String>();
	private String prefix;
	protected Map<String, String> options = new HashMap<String, String>();
	protected boolean unexpected = false;
	protected boolean noperm = false;
	
	public void run(CommandSource commandSource, org.bukkit.command.Command cmd, String label, String[] args, String prefix) {
		this.commandSource = commandSource;
		this.command = cmd;
		this.commandLabel = label;
		this.prefix = prefix;
		
		int argLen = args.length;
		int optLen = 0;
		String opt = "";
		for (int i = 0; i < argLen; i ++) {
			if (args[i].startsWith("-")) {
				opt = opt + args[i];
				optLen++;
			}
		}
		argLen -= optLen;
		for (int i = 0; i < opt.length(); i++)
			options.put(opt.charAt(i) + "", null);
		for (int i = optLen; i < args.length; i++)
			this.args.add(args[i]);
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*this.commandSource = commandSource;
		this.command = cmd;
		this.commandLabel = label;
		this.args = args;
		this.prefix = prefix;
		String opt = "";
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				hasOptions = true;
				opt = opt + args[i].replaceAll("-", "");
			}
		}
		if (hasOptions) {
			for (int i = 0; i < opt.length(); i++) {
				options.put(opt.charAt(i) + "", "");
			}
		}*/
	}
	
	public void run(User user) {
		
	}
	
	public void run() {
		
	}
	
	protected CommandSource getSource() {
		return commandSource;
	}
	
	protected org.bukkit.command.Command getCommand() {
		return command;
	}
	
	protected String getLabel() {
		return commandLabel;
	}
	
	protected List<String> getArgs() {
		return args;
	}
	
	protected String getPrefix() {
		return prefix;
	}
}