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
package dev.panda.gkit.commons.category.menu.buttons.edit;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CategoryDisplayNameButton
extends Button {
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.NAME_TAG).setName("&3Change DisplayName").setLore("&8&m-----------------------------", "&fChange the display name of the category.", "", "&7DisplayName: &f" + this.category.getDisplayName(), "", "&fClick to edit the display name.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategoryEdit().put("categoryDisplayName", player.getUniqueId(), this.category);
        CategoryDisplayNameButton.playSuccess(player);
        ChatUtil.sendMessage((CommandSender)player, "&eYou're now editing name of '&f" + this.category.getName() + "&e'.");
        ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
        player.closeInventory();
    }

    public CategoryDisplayNameButton(Category category, PandaGKit plugin) {
        this.category = category;
        this.plugin = plugin;
    }
}

