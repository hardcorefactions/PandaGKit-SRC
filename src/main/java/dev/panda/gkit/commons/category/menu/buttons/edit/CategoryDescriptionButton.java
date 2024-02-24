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

public class CategoryDescriptionButton
extends Button {
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.PAPER).setName("&3Change Description").setLore("&8&m-----------------------------", "&fChange the description of the category.", "", "&fClick to edit the description.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategoryEdit().put("categoryDescription", player.getUniqueId(), this.category);
        CategoryDescriptionButton.playSuccess(player);
        ChatUtil.sendMessage((CommandSender)player, "&eYou're now editing lore of '&f" + this.category.getName() + "'&e.");
        ChatUtil.sendMessage((CommandSender)player, "");
        ChatUtil.sendMessage((CommandSender)player, "&eAvailables command messages");
        ChatUtil.sendMessage((CommandSender)player, " &7\u25b6 &eadd <lore>");
        ChatUtil.sendMessage((CommandSender)player, " &7\u25b6 &eremove <number|all>");
        ChatUtil.sendMessage((CommandSender)player, " &7\u25b6 &elist");
        ChatUtil.sendMessage((CommandSender)player, "");
        ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
        player.closeInventory();
    }

    public CategoryDescriptionButton(Category category, PandaGKit plugin) {
        this.category = category;
        this.plugin = plugin;
    }
}

