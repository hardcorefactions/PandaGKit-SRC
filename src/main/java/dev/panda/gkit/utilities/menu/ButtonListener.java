/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.plugin.Plugin
 */
package dev.panda.gkit.utilities.menu;

import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

public class ButtonListener
implements Listener {
    public ButtonListener(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents((Listener)this, plugin);
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    private void onButtonPress(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        Menu menu = Menu.getMenu(player);
        if (menu != null) {
            event.setCancelled(menu.isCancelPlayerInventory());
            if (event.getSlot() != event.getRawSlot()) {
                if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
                    event.setCancelled(false);
                }
                return;
            }
            if (menu.getButtons().containsKey(event.getSlot())) {
                Button button = menu.getButtons().get(event.getSlot());
                boolean shouldCancel = button.shouldCancel(player, event.getSlot(), event.getClick());
                boolean shouldShift = button.shouldShift(player, event.getSlot(), event.getClick());
                if (shouldCancel && shouldShift) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(shouldCancel);
                }
                button.clicked(player, event.getSlot(), event.getClick(), event.getHotbarButton());
                if (Menu.getMenus().containsKey(player.getUniqueId())) {
                    Menu newMenu = Menu.getMenu(player);
                    if (newMenu == menu && menu.isUpdateAfterClick()) {
                        menu.setClosedByMenu(true);
                        newMenu.openMenu(player);
                    }
                } else if (button.shouldUpdate(player, event.getSlot(), event.getClick())) {
                    menu.setClosedByMenu(true);
                    menu.openMenu(player);
                }
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGH)
    private void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player)event.getPlayer();
        Menu menu = Menu.getMenu(player);
        if (menu != null) {
            menu.close(player);
        }
    }
}

