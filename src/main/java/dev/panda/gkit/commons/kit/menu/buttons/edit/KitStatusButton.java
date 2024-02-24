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

public class KitStatusButton
extends Button {
    private final Kit kit;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.kit.isEnable() ? CompatibleMaterial.REDSTONE_TORCH.getMaterial() : Material.LEVER).setName("&d\u2735 &5Change Status &d\u2735").setLore("&8&m-----------------------------", "&7Change the status of kit.", "", "&7Status: " + (this.kit.isEnable() ? "&aEnabled" : "&cDisabled"), "", "&eClick to toggle status.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        KitStatusButton.playSuccess(player);
        this.kit.setEnable(!this.kit.isEnable());
        this.kit.save();
    }

    public KitStatusButton(Kit kit) {
        this.kit = kit;
    }
}

