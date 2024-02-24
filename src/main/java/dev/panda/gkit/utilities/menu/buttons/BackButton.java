/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.utilities.menu.buttons;

import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BackButton
extends Button {
    private final Menu back;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.REDSTONE).setName("&c&lBack").setLore("&cClick here to return", "&cto the previous menu.").build();
    }

    @Override
    public void clicked(Player player, int i2, ClickType clickType, int hb) {
        Button.playNeutral(player);
        this.back.openMenu(player);
    }

    public BackButton(Menu back) {
        this.back = back;
    }
}

