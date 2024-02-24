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
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CategoryCreateButton
extends Button {
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.NETHER_STAR).setName("&3Create Category").setLore("&8&m-----------------------------", "&7Click to create a new category.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategoryCreate().put(player.getUniqueId(), "categoryCreate");
        CategoryCreateButton.playSuccess(player);
        ChatUtil.sendMessage((CommandSender)player, "&ePlease type the new category's name.");
        ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
        player.closeInventory();
    }

    public CategoryCreateButton(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

