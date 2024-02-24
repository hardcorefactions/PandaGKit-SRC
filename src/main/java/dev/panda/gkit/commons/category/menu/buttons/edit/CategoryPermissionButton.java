/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.category.menu.buttons.edit;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CategoryPermissionButton
extends Button {
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(CompatibleMaterial.SIGN.getMaterial()).setName("&3Change Permission").setLore("&8&m-----------------------------", "&fChange the permission of kit.", "", "&7Status: " + (this.category.isShouldPermission() ? "&aEnabled" : "&cDisabled"), "&7Permission: &f" + this.category.getPermission(), "", "&fLeft-Click to change the permission", "&fRight-Click to toggle the permission", "&8&m------------------------------").build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType.isLeftClick()) {
            this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategoryEdit().put("categoryPermission", player.getUniqueId(), this.category);
            CategoryPermissionButton.playSuccess(player);
            ChatUtil.sendMessage((CommandSender)player, "&eYou're now editing permission of '&f" + this.category.getName() + "&e'.");
            ChatUtil.sendMessage((CommandSender)player, "&eType '&ccancel&e' in the chat to cancel the process.");
            player.closeInventory();
        } else {
            CategoryPermissionButton.playNeutral(player);
            this.category.setShouldPermission(!this.category.isShouldPermission());
            this.category.save();
        }
    }

    public CategoryPermissionButton(Category category, PandaGKit plugin) {
        this.category = category;
        this.plugin = plugin;
    }
}

