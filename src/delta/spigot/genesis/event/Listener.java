package delta.spigot.genesis.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import delta.spigot.genesis.GenesisPlugin;

public class Listener implements org.bukkit.event.Listener
{
	protected final GenesisPlugin plugin;
	private static final List<String> materials = _INVALID_getAllMaterial();
	
	public Listener(final GenesisPlugin plugin) {
		this.plugin = plugin;
	}
	
	/*@Deprecated
	private static List<String> _INVALID_getAllMaterials; {
		for (Material material : Material.values())
			materials.add(material.name());
	}*/

	@Deprecated
	public static List<String> _INVALID_getAllMaterial() {
		List<String> materials = new ArrayList<String>();
		for (Material material : Material.values())
			materials.add(material.name());
		return materials;
	}
	
	public static List<String> getAllMaterials() {
		return materials;
	}
}