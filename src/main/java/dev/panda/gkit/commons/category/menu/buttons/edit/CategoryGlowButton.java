/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.category.menu.buttons.edit;

import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CategoryGlowButton
extends Button {
    private final Category category;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.category.isGlow() ? CompatibleMaterial.ENCHANTMENT_TABLE.getMaterial() : CompatibleMaterial.ENDER_PORTAL_FRAME.getMaterial()).setName("&3Change Glow").setLore("&8&m-----------------------------", "&fChange the glow of the category.", "", "&7Glow: " + (this.category.isGlow() ? "&aEnabled" : "&cDisabled"), "", "&fClick to toggle glow.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        CategoryGlowButton.playSuccess(player);
        this.category.setGlow(!this.category.isGlow());
        this.category.save();
    }

    public CategoryGlowButton(Category category) {
        this.category = category;
    }
}

