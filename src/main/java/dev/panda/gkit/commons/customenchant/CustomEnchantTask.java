/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 */
package dev.panda.gkit.commons.customenchant;

import com.google.common.collect.Maps;
import dev.panda.gkit.listeners.events.ArmorUnEquipEvent;
import dev.panda.gkit.listeners.events.PotionRemoveEvent;
import dev.panda.gkit.utilities.PotionUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomEnchantTask
extends BukkitRunnable {
    private final Map<UUID, List<PotionEffect>> lastTickPotions = Maps.newHashMap();
    private final Map<UUID, List<ItemStack>> lastTickArmor = Maps.newHashMap();

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            List<PotionEffectType> actualPotions = PotionUtil.getPotionTypes(player);
            if (this.lastTickPotions.containsKey(player.getUniqueId())) {
                for (PotionEffect potionEffect : this.lastTickPotions.get(player.getUniqueId())) {
                    if (actualPotions.contains(potionEffect.getType())) continue;
                    Bukkit.getPluginManager().callEvent((Event)new PotionRemoveEvent(player, potionEffect));
                }
            }
            this.lastTickPotions.put(player.getUniqueId(), (List)player.getActivePotionEffects());
            List<ItemStack> actualArmor = Arrays.asList(player.getInventory().getArmorContents());
            if (this.lastTickArmor.containsKey(player.getUniqueId())) {
                for (ItemStack item : this.lastTickArmor.get(player.getUniqueId())) {
                    if (actualArmor.contains(item)) continue;
                    Bukkit.getPluginManager().callEvent((Event)new ArmorUnEquipEvent(player, item));
                }
            }
            this.lastTickArmor.put(player.getUniqueId(), actualArmor);
        }
    }
}

