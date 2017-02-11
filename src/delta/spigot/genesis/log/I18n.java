package delta.spigot.genesis.log;

import delta.spigot.genesis.entity.PlayerCharacter;

public class I18n
{
	public I18n()
	{
		
	}
	
	public static void sendMessage(PlayerCharacter target, String message) {
		target.getPlayer().sendMessage(message);
	}
	
	public static void tl(String message) {
		
	}
	
	public static void tl(String message, Object obj) {
		
	}
}