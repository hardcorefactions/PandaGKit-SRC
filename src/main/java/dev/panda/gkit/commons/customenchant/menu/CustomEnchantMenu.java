/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commons.customenchant.menu;

import com.google.common.collect.Maps;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.commons.customenchant.menu.buttons.CustomEnchantButton;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.pagination.PageButton;
import dev.panda.gkit.utilities.menu.pagination.PaginatedMenu;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.entity.Player;

public class CustomEnchantMenu
extends PaginatedMenu {
    private final PandaGKit plugin;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return "Custom Enchant";
    }

    @Override
    public int getSize() {
        return 45;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 36;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        AtomicInteger count = new AtomicInteger();
        for (ICustomEnchant customEnchant : this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getCustomEnchants()) {
            buttons.put(count.getAndIncrement(), new CustomEnchantButton(customEnchant));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(this.getSize() - 9, new PageButton(-1, this));
        buttons.put(this.getSize() - 1, new PageButton(1, this));
        return buttons;
    }

    public CustomEnchantMenu(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

