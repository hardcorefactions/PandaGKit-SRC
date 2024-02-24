/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu.buttons.edit;

import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitGlowButton
extends Button {
    private final Kit kit;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.kit.isGlow() ? CompatibleMaterial.ENCHANTMENT_TABLE.getMaterial() : CompatibleMaterial.ENDER_PORTAL_FRAME.getMaterial()).setName("&d\u2735 &5Change Glow &d\u2735").setLore("&8&m-----------------------------", "&7Change the glow of kit.", "", "&7Glow: " + (this.kit.isGlow() ? "&aEnabled" : "&cDisabled"), "", "&eClick to toggle glow.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        KitGlowButton.playSuccess(player);
        this.kit.setGlow(!this.kit.isGlow());
        this.kit.save();
    }

    public KitGlowButton(Kit kit) {
        this.kit = kit;
    }
}

