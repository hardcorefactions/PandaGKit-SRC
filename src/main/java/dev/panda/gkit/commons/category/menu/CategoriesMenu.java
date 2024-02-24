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
import dev.panda.gkit.commons.category.menu.buttons.CategoryCreateButton;
import dev.panda.gkit.commons.category.menu.buttons.CategoryEditButton;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.pagination.PageButton;
import dev.panda.gkit.utilities.menu.pagination.PaginatedMenu;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.entity.Player;

public class CategoriesMenu
extends PaginatedMenu {
    private final PandaGKit plugin;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return "Categories";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 45;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        AtomicInteger count = new AtomicInteger();
        for (Category category : this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategories().values()) {
            buttons.put(count.getAndIncrement(), new CategoryEditButton(category, this.plugin));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(this.getSize() - 9, new PageButton(-1, this));
        buttons.put(this.getSize() - 5, new CategoryCreateButton(this.plugin));
        buttons.put(this.getSize() - 1, new PageButton(1, this));
        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    public CategoriesMenu(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

