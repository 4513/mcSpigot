package delta.spigot.genesis;

import java.lang.reflect.Method;

import org.bukkit.scheduler.BukkitRunnable;

public final class GenesisRunnable extends BukkitRunnable
{
	private Object targetObject;
	public Method targetMethod;
	private Object[] perameters;
	
	public GenesisRunnable(Object targetObject, String methodName, Object[] argsOrNull) {
		try {
			this.targetMethod = targetObject.getClass().getMethod(methodName, (Class<?>[]) argsOrNull);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.targetObject = targetObject;
		this.perameters = argsOrNull;
	}
	
	@Override
	public void run() {
		try {
			this.targetMethod.invoke(this.targetObject, perameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}