package delta.spigot.genesis.structure;

public interface Structure extends Grow
{
	String getName();
	
	int getLevel();
	
	void spawn();
	
	void remove();
}