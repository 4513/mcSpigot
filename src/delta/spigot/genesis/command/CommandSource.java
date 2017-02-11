package delta.spigot.genesis.command;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class CommandSource implements CommandSender
{
	private CommandSender source;
	
	public CommandSource(CommandSender source)
	{
		this.source = source;
	}
	
	public CommandSender getSource()
	{
		return source;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
		return null;
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return null;
	}

	@Override
	public boolean hasPermission(String arg0) {
		return false;
	}

	@Override
	public boolean hasPermission(Permission arg0) {
		return false;
	}

	@Override
	public boolean isPermissionSet(String arg0) {
		return false;
	}

	@Override
	public boolean isPermissionSet(Permission arg0) {
		return false;
	}

	@Override
	public void recalculatePermissions() {
		
	}

	@Override
	public void removeAttachment(PermissionAttachment arg0) {
		
	}

	@Override
	public boolean isOp() {
		return false;
	}

	@Override
	public void setOp(boolean arg0) {
		
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Server getServer() {
		return null;
	}

	@Override
	public void sendMessage(String arg0) {
		
	}

	@Override
	public void sendMessage(String[] arg0) {
		
	}
}