/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.SkullMeta
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
import org.bukkit.inventory.meta.SkullMeta;

public class CategoryIconButton
extends Button {
    private final Category category;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.ITEM_FRAME).setName("&3Change Icon").setLore("&8&m-----------------------------", "&fChange the icon of the category.", "", "&fDrag an item to set as the icon.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        ItemStack categoryItem = new ItemBuilder(this.category.getMaterial()).setData(this.category.getData()).build();
        if (player.getItemOnCursor() != null && !player.getItemOnCursor().getType().equals((Object)Material.AIR) && !player.getItemOnCursor().isSimilar(categoryItem)) {
            this.category.setMaterial(player.getItemOnCursor().getType());
            this.category.setData(player.getItemOnCursor().getDurability());
            if (player.getItemOnCursor().getType().equals((Object)CompatibleMaterial.HUMAN_SKULL.getMaterial())) {
                SkullMeta meta = (SkullMeta)player.getItemOnCursor().getItemMeta();
                this.category.setSkullOwner(meta.getOwner());
            }
            this.category.save();
            CategoryIconButton.playSuccess(player);
        }
    }

    public CategoryIconButton(Category category) {
        this.category = category;
    }
}

