/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.category.menu.buttons.edit;

import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CategoryDisplayButton
extends Button {
    private final Category category;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.category.getMaterial()).setData(this.category.getData()).setName(this.category.getDisplayName()).setLore(this.category.getDescription()).setEnchant(this.category.isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL).setOwner(this.category.getSkullOwner()).build();
    }

    public CategoryDisplayButton(Category category) {
        this.category = category;
    }
}

