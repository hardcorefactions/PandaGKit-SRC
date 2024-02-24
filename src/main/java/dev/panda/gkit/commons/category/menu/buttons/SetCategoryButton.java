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
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.JavaUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import java.util.Arrays;
import java.util.Iterator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SetCategoryButton
extends Button {
    private final Kit kit;
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.category.getMaterial(), this.category.getData()).setName(this.category.getDisplayName()).setEnchant(this.category.isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL).setOwner(this.category.getSkullOwner()).setLore(Arrays.asList("&8&m-----------------------------", "&7Click to set the category from", "&7the Kit " + this.kit.getName() + " to " + this.category.getName(), "&8&m------------------------------")).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        SetCategoryButton.playSuccess(player);
        this.kit.setCategory(this.category);
        if (this.hasAnyKitAtSameSlot() || this.kit.getSlot() >= this.category.getRows() * 9) {
            this.kit.setSlot(JavaUtil.randomExcluding(0, this.category.getRows() * 9, this.kit.getSlot()));
        }
        this.kit.save();
        ChatUtil.sendMessage((CommandSender)player, "&aYou successfully set the category from the Kit " + this.kit.getName() + " to " + this.category.getName() + "!");
    }

    private boolean hasAnyKitAtSameSlot() {
        Kit kitL;
        Iterator<Kit> var1 = this.plugin.getModuleService().getManagerModule().getKitManager().getKits().values().iterator();
        do {
            if (var1.hasNext()) continue;
            return false;
        } while ((kitL = var1.next()).getSlot() != this.kit.getSlot() || !kitL.getCategory().equals(this.category));
        return true;
    }

    public SetCategoryButton(Kit kit, Category category, PandaGKit plugin) {
        this.kit = kit;
        this.category = category;
        this.plugin = plugin;
    }
}

