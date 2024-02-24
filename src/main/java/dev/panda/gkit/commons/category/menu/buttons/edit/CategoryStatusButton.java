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

public class CategoryStatusButton
extends Button {
    private final Category category;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.category.isEnable() ? CompatibleMaterial.REDSTONE_TORCH.getMaterial() : Material.LEVER).setName("&3Change Status").setLore("&8&m-----------------------------", "&fChange the status of the category.", "", "&7Status: " + (this.category.isEnable() ? "&aEnabled" : "&cDisabled"), "", "&fClick to toggle status.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        CategoryStatusButton.playSuccess(player);
        this.category.setEnable(!this.category.isEnable());
        this.category.save();
    }

    public CategoryStatusButton(Category category) {
        this.category = category;
    }
}

