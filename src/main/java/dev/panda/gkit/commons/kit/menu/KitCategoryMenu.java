/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commons.kit.menu;

import com.google.common.collect.Maps;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.commons.category.menu.buttons.ClearCategoryButton;
import dev.panda.gkit.commons.category.menu.buttons.SetCategoryButton;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.menu.KitEditMenu;
import dev.panda.gkit.commons.kit.menu.buttons.FillButton;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.buttons.BackButton;
import dev.panda.gkit.utilities.menu.pagination.PageButton;
import dev.panda.gkit.utilities.menu.pagination.PaginatedMenu;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.entity.Player;

public class KitCategoryMenu
extends PaginatedMenu {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return ChatUtil.translate("Edit " + this.kit.getName() + " Category");
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 45;
    }

    @Override
    public int getSize() {
        return 45;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        AtomicInteger slot = new AtomicInteger();
        for (Category category : this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategories().values()) {
            buttons.put(slot.getAndIncrement(), new SetCategoryButton(this.kit, category, this.plugin));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(this.getSize() - 9, new PageButton(-1, this));
        buttons.put(this.getSize() - 1, new PageButton(1, this));
        buttons.put(this.getSize() - 6, new ClearCategoryButton(this.kit, this.plugin));
        buttons.put(this.getSize() - 4, new BackButton(new KitEditMenu(this.kit, this.plugin)));
        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return false;
    }

    @Override
    public boolean isPlaceholder() {
        return ConfigService.MENU_FILL_ENABLE;
    }

    @Override
    public Button getPlaceholderButton() {
        return new FillButton();
    }

    public KitCategoryMenu(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

