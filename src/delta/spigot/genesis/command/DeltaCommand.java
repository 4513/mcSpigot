package delta.spigot.genesis.command;

import java.util.logging.Logger;

public class DeltaCommand extends Command
{
	public void run(CommandSource commandSource, org.bukkit.command.Command cmd, String label, String[] args, String prefix) {
		super.run(commandSource, cmd, label, args, prefix);
		Logger.getLogger("Minecraft").info("wwww");
	}
}