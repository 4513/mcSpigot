package delta.spigot.genesis.entity;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.GoalController;
import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.ai.speech.SpeechController;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.BlockBreaker;
import net.citizensnpcs.api.npc.BlockBreaker.BlockBreakerConfiguration;
import net.citizensnpcs.api.npc.MetadataStore;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.util.DataKey;

public final class NPC extends PlayerCharacter {
	private final EntityType entityType;
	private final net.citizensnpcs.api.npc.NPC npc;
	private Location location;
	
	public NPC(EntityType type, UUID uuid, int value, String name) {
		this.entityType = type;
		npc = getRegister().createNPC(type, uuid, value, name);
		this.nickName = name;
		this.player = Bukkit.getPlayer(name);
		register();
	}
	
	public NPC(EntityType type, UUID uuid, String name) {
		this.entityType = type;
		npc = getRegister().createNPC(type, uuid, 0, name);
		this.nickName = name;
		this.player = Bukkit.getPlayer(name);
		register();
	}
	
	public NPC(EntityType type, String name) {
		this.entityType = type;
		npc = getRegister().createNPC(type, name);
		this.nickName = name;
		this.player = Bukkit.getPlayer(name);
		register();
	}
	
	public NPC(UUID uuid, String name) {
		entityType = EntityType.PLAYER;
		npc = getRegister().createNPC(entityType, uuid, 0, name);
		this.nickName = name;
		this.player = Bukkit.getPlayer(name);
		register();
	}
	
	public NPC(String name) {
		entityType = EntityType.PLAYER;
		npc = getRegister().createNPC(entityType, name);
		this.nickName = name;
		this.player = Bukkit.getPlayer(name);
		register();
	}
	
	private final NPCRegistry getRegister() {
		return CitizensAPI.getNPCRegistry();
	}
	
	private final void register() {
		
	}
	
	public final boolean spawn(Location location) {
		setLocation(location);
		return npc.spawn(location);
	}
	
	public final boolean spawn(User user) {
		setLocation(user.getLocation());
		return npc.spawn(user.getLocation());
	}
	
	public final boolean spawn(Player player) {
		setLocation(player.getLocation());
		return npc.spawn(player.getLocation());
	}
	
	@Deprecated
	public final boolean spawn(net.citizensnpcs.api.npc.NPC citizen) {
		setLocation(citizen.getStoredLocation());
		return npc.spawn(citizen.getStoredLocation());
	}
	
	public final boolean spawn(NPC npc) {
		setLocation(npc.getLocation());
		return this.npc.spawn(npc.getLocation());
	}
	
	public final Location getLocation() {
		return location;
	}
	
	@Deprecated
	public final void setLocation(Location location) {
		this.location = location;
	}
	
	public final net.citizensnpcs.api.npc.NPC getCitizen() {
		return npc;
	}
	
	public final String getName() {
		return npc.getName();
	}
	
	public final String getFullName() {
		return npc.getFullName();
	}
	
	public final EntityType getEntityType() {
		return entityType;
	}

	public final void addTrait(Class<? extends Trait> value) {
		npc.addTrait(value);
	}

	public final void addTrait(Trait trait) {
		npc.addTrait(trait);
	}

	public final net.citizensnpcs.api.npc.NPC clone() {
		return npc.clone();
	}

	public final MetadataStore data() {
		return npc.data();
	}

	public final boolean despawn() {
		return npc.despawn();
	}

	public final boolean despawn(DespawnReason despawnReason) {
		return npc.despawn(despawnReason);
	}

	public final void destroy() {
		npc.destroy();
	}

	public final void faceLocation(Location location) {
		npc.faceLocation(location);
	}

	public final BlockBreaker getBlockBreaker(Block block, BlockBreakerConfiguration blockBreakerConf) {
		return npc.getBlockBreaker(block, blockBreakerConf);
	}

	public final GoalController getDefaultGoalController() {
		return npc.getDefaultGoalController();
	}

	public final SpeechController getDefaultSpeechController() {
		return npc.getDefaultSpeechController();
	}

	public final Entity getEntity() {
		return npc.getEntity();
	}

	public int getId() {
		return npc.getId();
	}

	public Navigator getNavigator() {
		return npc.getNavigator();
	}

	public NPCRegistry getOwningRegistry() {
		return npc.getOwningRegistry();
	}

	public Location getStoredLocation() {
		return npc.getStoredLocation();
	}

	public <T extends Trait> T getTrait(Class<T> value) {
		return npc.getTrait(value);
	}

	public Iterable<Trait> getTraits() {
		return npc.getTraits();
	}

	public boolean hasTrait(Class<? extends Trait> value) {
		return npc.hasTrait(value);
	}

	public boolean isFlyable() {
		return npc.isFlyable();
	}

	public boolean isProtected() {
		return npc.isProtected();
	}

	public boolean isSpawned() {
		return npc.isSpawned();
	}

	public void load(DataKey dataKey) {
		npc.load(dataKey);
	}

	public void removeTrait(Class<? extends Trait> value) {
		npc.removeTrait(value);
	}

	public void save(DataKey dataKey) {
		npc.save(dataKey);
	}

	@Deprecated
	public final void setBukkitEntityType(EntityType entityType) {
		npc.setBukkitEntityType(entityType);
	}
	
	public final void setEntityType(EntityType entityType) {
		setBukkitEntityType(entityType);
	}

	public void setFlyable(boolean value) {
		npc.setFlyable(value);
	}

	public void setName(String name) {
		npc.setName(name);
		this.nickName = name;
	}

	public void tp(Location location, TeleportCause teleportCause) {
		npc.teleport(location, teleportCause);
	}

	public void setProtected(boolean value) {
		npc.setProtected(value);
	}
}