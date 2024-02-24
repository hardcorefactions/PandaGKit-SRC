/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.category.menu.buttons;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ClearCategoryButton
extends Button {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.REDSTONE_BLOCK).setName("&c&lClear Category").setLore("&8&m-----------------------------", "&7Click to remove the current category from", "&7the Kit " + this.kit.getName(), "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (this.kit.getCategory() == null) {
            ClearCategoryButton.playFail(player);
            ChatUtil.sendMessage((CommandSender)player, "&cThe Kit " + this.kit.getName() + " doesn't have a category!");
        } else {
            ClearCategoryButton.playSuccess(player);
            this.kit.setCategory(null);
            this.kit.save();
            ChatUtil.sendMessage((CommandSender)player, "&aYou successfully removed the category from the Kit " + this.kit.getName() + "!");
        }
    }

    public ClearCategoryButton(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

