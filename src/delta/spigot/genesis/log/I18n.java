package delta.spigot.genesis.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import org.bukkit.ChatColor;

import delta.spigot.genesis.Genesis;
import delta.spigot.genesis.entity.PlayerCharacter;

public class I18n
{
	public I18n()
	{
		
	}
	
	public static void sendMessage(PlayerCharacter target, String message) {
		target.getPlayer().sendMessage(message);
	}
	
	public static String tl(String message) {
		return "";
	}
	
	public static String tl(String message, Object obj) {
		return "";
	}
	
	public static void debug(String message, String errorMessage) {
		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = new Date();
		String currentTime = format.format(date).toString();
		
		if (Genesis.debugMode) {
			Genesis.logger.warning("[Genesis-Debug] " + message + "\n  Info: " + currentTime + (Math.random()*9));
		} else {
			
		}
	}
	
	public static String messageHead(String title, int length) {
		if (title.length() >= length) return (ChatColor.WHITE + title);
		else {
			String tmp = ChatColor.GOLD + "";
			int oneSide = (length-title.length()) % 2 == 0 ? (length-title.length())/2 : (length-title.length()-1)/2;
			for (int i = 1; i <= oneSide; i++) {
				if (i == oneSide) tmp = tmp + " ";
				else tmp = tmp + "-";
			}
			tmp = tmp + ChatColor.WHITE + title + ChatColor.GOLD;
			for (int i = oneSide; i >= 1; i--) {
				if (i == oneSide) tmp = tmp + " ";
				else tmp = tmp + "-";
			}
			return tmp;
		}
	}
	
	public static String[] messageDescription(String description, int length) {
		try {
			String[] tmp = description.split(" ");
			String[] rows = new String[tmp.length];
			int j = 0;
			for (int i = 0; i < rows.length; i++) rows[i] = "";
			rows[0] = " ";
			for (int i = 0; i < tmp.length; i++) {
				if (rows[j].length() + tmp[i].length() + 1 < length) rows[j] = rows[j] + " " + tmp[i];
				else {
					j++;
					rows[j] = rows[j] + tmp[i];
				}
			}
			String[] ret = new String[j+1];
			for (int i = 0; i < ret.length; i++)
				ret[i] = ChatColor.DARK_GRAY + rows[i];
			return ret;
		} catch (PatternSyntaxException e) {
			e.getStackTrace();
			return null;
		}
	}
	
	public static List<String> messageCol(Map<String, String> map, int lcol1, int lcol2, ChatColor color, String prefix) {
		List<String> ret = new ArrayList<String>();
		for (String col1 : map.keySet()) {
			String row = color + prefix + col1;
			for (int i = (lcol1 - col1.length() - prefix.length()); i >= 0; i--) row = row + " ";
			row = row + ChatColor.GRAY + map.get(col1);
			ret.add(row);
		}
		return ret;
	}
	
	public static void messageFull(String title, String description, String[] idk, int length) {
		
	}
}