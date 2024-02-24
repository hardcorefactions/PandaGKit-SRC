/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu.buttons.settings;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.file.FileConfig;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitSettingsFillButton
extends Button {
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.ITEM_FRAME).setName("&d\u2735 &5Fill Item &d\u2735").setLore("&8&m-----------------------------", "&7Item: &f" + ChatUtil.toReadable(ConfigService.MENU_FILL_MATERIAL), "&7Status: &f" + (ConfigService.MENU_FILL_ENABLE ? "&aEnabled" : "&cDisabled"), "", "&eClick to toggle the status.", "&eDrag an item to change the fill item.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        FileConfig config = this.plugin.getModuleService().getFileModule().getFile("config");
        if (player.getItemOnCursor() != null && !player.getItemOnCursor().getType().equals((Object)Material.AIR)) {
            if (!player.getItemOnCursor().isSimilar(new ItemBuilder(ConfigService.MENU_FILL_MATERIAL).setData(ConfigService.MENU_FILL_DATA).build())) {
                config.getConfiguration().set("MENU.FILL.MATERIAL", (Object)player.getItemOnCursor().getType().name());
                config.getConfiguration().set("MENU.FILL.DATA", (Object)player.getItemOnCursor().getDurability());
                config.save();
                this.plugin.getModuleService().getServiceModule().load(true);
                KitSettingsFillButton.playSuccess(player);
            }
        } else {
            config.getConfiguration().set("MENU.FILL.ENABLE", (Object)(!config.getBoolean("MENU.FILL.ENABLE") ? 1 : 0));
            config.save();
            this.plugin.getModuleService().getServiceModule().load(true);
            KitSettingsFillButton.playSuccess(player);
        }
    }

    public KitSettingsFillButton(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

