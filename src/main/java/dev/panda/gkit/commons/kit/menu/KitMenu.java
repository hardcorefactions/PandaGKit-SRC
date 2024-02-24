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
import dev.panda.gkit.commons.category.menu.buttons.CategoryButton;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.menu.buttons.FillButton;
import dev.panda.gkit.commons.kit.menu.buttons.KitButton;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class KitMenu
extends Menu {
    private final PandaGKit plugin;

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(ConfigService.MENU_TITLE);
    }

    @Override
    public int getSize() {
        return ConfigService.MENU_ROWS * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        for (Kit kit : this.plugin.getModuleService().getManagerModule().getKitManager().getKits().values()) {
            if (!kit.isShowMenu() || kit.getCategory() != null) continue;
            buttons.put(kit.getSlot(), new KitButton(kit, this.plugin));
        }
        for (Category category : this.plugin.getModuleService().getManagerModule().getCategoryManager().getCategories().values()) {
            if (!category.isShowMenu()) continue;
            buttons.put(category.getSlot(), new CategoryButton(category, this.plugin));
        }
        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    @Override
    public boolean isPlaceholder() {
        return ConfigService.MENU_FILL_ENABLE;
    }

    @Override
    public Button getPlaceholderButton() {
        return new FillButton();
    }

    public KitMenu(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

