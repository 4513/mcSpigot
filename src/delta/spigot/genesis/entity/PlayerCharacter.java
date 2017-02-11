package delta.spigot.genesis.entity;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Achievement;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import delta.spigot.genesis.log.I18n;

public class PlayerCharacter implements Player
{
	private float walkSpeed;
	//private double runSpeed;
	private float flySpeed;
	//private double crouchSpeed;
	//private double jumpPower;
	//private String firstName;
	//private String lastName;
	private String nickName;
	private String displayName = nickName;
	private Player player;
	//private double health = 20.0;
	//private double hunger = 20.0;
	//private List<PlayerCharacter> bodyguards;
	//private Location location;
	
	private Inventory enderChest;
	private PlayerInventory inventory;
	
	public PlayerCharacter(Player player) {
		this.player = player;
		/*if (!Genesis.listOfPlayerCharacter.contains(player))
			Genesis.listOfPlayerCharacter.add(this);*/
		this.nickName = player.getName();
	}
	
	public boolean isUser() {
		if (this instanceof User) return true;
		else return false;
	}

	@Override
	public void closeInventory() {
		player.closeInventory();
	}

	@Override
	public Inventory getEnderChest() {
		return enderChest;
	}

	@Override
	public int getExpToLevel() {
		return player.getExpToLevel();
	}

	@Override
	public GameMode getGameMode() {
		return player.getGameMode();
	}

	@Override
	public PlayerInventory getInventory() {
		return inventory;
	}

	@Override
	@Deprecated
	public ItemStack getItemInHand() {
		return player.getItemInHand();
	}

	@Override
	public ItemStack getItemOnCursor() {
		return player.getItemOnCursor();
	}

	@Override
	public MainHand getMainHand() {
		return player.getMainHand();
	}

	@Override
	public String getName() {
		return nickName;
	}

	@Override
	public InventoryView getOpenInventory() {
		return player.getOpenInventory();
	}

	@Override
	public int getSleepTicks() {
		return player.getSleepTicks();
	}

	@Override
	public boolean isBlocking() {
		return player.isBlocking();
	}

	@Override
	public boolean isHandRaised() {
		return player.isHandRaised();
	}

	@Override
	public boolean isSleeping() {
		return player.isSleeping();
	}

	@Override
	public InventoryView openEnchanting(Location location, boolean value) {
		return player.openEnchanting(location, value);
	}

	@Override
	public InventoryView openInventory(Inventory inventory) {
		return player.openInventory(inventory);
	}

	@Override
	public void openInventory(InventoryView inventoryView) {
		player.openInventory(inventoryView);
	}

	@Override
	public InventoryView openMerchant(Villager villager, boolean value) {
		return player.openMerchant(villager, value);
	}

	@Override
	public InventoryView openMerchant(Merchant merchant, boolean value) {
		return player.openMerchant(merchant, value);
	}

	@Override
	public InventoryView openWorkbench(Location location, boolean value) {
		return player.openWorkbench(location, value);
	}

	@Override
	public void setGameMode(GameMode gameMode) {
		player.setGameMode(gameMode);
	}

	@Override
	@Deprecated
	public void setItemInHand(ItemStack itemStack) {
		player.setItemInHand(itemStack);
	}

	@Override
	public void setItemOnCursor(ItemStack itemStack) {
		player.setItemOnCursor(itemStack);
	}

	@Override
	public boolean setWindowProperty(Property property, int value) {
		return player.setWindowProperty(property, value);
	}

	@Override
	@Deprecated
	public int _INVALID_getLastDamage() {
		return player._INVALID_getLastDamage();
	}

	@Override
	@Deprecated
	public void _INVALID_setLastDamage(int value) {
		player._INVALID_setLastDamage(value);
	}

	@Override
	public boolean addPotionEffect(PotionEffect potionEffect) {
		return player.addPotionEffect(potionEffect);
	}

	@Override
	public boolean addPotionEffect(PotionEffect potionEffect, boolean value) {
		return player.addPotionEffect(potionEffect, value);
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> potionEffect) {
		return player.addPotionEffects(potionEffect);
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		return player.getActivePotionEffects();
	}

	@Override
	public boolean getCanPickupItems() {
		return player.getCanPickupItems();
	}

	@Override
	public EntityEquipment getEquipment() {
		return player.getEquipment();
	}

	@Override
	public double getEyeHeight() {
		return player.getEyeHeight();
	}

	@Override
	public double getEyeHeight(boolean value) {
		return player.getEyeHeight(value);
	}

	@Override
	public Location getEyeLocation() {
		return player.getEyeLocation();
	}

	@Override
	public Player getKiller() {
		return player.getKiller();
	}

	@Override
	public double getLastDamage() {
		return player.getLastDamage();
	}

	@Override
	@Deprecated
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> bytes, int value) {
		return player.getLastTwoTargetBlocks(bytes, value);
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(Set<Material> material, int value) {
		return player.getLastTwoTargetBlocks(material, value);
	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		return player.getLeashHolder();
	}

	@Override
	@Deprecated
	public List<Block> getLineOfSight(HashSet<Byte> bytes, int value) {
		return player.getLineOfSight(bytes, value);
	}

	@Override
	public List<Block> getLineOfSight(Set<Material> material, int value) {
		return player.getLineOfSight(material, value);
	}

	@Override
	public int getMaximumAir() {
		return player.getMaximumAir();
	}

	@Override
	public int getMaximumNoDamageTicks() {
		return player.getMaximumNoDamageTicks();
	}

	@Override
	public int getNoDamageTicks() {
		return player.getNoDamageTicks();
	}

	@Override
	public PotionEffect getPotionEffect(PotionEffectType potionEffectType) {
		return player.getPotionEffect(potionEffectType);
	}

	@Override
	public int getRemainingAir() {
		return player.getRemainingAir();
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		return player.getRemoveWhenFarAway();
	}

	@Override
	@Deprecated
	public Block getTargetBlock(HashSet<Byte> bytes, int value) {
		return player.getTargetBlock(bytes, value);
	}

	@Override
	public Block getTargetBlock(Set<Material> material, int value) {
		return player.getTargetBlock(material, value);
	}

	@Override
	public boolean hasAI() {
		return player.hasAI();
	}

	@Override
	public boolean hasLineOfSight(Entity entity) {
		return player.hasLineOfSight(entity);
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType potionEffectType) {
		return player.hasPotionEffect(potionEffectType);
	}

	@Override
	public boolean isCollidable() {
		return player.isCollidable();
	}

	@Override
	public boolean isGliding() {
		return player.isGliding();
	}

	@Override
	public boolean isLeashed() {
		return player.isLeashed();
	}

	@Override
	public void removePotionEffect(PotionEffectType potionEffectType) {
		player.removePotionEffect(potionEffectType);
	}

	@Override
	public void setAI(boolean value) {
		player.setAI(value);
	}

	@Override
	public void setCanPickupItems(boolean value) {
		player.setCanPickupItems(value);
	}

	@Override
	public void setCollidable(boolean value) {
		player.setCollidable(value);
	}

	@Override
	public void setGliding(boolean value) {
		player.setGliding(value);
	}

	@Override
	public void setLastDamage(double value) {
		player.setLastDamage(value);
	}

	@Override
	public boolean setLeashHolder(Entity entity) {
		return player.setLeashHolder(entity);
	}

	@Override
	public void setMaximumAir(int value) {
		player.setMaximumAir(value);
	}

	@Override
	public void setMaximumNoDamageTicks(int value) {
		player.setMaximumNoDamageTicks(value);
	}

	@Override
	public void setNoDamageTicks(int value) {
		player.setNoDamageTicks(value);
	}

	@Override
	public void setRemainingAir(int value) {
		player.setRemainingAir(value);
	}

	@Override
	public void setRemoveWhenFarAway(boolean value) {
		player.setRemoveWhenFarAway(value);
	}

	@Override
	public AttributeInstance getAttribute(Attribute attribute) {
		return player.getAttribute(attribute);
	}

	@Override
	public boolean addPassenger(Entity entity) {
		return player.addPassenger(entity);
	}

	@Override
	public boolean addScoreboardTag(String string) {
		return player.addScoreboardTag(string);
	}

	@Override
	public boolean eject() {
		return player.eject();
	}

	@Override
	public int getEntityId() {
		return player.getEntityId();
	}

	@Override
	public float getFallDistance() {
		return player.getFallDistance();
	}

	@Override
	public int getFireTicks() {
		return player.getFireTicks();
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		return player.getLastDamageCause();
	}

	@Override
	public Location getLocation() {
		return player.getLocation();
	}

	@Override
	public Location getLocation(Location location) {
		return player.getLocation(location);
	}

	@Override
	public int getMaxFireTicks() {
		return player.getMaxFireTicks();
	}

	@Override
	public List<Entity> getNearbyEntities(double value1, double value2, double value3) {
		return player.getNearbyEntities(value1, value2, value3);
	}

	@Override
	@Deprecated
	public Entity getPassenger() {
		return player.getPassenger();
	}

	@Override
	public List<Entity> getPassengers() {
		return player.getPassengers();
	}

	@Override
	public int getPortalCooldown() {
		return player.getPortalCooldown();
	}

	@Override
	public Set<String> getScoreboardTags() {
		return player.getScoreboardTags();
	}

	@Override
	public Server getServer() {
		return player.getServer();
	}

	@Override
	public int getTicksLived() {
		return player.getTicksLived();
	}

	@Override
	@Deprecated // Cekni toto
	public EntityType getType() {
		return player.getType();
	}

	@Override
	public UUID getUniqueId() {
		return player.getUniqueId();
	}

	@Override
	public Entity getVehicle() {
		return player.getVehicle();
	}

	@Override
	public Vector getVelocity() {
		return player.getVelocity();
	}

	@Override
	public World getWorld() {
		return player.getWorld();
	}

	@Override
	public boolean hasGravity() {
		return player.hasGravity();
	}

	@Override
	public boolean isCustomNameVisible() {
		return player.isCustomNameVisible();
	}

	@Override
	public boolean isDead() {
		return player.isDead();
	}

	@Override
	public boolean isEmpty() {
		return player.isEmpty();
	}

	@Override
	public boolean isGlowing() {
		return player.isGlowing();
	}

	@Override
	public boolean isInsideVehicle() {
		return player.isInsideVehicle();
	}

	@Override
	public boolean isInvulnerable() {
		return player.isInvulnerable();
	}

	@Override
	public boolean isOnGround() {
		return player.isOnGround();
	}

	@Override
	public boolean isSilent() {
		return player.isSilent();
	}

	@Override
	public boolean isValid() {
		return player.isValid();
	}

	@Override
	public boolean leaveVehicle() {
		return player.leaveVehicle();
	}

	@Override
	public void playEffect(EntityEffect entityEffect) {
		player.playEffect(entityEffect);
	}

	@Override
	public void remove() {
		player.remove();
	}

	@Override
	public boolean removePassenger(Entity entity) {
		return player.removePassenger(entity);
	}

	@Override
	public boolean removeScoreboardTag(String string) {
		return player.removeScoreboardTag(string);
	}

	@Override
	public void setCustomNameVisible(boolean value) {
		player.setCustomNameVisible(value);
	}

	@Override
	public void setFallDistance(float value) {
		player.setFallDistance(value);
	}

	@Override
	public void setFireTicks(int value) {
		player.setFireTicks(value);
	}

	@Override
	public void setGlowing(boolean value) {
		player.setGlowing(value);
	}

	@Override
	public void setGravity(boolean value) {
		player.setGravity(value);
	}

	@Override
	public void setInvulnerable(boolean value) {
		player.setInvulnerable(value);
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
		player.setLastDamageCause(entityDamageEvent);
	}

	@Override
	@Deprecated
	public boolean setPassenger(Entity entity) {
		return player.setPassenger(entity);
	}

	@Override
	public void setPortalCooldown(int value) {
		player.setPortalCooldown(value);
	}

	@Override
	public void setSilent(boolean value) {
		player.setSilent(value);
	}

	@Override
	public void setTicksLived(int value) {
		player.setTicksLived(value);
	}

	@Override
	public void setVelocity(Vector vector) {
		player.setVelocity(vector);
	}

	@Override
	public boolean teleport(Location location) {
		return player.teleport(location);
	}

	@Override
	public boolean teleport(Entity entity) {
		return player.teleport(entity);
	}

	@Override
	public boolean teleport(Location location, TeleportCause teleportCause) {
		return player.teleport(location, teleportCause);
	}

	@Override
	public boolean teleport(Entity entity, TeleportCause teleportCause) {
		return player.teleport(entity, teleportCause);
	}

	@Override
	public List<MetadataValue> getMetadata(String string) {
		return player.getMetadata(string);
	}

	@Override
	public boolean hasMetadata(String string) {
		return player.hasMetadata(string);
	}

	@Override
	public void removeMetadata(String string, Plugin plugin) {
		player.removeMetadata(string, plugin);
	}

	@Override
	public void setMetadata(String string, MetadataValue metadataValue) {
		player.setMetadata(string, metadataValue);
	}

	@Override
	public void sendMessage(String message) {
		I18n.sendMessage(this, message);
	}

	@Override
	public void sendMessage(String[] message) {
		for (int i = 0; i < message.length; i++)
			sendMessage(message[i]);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		return player.addAttachment(plugin);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int value) {
		return player.addAttachment(plugin, value);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String string, boolean value) {
		return player.addAttachment(plugin, string, value);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String string, boolean value1, int value2) {
		return player.addAttachment(plugin, string, value1, value2);
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return player.getEffectivePermissions();
	}

	@Override
	public boolean hasPermission(String permission) {
		return player.hasPermission(permission);
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return player.hasPermission(permission);
	}

	@Override
	public boolean isPermissionSet(String permission) {
		return player.isPermissionSet(permission);
	}

	@Override
	public boolean isPermissionSet(Permission permission) {
		return player.isPermissionSet(permission);
	}

	@Override
	public void recalculatePermissions() {
		player.recalculatePermissions();
	}

	@Override
	public void removeAttachment(PermissionAttachment permissionAttachment) {
		player.removeAttachment(permissionAttachment);
	}

	@Override
	@Deprecated
	public boolean isOp() {
		return player.isOp();
	}

	@Override
	@Deprecated
	public void setOp(boolean value) {
		player.setOp(value);
	}

	@Override
	public String getCustomName() {
		return player.getCustomName();
	}

	@Override
	public void setCustomName(String string) {
		player.setCustomName(string);
	}

	@Override
	@Deprecated
	public void _INVALID_damage(int value) {
		player._INVALID_damage(value);
	}

	@Override
	@Deprecated
	public void _INVALID_damage(int value, Entity entity) {
		player._INVALID_damage(value, entity);
	}

	@Override
	@Deprecated
	public int _INVALID_getHealth() {
		return player._INVALID_getHealth();
	}

	@Override
	@Deprecated
	public int _INVALID_getMaxHealth() {
		return player._INVALID_getMaxHealth();
	}

	@Override
	@Deprecated
	public void _INVALID_setHealth(int value) {
		player._INVALID_setHealth(value);
	}

	@Override
	@Deprecated
	public void _INVALID_setMaxHealth(int value) {
		player._INVALID_setMaxHealth(value);
	}

	@Override
	public void damage(double value) {
		player.damage(value);
	}

	@Override
	public void damage(double value, Entity entity) {
		player.damage(value, entity);
	}

	@Override
	public double getHealth() {
		return player.getHealth();
	}

	@Override
	@Deprecated
	public double getMaxHealth() {
		return player.getMaxHealth();
	}

	@Override
	@Deprecated
	public void resetMaxHealth() {
		player.resetMaxHealth();
	}

	@Override
	public void setHealth(double value) {
		player.setHealth(value);
	}

	@Override
	@Deprecated
	public void setMaxHealth(double value) {
		player.setMaxHealth(value);
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> value) {
		return player.launchProjectile(value);
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> value, Vector vector) {
		return player.launchProjectile(value, vector);
	}

	@Override
	public void abandonConversation(Conversation conversation) {
		player.abandonConversation(conversation);
	}

	@Override
	public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {
		player.abandonConversation(conversation, conversationAbandonedEvent);
	}

	@Override
	public void acceptConversationInput(String string) {
		player.acceptConversationInput(string);
	}

	@Override
	public boolean beginConversation(Conversation conversation) {
		return player.beginConversation(conversation);
	}

	@Override
	public boolean isConversing() {
		return player.isConversing();
	}

	@Override
	public long getFirstPlayed() {
		return player.getFirstPlayed();
	}

	@Override
	public long getLastPlayed() {
		return player.getLastPlayed();
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public boolean hasPlayedBefore() {
		return player.hasPlayedBefore();
	}

	@Override
	@Deprecated //Cekni toto
	public boolean isBanned() {
		return player.isBanned();
	}

	@Override
	public boolean isOnline() {
		return player.isOnline();
	}

	@Override
	public boolean isWhitelisted() {
		return player.isWhitelisted();
	}

	@Override
	@Deprecated
	public void setBanned(boolean value) {
		player.setBanned(value);
	}

	@Override
	public void setWhitelisted(boolean value) {
		player.setWhitelisted(value);
	}

	@Override
	public Map<String, Object> serialize() {
		return player.serialize();
	}

	@Override
	public Set<String> getListeningPluginChannels() {
		return player.getListeningPluginChannels();
	}

	@Override
	@Deprecated //Cekni toto
	public void sendPluginMessage(Plugin plugin, String string, byte[] bytes) {
		player.sendPluginMessage(plugin, string, bytes);
	}

	@Override
	public void awardAchievement(Achievement achievement) {
		player.awardAchievement(achievement);
	}

	@Override
	public boolean canSee(Player player) {
		return player.canSee(player);
	}
	
	public boolean canSee(PlayerCharacter playerCharacter) {
		return player.canSee(playerCharacter.player);
	}

	@Override
	@Deprecated //Cekni toto
	public void chat(String string) {
		player.chat(string);
	}

	@Override
	public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
		player.decrementStatistic(statistic);
	}

	@Override
	public void decrementStatistic(Statistic statistic, int value) throws IllegalArgumentException {
		player.decrementStatistic(statistic, value);
	}

	@Override
	public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
		player.decrementStatistic(statistic, material);
	}

	@Override
	public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
		player.decrementStatistic(statistic, entityType);
	}

	@Override
	public void decrementStatistic(Statistic statistic, Material material, int value) throws IllegalArgumentException {
		player.decrementStatistic(statistic, material, value);
	}

	@Override
	public void decrementStatistic(Statistic statistic, EntityType entityType, int value) {
		player.decrementStatistic(statistic, entityType, value);
	}

	@Override
	public InetSocketAddress getAddress() {
		return player.getAddress();
	}
	
	public String getIPAddress() {
		return player.getAddress().getHostString();
	}

	@Override
	@Deprecated //Zmen zo suboru
	public boolean getAllowFlight() {
		return player.getAllowFlight();
	}

	@Override
	public Location getBedSpawnLocation() {
		return player.getBedSpawnLocation();
	}

	@Override
	public Location getCompassTarget() {
		return player.getCompassTarget();
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public float getExhaustion() {
		return player.getExhaustion();
	}

	@Override
	public float getExp() {
		return player.getExp();
	}

	@Override
	public float getFlySpeed() {
		return flySpeed;
	}

	@Override
	public int getFoodLevel() {
		return player.getFoodLevel();
	}

	@Override
	public double getHealthScale() {
		return player.getHealthScale();
	}

	@Override
	public int getLevel() {
		return player.getLevel();
	}

	@Override
	public String getPlayerListName() {
		return player.getPlayerListName();
	}

	@Override
	public long getPlayerTime() {
		return player.getPlayerTime();
	}

	@Override
	public long getPlayerTimeOffset() {
		return player.getPlayerTimeOffset();
	}

	@Override
	public WeatherType getPlayerWeather() {
		return player.getPlayerWeather();
	}

	@Override
	public float getSaturation() {
		return player.getSaturation();
	}

	@Override
	public Scoreboard getScoreboard() {
		return player.getScoreboard();
	}

	@Override
	public Entity getSpectatorTarget() {
		return player.getSpectatorTarget();
	}

	@Override
	public int getStatistic(Statistic statistic) throws IllegalArgumentException {
		return player.getStatistic(statistic);
	}

	@Override
	public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
		return player.getStatistic(statistic, material);
	}

	@Override
	public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
		return player.getStatistic(statistic, entityType);
	}

	@Override
	public int getTotalExperience() {
		return player.getTotalExperience();
	}

	@Override
	public float getWalkSpeed() {
		return walkSpeed;
	}

	@Override
	public void giveExp(int value) {
		player.giveExp(value);
	}

	@Override
	public void giveExpLevels(int value) {
		player.giveExpLevels(value);
	}

	@Override
	public boolean hasAchievement(Achievement achievement) {
		return player.hasAchievement(achievement);
	}

	@Override
	public void hidePlayer(Player player) {
		player.hidePlayer(player);
	}
	
	public void hidePlayer(PlayerCharacter playerCharacter) {
		player.hidePlayer(playerCharacter.player);
	}

	@Override
	public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
		player.incrementStatistic(statistic);
	}

	@Override
	public void incrementStatistic(Statistic statistic, int value) throws IllegalArgumentException {
		player.incrementStatistic(statistic, value);
	}

	@Override
	public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
		player.incrementStatistic(statistic, material);
	}

	@Override
	public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
		player.incrementStatistic(statistic, entityType);
	}

	@Override
	public void incrementStatistic(Statistic statistic, Material material, int value) throws IllegalArgumentException {
		player.incrementStatistic(statistic, material, value);
	}

	@Override
	public void incrementStatistic(Statistic statistic, EntityType entityType, int value) throws IllegalArgumentException {
		player.incrementStatistic(statistic, entityType, value);
	}

	@Override
	public boolean isFlying() {
		return player.isFlying();
	}

	@Override
	public boolean isHealthScaled() {
		return player.isHealthScaled();
	}

	@Override
	public boolean isPlayerTimeRelative() {
		return player.isPlayerTimeRelative();
	}

	@Override
	public boolean isSleepingIgnored() {
		return player.isSleepingIgnored();
	}

	@Override
	public boolean isSneaking() {
		return player.isSneaking();
	}

	@Override
	public boolean isSprinting() {
		return player.isSprinting();
	}

	@Override
	@Deprecated
	public void kickPlayer(String string) {
		player.kickPlayer(string);
	}

	@Override
	public void loadData() {
		player.loadData();
	}

	@Override
	public boolean performCommand(String string) {
		return player.performCommand(string);
	}

	@Override
	@Deprecated
	public void playEffect(Location location, Effect effect, int value) {
		player.playEffect(location, effect, value);
	}

	@Override
	public <T> void playEffect(Location location, Effect effect, T t) {
		player.playEffect(location, effect, t);
	}

	@Override
	@Deprecated
	public void playNote(Location location, byte bytes1, byte bytes2) {
		player.playNote(location, bytes1, bytes2);
	}

	@Override
	public void playNote(Location location, Instrument instrument, Note note) {
		player.playNote(location, instrument, note);
	}

	@Override
	public void playSound(Location location, Sound sound, float value1, float value2) {
		player.playSound(location, sound, value1, value2);
	}

	@Override
	public void playSound(Location location, String string, float value1, float value2) {
		player.playSound(location, string, value1, value2);
	}

	@Override
	public void playSound(Location location, Sound sound, SoundCategory soundCategory, float value1, float value2) {
		player.playSound(location, sound, soundCategory, value1, value2);
	}

	@Override
	public void playSound(Location location, String string, SoundCategory soundCategory, float value1, float value2) {
		player.playSound(location, string, soundCategory, value1, value2);
	}

	@Override
	public void removeAchievement(Achievement achievement) {
		player.removeAchievement(achievement);
	}

	@Override
	public void resetPlayerTime() {
		player.resetPlayerTime();
	}

	@Override
	public void resetPlayerWeather() {
		player.resetPlayerWeather();
	}

	@Override
	public void resetTitle() {
		player.resetTitle();
	}

	@Override
	public void saveData() {
		player.saveData();
		Logger.getLogger("Minecraft").info("Player.saveData(): void");
	}

	@Override
	@Deprecated
	public void sendBlockChange(Location location, Material material, byte bytes) {
		player.sendBlockChange(location, material, bytes);
	}

	@Override
	@Deprecated
	public void sendBlockChange(Location location, int value, byte bytes) {
		player.sendBlockChange(location, value, bytes);
	}

	@Override
	@Deprecated
	public boolean sendChunkChange(Location location, int value1, int value2, int value3, byte[] bytes) {
		return player.sendChunkChange(location, value1, value2, value3, bytes);
	}

	@Override
	public void sendMap(MapView mapView) {
		player.sendMap(mapView);
	}

	@Override
	@Deprecated
	public void sendRawMessage(String message) {
		player.sendRawMessage(message);
	}

	@Override
	public void sendSignChange(Location location, String[] string) throws IllegalArgumentException {
		player.sendSignChange(location, string);
	}

	@Override
	@Deprecated
	public void sendTitle(String string1, String string2) {
		player.sendTitle(string1, string2);
	}

	@Override
	public void sendTitle(String string1, String string2, int value1, int value2, int value3) {
		player.sendTitle(string1, string2, value1, value2, value3);
	}

	@Override
	public void setAllowFlight(boolean value) {
		player.setAllowFlight(value);
		Logger.getLogger("Minecraft").info("Player.setAllowFlight(boolean value): void");
	}

	@Override
	public void setBedSpawnLocation(Location location) {
		player.setBedSpawnLocation(location);
	}

	@Override
	public void setBedSpawnLocation(Location location, boolean value) {
		player.setBedSpawnLocation(location, value);
	}

	@Override
	public void setCompassTarget(Location location) {
		player.setCompassTarget(location);
	}

	@Override
	public void setDisplayName(String string) {
		displayName = string;
		player.setDisplayName(string);
	}

	@Override
	public void setExhaustion(float value) {
		player.setExhaustion(value);
	}

	@Override
	public void setExp(float value) {
		player.setExp(value);
	}

	@Override
	public void setFlySpeed(float value) throws IllegalArgumentException {
		flySpeed = value;
		player.setFlySpeed(value);
		Logger.getLogger("Minecraft").info("Player.setFlySpeed(float value): void");
	}

	@Override
	public void setFlying(boolean value) {
		player.setFlying(value);
		Logger.getLogger("Minecraft").info("Player.setFlying(boolean value): void");
	}

	@Override
	public void setFoodLevel(int value) {
		player.setFoodLevel(value);
	}

	@Override
	public void setHealthScale(double value) throws IllegalArgumentException {
		player.setHealthScale(value);
	}

	@Override
	public void setHealthScaled(boolean value) {
		player.setHealthScaled(value);
	}

	@Override
	public void setLevel(int value) {
		player.setLevel(value);
	}

	@Override
	public void setPlayerListName(String string) {
		player.setPlayerListName(string);
	}

	@Override
	public void setPlayerTime(long value1, boolean value2) {
		player.setPlayerTime(value1, value2);
	}

	@Override
	public void setPlayerWeather(WeatherType weatherType) {
		player.setPlayerWeather(weatherType);
	}

	@Override
	public void setResourcePack(String string) {
		player.setResourcePack(string);
	}

	@Override
	public void setSaturation(float value) {
		player.setSaturation(value);
	}

	@Override
	public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
		player.setScoreboard(scoreboard);
	}

	@Override
	public void setSleepingIgnored(boolean value) {
		player.setSleepingIgnored(value);
	}

	@Override
	public void setSneaking(boolean value) {
		player.setSneaking(value);
	}

	@Override
	public void setSpectatorTarget(Entity entity) {
		player.setSpectatorTarget(entity);
	}

	@Override
	public void setSprinting(boolean value) {
		player.setSprinting(value);
	}

	@Override
	public void setStatistic(Statistic statistic, int value) throws IllegalArgumentException {
		player.setStatistic(statistic, value);
	}

	@Override
	public void setStatistic(Statistic statistic, Material material, int value) throws IllegalArgumentException {
		player.setStatistic(statistic, material, value);
	}

	@Override
	public void setStatistic(Statistic statistic, EntityType entityType, int value) {
		player.setStatistic(statistic, entityType, value);
	}

	@Override
	@Deprecated
	public void setTexturePack(String string) {
		player.setTexturePack(string);
	}

	@Override
	public void setTotalExperience(int value) {
		player.setTotalExperience(value);
	}

	@Override
	public void setWalkSpeed(float value) throws IllegalArgumentException {
		walkSpeed = value;
		player.setWalkSpeed(value);
		Logger.getLogger("Minecraft").info("Player.setWalkSpeed(float value): void");
	}

	@Override
	public void showPlayer(Player player) {
		player.showPlayer(player);
	}
	
	public void showPlayer(PlayerCharacter playerCharacter) {
		player.showPlayer(playerCharacter.player);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int value) {
		player.spawnParticle(particle, location, value);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int value, T t) {
		player.spawnParticle(particle, location, value, t);
	}

	@Override
	public void spawnParticle(Particle particle, double value1, double value2, double value3, int value4) {
		player.spawnParticle(particle, value1, value2, value3, value4);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double value1, double value2, double value3, int value4, T t) {
		player.spawnParticle(particle, value1, value2, value3, value4, t);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int value1, double value2, double value3, double value4) {
		player.spawnParticle(particle, location, value1, value2, value3, value4);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int value1, double value2, double value3, double value4,
			T t) {
		player.spawnParticle(particle, location, value1, value2, value3, value4, t);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int value1, double value2, double value3, double value4,
			double value5) {
		player.spawnParticle(particle, location, value1, value2, value3, value4, value5);
	}

	@Override
	public void spawnParticle(Particle particle, double value1, double value2, double value3, int value4, double value5, double value6,
			double value7) {
		player.spawnParticle(particle, value1, value2, value3, value4, value5, value6, value7);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int value1, double value2, double value3, double value4,
			double value5, T t) {
		player.spawnParticle(particle, location, value1, value2, value3, value4, value5, t);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double value1, double value2, double value3, int value4, double value5,
			double value6, double value7, T t) {
		player.spawnParticle(particle, value1, value2, value3, value4, value5, value6, value7, t);
	}

	@Override
	public void spawnParticle(Particle particle, double value1, double value2, double value3, int value4, double value5, double value6,
			double value7, double value8) {
		player.spawnParticle(particle, value1, value2, value3, value4, value5, value6, value7, value8);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double value1, double value2, double value3, int value4, double value5,
			double value6, double value7, double value8, T t) {
		player.spawnParticle(particle, value1, value2, value3, value4, value5, value6, value7, value8, t);
	}

	@Override
	public Spigot spigot() {
		return player.spigot();
	}

	@Override
	public void stopSound(Sound sound) {
		player.stopSound(sound);
	}

	@Override
	public void stopSound(String string) {
		player.stopSound(string);
	}

	@Override
	public void stopSound(Sound sound, SoundCategory soundCategory) {
		player.stopSound(sound, soundCategory);
	}

	@Override
	public void stopSound(String string, SoundCategory soundCategory) {
		player.stopSound(string, soundCategory);
	}

	@Override
	public void updateInventory() {
		player.updateInventory();
	}
}