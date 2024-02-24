/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commons.kit.menu;

import com.google.common.collect.Maps;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.menu.buttons.KitCreateButton;
import dev.panda.gkit.commons.kit.menu.buttons.KitEditButton;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.pagination.PageButton;
import dev.panda.gkit.utilities.menu.pagination.PaginatedMenu;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.entity.Player;

public class KitsMenu
extends PaginatedMenu {
    private final PandaGKit plugin;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return "Kits";
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
        for (Kit kit : this.plugin.getModuleService().getManagerModule().getKitManager().getKits().values()) {
            buttons.put(count.getAndIncrement(), new KitEditButton(kit, this.plugin));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(this.getSize() - 9, new PageButton(-1, this));
        buttons.put(this.getSize() - 5, new KitCreateButton(this.plugin));
        buttons.put(this.getSize() - 1, new PageButton(1, this));
        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    public KitsMenu(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

