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

import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CategoryShowMenuButton
extends Button {
    private final Category category;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.category.isShowMenu() ? CompatibleMaterial.STORAGE_MINECART.getMaterial() : Material.MINECART).setName("&3Change Show Menu").setLore("&8&m-----------------------------", "&fChange the show menu of the category.", "", "&7Show Menu: " + (this.category.isShowMenu() ? "&aEnabled" : "&cDisabled"), "", "&fClick to toggle show menu.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        CategoryShowMenuButton.playSuccess(player);
        this.category.setShowMenu(!this.category.isShowMenu());
        this.category.save();
    }

    public CategoryShowMenuButton(Category category) {
        this.category = category;
    }
}

