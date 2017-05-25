package delta.spigot.genesis.structure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;

import delta.spigot.genesis.Genesis;

public class Tree
{
	private int level = 0;
	private boolean isBig;
	private Location location;
	private TreeSpecies wood;
	private Material leaves;
	private int xp = -1;
	
	@Deprecated
	public Tree(int level, boolean bigTree, Location location, TreeSpecies species, Material leaves) {
		this.level = level;
		this.isBig = bigTree;
		this.location = location;
		this.wood = species;
		this.leaves = leaves;
		Genesis.listOfTrees.add(this);
	}

	@Deprecated
	public Tree(boolean bigTree, Location location, TreeSpecies species, Material leaves) {
		this.isBig = bigTree;
		this.location = location;
		this.wood = species;
		this.leaves = leaves;
		Genesis.listOfTrees.add(this);
	}
	
	public Tree(boolean bigTree, Location location, TreeSpecies species) {
		this.isBig = bigTree;
		this.location = location;
		this.wood = species;
		Genesis.listOfTrees.add(this);
	}

	@Deprecated
	public Tree(Location location, TreeSpecies species, Material leaves) {
		this.isBig = Math.random()*1000 > 900 ? true : false;
		this.location = location;
		this.wood = species;
		this.leaves = leaves;
		Genesis.listOfTrees.add(this);
	}
	
	public Tree(Location location, TreeSpecies species) {
		this.isBig = Math.random()*1000 > 900 ? true : false;
		this.location = location;
		this.wood = species;
		Genesis.listOfTrees.add(this);
		Genesis.logger.info("new Tree() " + species.name());
	}
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean isBig() {
		return isBig;
	}
	
	@Deprecated
	public void big(boolean value) {
		this.isBig = value;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	@Deprecated
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public TreeSpecies getWoodBlock() {
		return this.wood;
	}
	
	public void setWoodBlock(TreeSpecies wood) {
		this.wood = wood;
	}
	
	public Material getLeavesBlock() {
		return this.leaves;
	}
	
	public void setLeavesBlock(Material leaves) {
		this.leaves = leaves;
	}
	
	public void grow() {
		this.level = this.level + 1;
	}
	
	public void grow(int levelUp) {
		this.level = this.level + levelUp;
	}
	
	public void growToLevel(int level) {
		this.level = level;
	}
	
	public void addXP() {
		this.level = this.xp + 1 > 2 ? this.level + 1 : this.level;
		this.xp = this.xp + 1 > 2 ? 0 : this.xp + 1;
		if (this.xp == 0)
			draw();
	}
	
	public void draw() {
		if (level == 0) {
			location.getBlock().setType(Material.DIAMOND_BLOCK);
		}
		if (level == 1) {
			location.getBlock().setType(Material.GOLD_BLOCK);
		}
		if (level == 2) {
			location.getBlock().setType(Material.REDSTONE_BLOCK);
		}
		if (level > 2) {
			location.getBlock().setType(Material.LAVA);
		}
	}
	
	public static void createTree(Location location, Material material) {
		
	}
}