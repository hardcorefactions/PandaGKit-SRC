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
package dev.panda.gkit.commons.kit.menu.buttons.edit;

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

public class KitDisplayNameButton
extends Button {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.NAME_TAG).setName("&d\u2735 &5Change DisplayName &d\u2735").setLore("&8&m-----------------------------", "&7Change the display name of kit.", "", "&7DisplayName: &f" + this.kit.getDisplayName(), "", "&eClick to edit the display name.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        this.plugin.getModuleService().getManagerModule().getKitManager().getKitEdit().put("kitDisplayName", player.getUniqueId(), this.kit);
        KitDisplayNameButton.playSuccess(player);
        ChatUtil.sendMessage((CommandSender)player, "&eYou're now editing name of '&f" + this.kit.getName() + "&e'.");
        ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
        player.closeInventory();
    }

    public KitDisplayNameButton(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

