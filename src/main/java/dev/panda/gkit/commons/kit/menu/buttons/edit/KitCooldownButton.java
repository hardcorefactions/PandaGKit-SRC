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
import dev.panda.gkit.utilities.JavaUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitCooldownButton
extends Button {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(CompatibleMaterial.WATCH.getMaterial()).setName("&d\u2735 &5Change Cooldown &d\u2735").setLore("&8&m-----------------------------", "&7Change the cooldown of kit.", "", "&7Cooldown: &f" + JavaUtil.formatDurationInt(this.kit.getCooldown()), "", "&eClick to edit cooldown.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        this.plugin.getModuleService().getManagerModule().getKitManager().getKitEdit().put("kitCooldown", player.getUniqueId(), this.kit);
        KitCooldownButton.playSuccess(player);
        ChatUtil.sendMessage((CommandSender)player, "&eYou're now editing cooldown of '&f" + this.kit.getName() + "&e'.");
        ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
        ChatUtil.sendMessage((CommandSender)player, "");
        ChatUtil.sendMessage((CommandSender)player, "&eExample: &71d 30m 15s");
        player.closeInventory();
    }

    public KitCooldownButton(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

