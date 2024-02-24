/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.customenchant.menu.buttons;

import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantButton
extends Button {
    private final ICustomEnchant ce;

    @Override
    public ItemStack getButtonItem(Player player) {
        return this.ce.getItem();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        CustomEnchantButton.playSuccess(player);
        this.ce.give(player, 1);
    }

    public CustomEnchantButton(ICustomEnchant ce) {
        this.ce = ce;
    }
}

