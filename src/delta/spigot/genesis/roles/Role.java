package delta.spigot.genesis.roles;

public interface Role
{
	@Deprecated
	public String getString();
	
	public String getName();
	
	public int getLevel();
	
	public void setLevel(int level);
	
	public float getXp();
	
	public void setXp(float xp);
	
	@Deprecated
	public void reset();
}