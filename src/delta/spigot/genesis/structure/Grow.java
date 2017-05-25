package delta.spigot.genesis.structure;

public interface Grow
{
	boolean isGrowable();
	
	void grow();
	
	void grow(int level);
	
	void growToLvl(int level);
}