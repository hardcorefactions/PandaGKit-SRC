/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.category.menu.buttons.edit;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.commons.category.menu.CategorySlotMenu;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CategorySlotButton
extends Button {
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.ANVIL).setName("&3Change Slot").setLore("&8&m-----------------------------", "&fChange the slot of the category.", "", "&7Slot: &f" + this.category.getSlot(), "", "&fClick to edit slot.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new CategorySlotMenu(this.category, this.plugin).openMenu(player);
        CategorySlotButton.playNeutral(player);
    }

    public CategorySlotButton(Category category, PandaGKit plugin) {
        this.category = category;
        this.plugin = plugin;
    }
}

