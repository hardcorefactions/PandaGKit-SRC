/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu;

import com.google.common.collect.Maps;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.menu.CategoryMenu;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.menu.KitMenu;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import dev.panda.gkit.utilities.menu.buttons.BackButton;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitPreviewMenu
extends Menu {
    private final PandaGKit plugin;
    private final Kit kit;
    private final ItemStack[] armor;
    private final ItemStack[] contents;

    public KitPreviewMenu(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.armor = (ItemStack[])kit.getArmor().clone();
        this.contents = (ItemStack[])kit.getContents().clone();
        this.plugin = plugin;
    }

    @Override
    public String getTitle(Player player) {
        return this.kit.getName() + " Kit Preview";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        for (int i2 = 0; i2 < this.contents.length; ++i2) {
            if (this.contents[i2] == null) continue;
            buttons.put(i2, new KitItemStack(this.contents[i2].clone()));
        }
        if (this.armor.length != 0) {
            ItemStack boots;
            ItemStack leggings;
            ItemStack chestplate;
            ItemStack helmet = this.armor[0];
            if (helmet != null) {
                buttons.put(48, new KitItemStack(helmet.clone()));
            }
            if ((chestplate = this.armor[1]) != null) {
                buttons.put(47, new KitItemStack(chestplate.clone()));
            }
            if ((leggings = this.armor[2]) != null) {
                buttons.put(46, new KitItemStack(leggings.clone()));
            }
            if ((boots = this.armor[3]) != null) {
                buttons.put(45, new KitItemStack(boots.clone()));
            }
        }
        buttons.put(this.getSize() - 1, new BackButton(this.kit.getCategory() == null ? new KitMenu(this.plugin) : new CategoryMenu(this.kit.getCategory(), this.plugin)));
        return buttons;
    }

    private static class KitItemStack
    extends Button {
        private final ItemStack itemStack;

        @Override
        public ItemStack getButtonItem(Player player) {
            return this.itemStack;
        }

        @Override
        public boolean shouldCancel(Player player, int slot, ClickType clickType) {
            return true;
        }

        @Override
        public boolean shouldShift(Player player, int slot, ClickType clickType) {
            return true;
        }

        public KitItemStack(ItemStack itemStack) {
            this.itemStack = itemStack;
        }
    }
}

