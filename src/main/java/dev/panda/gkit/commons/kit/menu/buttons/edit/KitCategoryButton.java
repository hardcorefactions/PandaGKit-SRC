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
import dev.panda.gkit.commons.kit.menu.KitCategoryMenu;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitCategoryButton
extends Button {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.BLAZE_POWDER).setName("&d\u2735 &5Change Category &d\u2735").setLore("&8&m-----------------------------", "&7Change the category of kit.", "", "&7Category: &f" + (this.kit.getCategory() != null && !this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategories().isEmpty() ? this.kit.getCategory().getDisplayName() : "None"), "", "&eClick to edit the category.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategories().isEmpty()) {
            KitCategoryButton.playFail(player);
            ChatUtil.sendMessage((CommandSender)player, "&cThere are no categories!");
        } else {
            KitCategoryButton.playNeutral(player);
            new KitCategoryMenu(this.kit, this.plugin).openMenu(player);
        }
    }

    public KitCategoryButton(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

