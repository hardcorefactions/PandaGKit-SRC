// Decompiled with: Procyon 0.6.0
// Class Version: 8
package dev.panda.gkit.listeners;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.listeners.events.ArmorEquipEvent;
import dev.panda.gkit.listeners.events.ArmorUnEquipEvent;
import dev.panda.gkit.listeners.events.PotionRemoveEvent;
import dev.panda.gkit.utilities.PlayerUtil;
import dev.panda.gkit.utilities.PotionUtil;
import dev.panda.gkit.utilities.TaskUtil;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ArmorListener implements Listener
{
    private final PandaGKit plugin;
    private final Table<UUID, PotionEffectType, Integer> permanentPotions;

    public ArmorListener(final PandaGKit plugin) {
        this.plugin = plugin;
        this.permanentPotions = HashBasedTable.create();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEquip(final ArmorEquipEvent event) {
        final ItemStack[] armor2;
        final ItemStack[] playerArmors = armor2 = event.getArmor();
        for (final ItemStack armor : armor2) {
            if (armor != null && armor.hasItemMeta() && Objects.requireNonNull(armor.getItemMeta()).hasLore()) {
                final Player player = event.getPlayer();
                final List<ICustomEnchant> customEnchants = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByArmorItem(armor);
                if (!customEnchants.isEmpty()) {
                    for (final ICustomEnchant customEnchant : customEnchants) {
                        if (customEnchant.getEffects().length != 0 && customEnchant.getProperties().getLevel() != null && customEnchant.getProperties().getLevel() > 0) {
                            final PotionEffectType[] effects;
                            final PotionEffectType[] var10 = effects = customEnchant.getEffects();
                            for (final PotionEffectType effect : effects) {
                                final PotionEffect potionEffect = new PotionEffect(effect, Integer.MAX_VALUE, PotionUtil.isLeveleable(effect) ? (customEnchant.getProperties().getLevel() - 1) : 0);
                                player.addPotionEffect(potionEffect);
                                this.permanentPotions.put(player.getUniqueId(), potionEffect.getType(), potionEffect.getAmplifier());
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onUnEquip(final ArmorUnEquipEvent event) {
        final ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasLore()) {
            final Player player = event.getPlayer();
            final List<ICustomEnchant> customEnchants = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByArmorItem(item);
            if (!customEnchants.isEmpty()) {
                for (final ICustomEnchant customEnchant : customEnchants) {
                    if (customEnchant.getProperties().getLevel() != null && customEnchant.getProperties().getLevel() > 0 && customEnchant.getEffects().length != 0) {
                        final PotionEffectType[] effects;
                        final PotionEffectType[] var6 = effects = customEnchant.getEffects();
                        for (final PotionEffectType effect : effects) {
                            player.removePotionEffect(effect);
                            this.permanentPotions.remove(player.getUniqueId(), effect);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArmorAddInvClick(final InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && (event.getSlotType() == InventoryType.SlotType.ARMOR || event.isShiftClick())) {
            final Material material1 = Objects.requireNonNull(event.getCursor()).getType();
            final Material material2 = Objects.requireNonNull(event.getCurrentItem()).getType();
            if (material1.name().endsWith("_HELMET") || material1.name().endsWith("_CHESTPLATE") || material1.name().endsWith("_LEGGINGS") || material1.name().endsWith("_BOOTS") || material2.name().endsWith("_HELMET") || material2.name().endsWith("_CHESTPLATE") || material2.name().endsWith("_LEGGINGS") || material2.name().endsWith("_BOOTS")) {
                final Player player = (Player)event.getWhoClicked();
                TaskUtil.runLater(() -> {
                    final ItemStack[] armor = player.getInventory().getArmorContents();
                    Bukkit.getPluginManager().callEvent(new ArmorEquipEvent(player, armor));
                }, 1L);
            }
        }
    }

    @EventHandler
    private void onArmorRemoveInvClick(final InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && event.getSlotType() == InventoryType.SlotType.ARMOR) {
            final Material material = Objects.requireNonNull(event.getCurrentItem()).getType();
            if (material.name().endsWith("_HELMET") || material.name().endsWith("_CHESTPLATE") || material.name().endsWith("_LEGGINGS") || material.name().endsWith("_BOOTS")) {
                final Player player = (Player)event.getWhoClicked();
                Bukkit.getPluginManager().callEvent(new ArmorUnEquipEvent(player, event.getCurrentItem()));
            }
        }
    }

    @EventHandler
    private void onArmorInteract(final PlayerInteractEvent event) {
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.hasItem() && Objects.requireNonNull(event.getItem()).hasItemMeta()) {
            final Material material = event.getItem().getType();
            if (material.name().endsWith("_HELMET") || material.name().endsWith("_CHESTPLATE") || material.name().endsWith("_LEGGINGS") || material.name().endsWith("_BOOTS")) {
                final Player player = event.getPlayer();
                TaskUtil.runLater(() -> {
                    final ItemStack[] armor = player.getInventory().getArmorContents();
                    Bukkit.getPluginManager().callEvent(new ArmorEquipEvent(player, armor));
                }, 1L);
            }
        }
    }

    @EventHandler
    private void onArmorBreak(final PlayerItemBreakEvent event) {
        final Material material = event.getBrokenItem().getType();
        if (material.name().endsWith("_HELMET") || material.name().endsWith("_CHESTPLATE") || material.name().endsWith("_LEGGINGS") || material.name().endsWith("_BOOTS")) {
            Bukkit.getPluginManager().callEvent(new ArmorUnEquipEvent(event.getPlayer(), event.getBrokenItem()));
        }
    }

    @EventHandler
    private void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        TaskUtil.runLater(() -> Bukkit.getPluginManager().callEvent(new ArmorEquipEvent(player, player.getInventory().getArmorContents())), 1L);
    }

    @EventHandler
    private void onRespawn(final PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        TaskUtil.runLater(() -> Bukkit.getPluginManager().callEvent(new ArmorEquipEvent(player, player.getInventory().getArmorContents())), 1L);
    }

    @EventHandler
    public void onConsume(final PlayerItemConsumeEvent event) {
        final Player player = event.getPlayer();
        if (event.getItem().getType() == CompatibleMaterial.MILK_BUCKET.getMaterial()) {
            event.setCancelled(true);
            if (player.getGameMode() != GameMode.CREATIVE) {
                PlayerUtil.decrement(player, player.getItemInHand(), false, false);
            }
            final ItemStack[] armorContents;
            final ItemStack[] var3 = armorContents = player.getInventory().getArmorContents();
            for (final ItemStack item : armorContents) {
                if (item != null && item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasLore()) {
                    final List<ICustomEnchant> customEnchants = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByArmorItem(item);
                    if (!customEnchants.isEmpty()) {
                        for (final ICustomEnchant customEnchant : customEnchants) {
                            if (customEnchant.getProperties().getLevel() != null && customEnchant.getProperties().getLevel() > 0 && customEnchant.getEffects().length != 0) {
                                for (final PotionEffectType effect : PotionUtil.getPotionsExcluding(player, customEnchant.getEffects())) {
                                    player.removePotionEffect(effect);
                                    this.permanentPotions.remove(player.getUniqueId(), effect);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final Location from = event.getFrom();
        final Location to = event.getTo();
        if (!event.isCancelled() && to != null && (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) && (from.getBlockX() != to.getBlockX() || from.getBlockY() == to.getBlockY() || from.getBlockZ() != to.getBlockZ())) {
            final ItemStack[] armorContents;
            final ItemStack[] var5 = armorContents = player.getInventory().getArmorContents();
            for (final ItemStack item : armorContents) {
                if (item != null && item.getType() != Material.AIR) {
                    final List<ICustomEnchant> customEnchants = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByArmorItem(item);
                    if (!customEnchants.isEmpty()) {
                        for (final ICustomEnchant customEnchant : customEnchants) {
                            customEnchant.onMove(player);
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerKill(final PlayerDeathEvent event) {
        final Player player = event.getEntity();
        if (player.getKiller() != null) {
            final Player killer = player.getKiller();
            final ItemStack[] armorContents;
            final ItemStack[] var4 = armorContents = killer.getInventory().getArmorContents();
            for (final ItemStack item : armorContents) {
                if (item != null && item.getType() != Material.AIR) {
                    final List<ICustomEnchant> customEnchants = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByArmorItem(item);
                    if (!customEnchants.isEmpty()) {
                        for (final ICustomEnchant customEnchant : customEnchants) {
                            customEnchant.onKill(player, killer);
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerHit(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player)event.getEntity();
            if (event.getDamager() instanceof Player) {
                final Player damager = (Player)event.getDamager();
                final ItemStack[] armorContents;
                final ItemStack[] var4 = armorContents = damager.getInventory().getArmorContents();
                for (final ItemStack item : armorContents) {
                    if (item != null && item.getType() != Material.AIR) {
                        final List<ICustomEnchant> customEnchants = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByArmorItem(item);
                        if (!customEnchants.isEmpty()) {
                            for (final ICustomEnchant customEnchant : customEnchants) {
                                final double damage = customEnchant.onHit(player, damager, event.getDamage());
                                if (damage != event.getDamage()) {
                                    event.setDamage(damage);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerEffect(final PotionRemoveEvent event) {
        final Player player = event.getPlayer();
        final PotionEffect effect = event.getEffect();
        if (effect != null && this.permanentPotions.contains(player.getUniqueId(), effect.getType())) {
            final Integer amplifier = this.permanentPotions.get(player.getUniqueId(), effect.getType());
            if (amplifier != null) {
                player.addPotionEffect(new PotionEffect(effect.getType(), Integer.MAX_VALUE, amplifier));
            }
        }
    }
}
