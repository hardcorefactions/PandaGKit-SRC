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

import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitLootButton
extends Button {
    private final Kit kit;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.CHEST).setName("&d\u2735 &5Change Loot &d\u2735").setLore("&8&m-----------------------------", "&7Change the loot of kit.", "", "&eClick to save loot.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        int i2;
        ItemStack[] armor = new ItemStack[4];
        ItemStack[] contents = new ItemStack[36];
        for (i2 = 0; i2 < 4; ++i2) {
            if (player.getInventory().getArmorContents()[i2] == null) continue;
            armor[i2] = player.getInventory().getArmorContents()[i2].clone();
        }
        for (i2 = 0; i2 < 36; ++i2) {
            if (player.getInventory().getContents()[i2] == null) continue;
            contents[i2] = player.getInventory().getContents()[i2].clone();
        }
        this.kit.setArmor(armor);
        this.kit.setContents(contents);
        this.kit.save();
        ChatUtil.sendMessage((CommandSender)player, "&eKit Loot has been updated.");
        KitLootButton.playSuccess(player);
    }

    public KitLootButton(Kit kit) {
        this.kit = kit;
    }
}

