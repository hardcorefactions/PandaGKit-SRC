/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 */
package dev.panda.gkit.listeners;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.utilities.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BookListener
implements Listener {
    private final PandaGKit plugin;

    public BookListener(PandaGKit plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    @EventHandler
    private void onBook(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        ItemStack cursorItem = event.getCursor();
        ItemStack currentItem = event.getCurrentItem();
        if (cursorItem != null && currentItem != null && !cursorItem.getType().equals((Object)Material.AIR) && !currentItem.getType().equals((Object)Material.AIR) && (currentItem.getType().name().endsWith("_HELMET") || currentItem.getType().name().endsWith("_CHESTPLATE") || currentItem.getType().name().endsWith("_LEGGINGS") || currentItem.getType().name().endsWith("_BOOTS"))) {
            ICustomEnchant customEnchant = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByItem(cursorItem);
            if (customEnchant == null) {
                return;
            }
            event.setCancelled(true);
            customEnchant.apply(currentItem);
            PlayerUtil.decrement(player, cursorItem, true, true);
        }
    }
}

