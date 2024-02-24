/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu;

import com.google.common.collect.Maps;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitSlotMenu
extends Menu {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public String getTitle(Player player) {
        return this.kit.getName() + " Kit Slot";
    }

    @Override
    public int getSize() {
        return (this.kit.getCategory() == null ? ConfigService.MENU_ROWS : this.kit.getCategory().getRows()) * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        for (int i2 = 0; i2 < this.getSize(); ++i2) {
            buttons.put(i2, new AvailableSlot(this.kit));
        }
        for (Kit otherKit : this.plugin.getModuleService().getManagerModule().getKitManager().getKits().values()) {
            buttons.put(otherKit.getSlot(), new NoAvailableSlotKit(otherKit));
        }
        if (this.kit.getCategory() == null) {
            for (Category category : this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategories().values()) {
                buttons.put(category.getSlot(), new NoAvailableSlotCategory(category));
            }
        }
        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    public KitSlotMenu(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }

    private static class AvailableSlot
    extends Button {
        private final Kit kit;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(CompatibleMaterial.STAINED_GLASS_PANE.getMaterial()).setData(5).setName("&aAvailable Slot").build();
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            this.kit.setSlot(slot);
            this.kit.save();
            AvailableSlot.playSuccess(player);
        }

        public AvailableSlot(Kit kit) {
            this.kit = kit;
        }
    }

    private static class NoAvailableSlotKit
    extends Button {
        private final Kit kit;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(CompatibleMaterial.STAINED_GLASS_PANE.getMaterial()).setData(14).setName("&c" + this.kit.getName() + " Kit").build();
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            ChatUtil.sendMessage((CommandSender)player, "&cThis slot is occupied by " + this.kit.getName() + " Kit");
            NoAvailableSlotKit.playFail(player);
        }

        public NoAvailableSlotKit(Kit kit) {
            this.kit = kit;
        }
    }

    private static class NoAvailableSlotCategory
    extends Button {
        private final Category category;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(CompatibleMaterial.STAINED_GLASS_PANE.getMaterial()).setData(14).setName("&c" + this.category.getName() + " Category").build();
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            ChatUtil.sendMessage((CommandSender)player, "&cThis slot is occupied by " + this.category.getName() + " Category");
            NoAvailableSlotCategory.playFail(player);
        }

        public NoAvailableSlotCategory(Category category) {
            this.category = category;
        }
    }
}

