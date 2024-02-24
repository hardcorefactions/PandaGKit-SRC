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
package dev.panda.gkit.commons.kit.menu.buttons.edit;

import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class KitIconButton
extends Button {
    private final Kit kit;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.ITEM_FRAME).setName("&d\u2735 &5Change Icon &d\u2735").setLore("&8&m-----------------------------", "&7Change the icon of kit.", "", "&eDrag an item to set as the icon.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        ItemStack kitItem = new ItemBuilder(this.kit.getMaterial()).setData(this.kit.getData()).build();
        if (player.getItemOnCursor() != null && !player.getItemOnCursor().getType().equals((Object)Material.AIR) && !player.getItemOnCursor().isSimilar(kitItem)) {
            this.kit.setMaterial(player.getItemOnCursor().getType());
            this.kit.setData(player.getItemOnCursor().getDurability());
            if (player.getItemOnCursor().getType().equals((Object)CompatibleMaterial.HUMAN_SKULL.getMaterial())) {
                SkullMeta meta = (SkullMeta)player.getItemOnCursor().getItemMeta();
                this.kit.setSkullOwner(meta.getOwner());
            }
            this.kit.save();
            KitIconButton.playSuccess(player);
        }
    }

    public KitIconButton(Kit kit) {
        this.kit = kit;
    }
}

