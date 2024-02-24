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

public class CategoryMenu
extends Menu {
    private final Category category;
    private final PandaGKit plugin;

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(this.category.getName() + " Category");
    }

    @Override
    public int getSize() {
        return this.category.getRows() * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        for (Kit kit : this.plugin.getModuleService().getManagerModule().getKitManager().getKits().values()) {
            if (!kit.isShowMenu() || !kit.getCategory().equals(this.category)) continue;
            buttons.put(kit.getSlot(), new KitButton(kit, this.plugin));
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

    public CategoryMenu(Category category, PandaGKit plugin) {
        this.category = category;
        this.plugin = plugin;
    }
}

