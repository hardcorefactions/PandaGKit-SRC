/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu.buttons.edit;

import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitDisplayButton
extends Button {
    private final Kit kit;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.kit.getMaterial()).setData(this.kit.getData()).setName(this.kit.getDisplayName()).setEnchant(this.kit.isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL).setOwner(this.kit.getSkullOwner()).build();
    }

    public KitDisplayButton(Kit kit) {
        this.kit = kit;
    }
}

