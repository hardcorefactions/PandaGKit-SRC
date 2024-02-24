/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
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

public class KitShowMenuButton
extends Button {
    private final Kit kit;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.kit.isShowMenu() ? CompatibleMaterial.STORAGE_MINECART.getMaterial() : Material.MINECART).setName("&d\u2735 &5Change Show Menu &d\u2735").setLore("&8&m-----------------------------", "&7Change the show menu of kit.", "", "&7Show Menu: " + (this.kit.isShowMenu() ? "&aEnabled" : "&cDisabled"), "", "&eClick to toggle show menu.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        KitShowMenuButton.playSuccess(player);
        this.kit.setShowMenu(!this.kit.isShowMenu());
        this.kit.save();
    }

    public KitShowMenuButton(Kit kit) {
        this.kit = kit;
    }
}

