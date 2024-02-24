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
import dev.panda.gkit.commons.category.menu.CategoriesMenu;
import dev.panda.gkit.commons.category.menu.CategoryEditMenu;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.impl.PromptMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CategoryEditButton
extends Button {
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.category.getMaterial()).setData(this.category.getData()).setName("&b" + this.category.getName() + " Category").setLore("&7&m------------------", "&fLeft-Click to edit this category", "&fRight-Click to remove this category", "&7&m------------------").setEnchant(this.category.isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL).setOwner(this.category.getSkullOwner()).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        CategoryEditButton.playNeutral(player);
        if (clickType.isRightClick()) {
            new PromptMenu(plr -> {
                this.plugin.getModuleService().getManagerModule().getCategoryManager().delete(this.category.getName());
                ChatUtil.sendMessage((CommandSender)plr, "&aCategory '&f" + this.category.getName() + "&a' has been delete.");
            }, new CategoriesMenu(this.plugin), new ItemBuilder(CompatibleMaterial.WOOL.getMaterial()).setData(13).setName("&a&lRemove " + this.category.getName() + " Category").setLore("&7&m------------------", "&eClick to remove this category", "&7&m------------------").build(), new ItemBuilder(CompatibleMaterial.WOOL.getMaterial()).setData(14).setName("&c&lCancel").setLore("&7&m------------------", "&eClick to cancel this process", "&7&m------------------").build(), "Confirm Remove Category " + this.category.getName()).openMenu(player);
        } else {
            new CategoryEditMenu(this.category, this.plugin).openMenu(player);
        }
    }

    public CategoryEditButton(Category category, PandaGKit plugin) {
        this.category = category;
        this.plugin = plugin;
    }
}

