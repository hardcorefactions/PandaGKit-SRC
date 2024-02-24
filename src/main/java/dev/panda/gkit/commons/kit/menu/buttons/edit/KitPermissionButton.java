/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu.buttons.edit;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitPermissionButton
extends Button {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(CompatibleMaterial.SIGN.getMaterial()).setName("&d\u2735 &5Change Permission &d\u2735").setLore("&8&m-----------------------------", "&7Change the permission of kit.", "", "&7Permission: &f" + this.kit.getPermission(), "", "&eClick to edit permission.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        this.plugin.getModuleService().getManagerModule().getKitManager().getKitEdit().put("kitPermission", player.getUniqueId(), this.kit);
        KitPermissionButton.playSuccess(player);
        ChatUtil.sendMessage((CommandSender)player, "&eYou're now editing permission of '&f" + this.kit.getName() + "&e'.");
        ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
        player.closeInventory();
    }

    public KitPermissionButton(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

