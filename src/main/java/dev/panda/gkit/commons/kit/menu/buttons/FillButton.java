/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu.buttons;

import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FillButton
extends Button {
    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(ConfigService.MENU_FILL_MATERIAL).setData(ConfigService.MENU_FILL_DATA).setName(" ").build();
    }
}

