package delta.spigot.genesis.command;

import delta.spigot.genesis.entity.User;

public class Command
{
	private CommandSource commandSource;
	private org.bukkit.command.Command command;
	private String commandLabel;
	private String[] args;
	private String prefix;
	
	public void run(CommandSource commandSource, org.bukkit.command.Command cmd, String label, String[] args, String prefix) {
		this.commandSource = commandSource;
		this.command = cmd;
		this.commandLabel = label;
		this.args = args;
		this.prefix = prefix;
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
	
	protected String[] getArgs() {
		return args;
	}
	
	protected String getPrefix() {
		return prefix;
	}
}