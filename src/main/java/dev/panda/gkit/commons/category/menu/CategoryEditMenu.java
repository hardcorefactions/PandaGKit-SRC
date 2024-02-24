/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commons.category.menu;

import com.google.common.collect.Maps;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.commons.category.menu.CategoriesMenu;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategoryDescriptionButton;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategoryDisplayButton;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategoryDisplayNameButton;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategoryGlowButton;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategoryIconButton;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategoryPermissionButton;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategoryShowMenuButton;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategorySlotButton;
import dev.panda.gkit.commons.category.menu.buttons.edit.CategoryStatusButton;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import dev.panda.gkit.utilities.menu.buttons.BackButton;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class CategoryEditMenu
extends Menu {
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public String getTitle(Player player) {
        return this.category.getName() + " Category Edit";
    }

    @Override
    public int getSize() {
        return 36;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(4, new CategoryDisplayButton(this.category));
        buttons.put(11, new CategoryStatusButton(this.category));
        buttons.put(12, new CategoryShowMenuButton(this.category));
        buttons.put(13, new CategoryDisplayNameButton(this.category, this.plugin));
        buttons.put(14, new CategoryIconButton(this.category));
        buttons.put(15, new CategoryGlowButton(this.category));
        buttons.put(21, new CategorySlotButton(this.category, this.plugin));
        buttons.put(22, new CategoryDescriptionButton(this.category, this.plugin));
        buttons.put(23, new CategoryPermissionButton(this.category, this.plugin));
        buttons.put(31, new BackButton(new CategoriesMenu(this.plugin)));
        return buttons;
    }

    @Override
    public boolean isCancelPlayerInventory() {
        return false;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    public CategoryEditMenu(Category category, PandaGKit plugin) {
        this.category = category;
        this.plugin = plugin;
    }
}

