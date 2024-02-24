/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.category.menu.buttons;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.commons.category.menu.CategoryMenu;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.services.impl.LangService;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import java.util.Iterator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CategoryButton
extends Button {
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(this.category.getMaterial());
        itemBuilder.setData(this.category.getData());
        itemBuilder.setName(this.category.getDisplayName());
        itemBuilder.setEnchant(this.category.isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL);
        itemBuilder.setOwner(this.category.getSkullOwner());
        itemBuilder.setLore(this.category.getDescription());
        return itemBuilder.build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (!this.category.isEnable()) {
            CategoryButton.playFail(player);
            ChatUtil.sendMessage((CommandSender)player, LangService.CATEGORY_DISABLED);
        } else if (this.category.isShouldPermission() && !player.hasPermission(this.category.getPermission())) {
            CategoryButton.playFail(player);
            ChatUtil.sendMessage((CommandSender)player, LangService.CATEGORY_NO_PERMISSION);
        } else if (this.isEmpty()) {
            CategoryButton.playFail(player);
            ChatUtil.sendMessage((CommandSender)player, LangService.CATEGORY_EMPTY);
        } else {
            CategoryButton.playNeutral(player);
            new CategoryMenu(this.category, this.plugin).openMenu(player);
        }
    }

    private boolean isEmpty() {
        Kit kit;
        Iterator<Kit> var1 = this.plugin.getModuleService().getManagerModule().getKitManager().getKits().values().iterator();
        do {
            if (var1.hasNext()) continue;
            return true;
        } while ((kit = var1.next()).getCategory() == null || kit.getCategory() != this.category);
        return false;
    }

    public CategoryButton(Category category, PandaGKit plugin) {
        this.category = category;
        this.plugin = plugin;
    }
}

