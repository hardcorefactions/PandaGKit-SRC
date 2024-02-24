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

public class KitPlaytimeButton
extends Button {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(CompatibleMaterial.SEEDS.getMaterial()).setName("&d\u2735 &5Playtime &d\u2735").setLore("&8&m-----------------------------", "&7Toggle or change the playtime of kit.", "", "&7Status: " + (this.kit.isPlayTime() ? "&aEnabled" : "&cDisabled"), "&7Playtime: &f" + JavaUtil.formatDurationInt(this.kit.getPlayTimeTime()), "", "&eRight-Click to toggle the status.", "&eLeft-Click to edit playtime.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        KitPlaytimeButton.playSuccess(player);
        if (clickType.isLeftClick()) {
            this.plugin.getModuleService().getManagerModule().getKitManager().getKitEdit().put("kitPlaytime", player.getUniqueId(), this.kit);
            ChatUtil.sendMessage((CommandSender)player, "&eYou're now editing playtime of '&f" + this.kit.getName() + "&e'.");
            ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
            ChatUtil.sendMessage((CommandSender)player, "");
            ChatUtil.sendMessage((CommandSender)player, "&eExample: &71d 30m 15s");
            player.closeInventory();
        } else {
            this.kit.setPlayTime(!this.kit.isPlayTime());
            this.kit.save();
        }
    }

    public KitPlaytimeButton(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

