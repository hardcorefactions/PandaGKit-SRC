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
package dev.panda.gkit.commons.kit.menu.buttons;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitCreateButton
extends Button {
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.NETHER_STAR).setName("&d\u2735 &5Create Kit &d\u2735").setLore("&8&m-----------------------------", "&7Click to create a new kit.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        this.plugin.getModuleService().getManagerModule().getKitManager().getKitCreate().put(player.getUniqueId(), "kitCreate");
        KitCreateButton.playSuccess(player);
        ChatUtil.sendMessage((CommandSender)player, "&ePlease type the new kit's name.");
        ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
        player.closeInventory();
    }

    public KitCreateButton(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

