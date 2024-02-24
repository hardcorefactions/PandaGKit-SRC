/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.utilities.menu;

import com.google.common.collect.Maps;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class Menu {
    private static Map<UUID, Menu> menus = Maps.newHashMap();
    private Map<Integer, Button> buttons = Maps.newHashMap();
    private boolean updateAfterClick;
    private boolean closedByMenu;
    private boolean placeholder;
    private boolean cancelPlayerInventory = true;
    private Button placeholderButton;

    private ItemStack createItemStack(Player player, Button button) {
        return new ItemBuilder(button.getButtonItem(player)).build();
    }

    public void openMenu(Player player) {
        this.buttons = this.getButtons(player);
        Menu previousMenu = Menu.getMenu(player);
        Inventory inventory = null;
        String title = this.getTitle(player);
        int size = this.getSize() == -1 ? this.size(this.buttons) : this.getSize();
        boolean update = false;
        if (title.length() > 32) {
            title = title.substring(0, 32);
        }
        if (player.getOpenInventory() != null) {
            if (previousMenu == null) {
                player.closeInventory();
            } else {
                int previousSize = player.getOpenInventory().getTopInventory().getSize();
                if (previousSize == size && player.getOpenInventory().getTitle().equals(title)) {
                    inventory = player.getOpenInventory().getTopInventory();
                    update = true;
                } else {
                    previousMenu.setClosedByMenu(true);
                    player.closeInventory();
                }
            }
        }
        if (inventory == null) {
            inventory = Bukkit.createInventory((InventoryHolder)player, (int)size, (String)title);
        }
        inventory.setContents(new ItemStack[inventory.getSize()]);
        menus.put(player.getUniqueId(), this);
        for (Map.Entry<Integer, Button> buttonEntry : this.buttons.entrySet()) {
            inventory.setItem(buttonEntry.getKey().intValue(), this.createItemStack(player, buttonEntry.getValue()));
        }
        if (this.isPlaceholder() && this.getPlaceholderButton() != null) {
            Button fillButton = this.getPlaceholderButton();
            for (int index = 0; index < size; ++index) {
                if (this.buttons.get(index) != null) continue;
                this.buttons.put(index, fillButton);
                inventory.setItem(index, fillButton.getButtonItem(player));
            }
        }
        if (update) {
            player.updateInventory();
        } else {
            player.openInventory(inventory);
        }
        this.setClosedByMenu(false);
    }

    public int size(Map<Integer, Button> buttons) {
        int highest = 0;
        for (int buttonValue : buttons.keySet()) {
            if (buttonValue <= highest) continue;
            highest = buttonValue;
        }
        return (int)(Math.ceil((double)(highest + 1) / 9.0) * 9.0);
    }

    public int getSlot(int x2, int y2) {
        return 9 * y2 + x2;
    }

    public int getSize() {
        return -1;
    }

    public Button getPlaceholderButton() {
        return null;
    }

    public void close(Player player) {
        menus.remove(player.getUniqueId());
    }

    public abstract String getTitle(Player var1);

    public abstract Map<Integer, Button> getButtons(Player var1);

    public static Menu getMenu(Player player) {
        return menus.get(player.getUniqueId());
    }

    public boolean isUpdateAfterClick() {
        return this.updateAfterClick;
    }

    public boolean isClosedByMenu() {
        return this.closedByMenu;
    }

    public boolean isPlaceholder() {
        return this.placeholder;
    }

    public boolean isCancelPlayerInventory() {
        return this.cancelPlayerInventory;
    }

    public void setButtons(Map<Integer, Button> buttons) {
        this.buttons = buttons;
    }

    public void setUpdateAfterClick(boolean updateAfterClick) {
        this.updateAfterClick = updateAfterClick;
    }

    public void setClosedByMenu(boolean closedByMenu) {
        this.closedByMenu = closedByMenu;
    }

    public void setPlaceholder(boolean placeholder) {
        this.placeholder = placeholder;
    }

    public void setCancelPlayerInventory(boolean cancelPlayerInventory) {
        this.cancelPlayerInventory = cancelPlayerInventory;
    }

    public void setPlaceholderButton(Button placeholderButton) {
        this.placeholderButton = placeholderButton;
    }

    public static Map<UUID, Menu> getMenus() {
        return menus;
    }

    public Map<Integer, Button> getButtons() {
        return this.buttons;
    }
}

