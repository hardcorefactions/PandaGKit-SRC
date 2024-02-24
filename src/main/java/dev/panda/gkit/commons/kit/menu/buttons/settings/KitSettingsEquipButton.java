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
import dev.panda.gkit.utilities.file.FileConfig;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitSettingsEquipButton
extends Button {
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.NAME_TAG).setName("&d\u2735 &5Clear Inventory &d\u2735").setLore("&8&m-----------------------------", "&7Status: &f" + (ConfigService.CLEAR_INVENTORY ? "&aEnabled" : "&cDisabled"), "", "&eClick to toggle the clear inventory.", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        FileConfig config = this.plugin.getModuleService().getFileModule().getFile("config");
        config.getConfiguration().set("CLEAR_INVENTORY", (Object)(!config.getBoolean("CLEAR_INVENTORY") ? 1 : 0));
        config.save();
        this.plugin.getModuleService().getServiceModule().load(true);
        KitSettingsEquipButton.playSuccess(player);
    }

    public KitSettingsEquipButton(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

