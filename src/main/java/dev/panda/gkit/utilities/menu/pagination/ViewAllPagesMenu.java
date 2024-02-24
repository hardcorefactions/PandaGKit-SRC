/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  lombok.NonNull
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.utilities.menu.pagination;

import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import dev.panda.gkit.utilities.menu.buttons.BackButton;
import dev.panda.gkit.utilities.menu.pagination.JumpToPageButton;
import dev.panda.gkit.utilities.menu.pagination.PaginatedMenu;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import org.bukkit.entity.Player;

public class ViewAllPagesMenu
extends Menu {
    @NonNull
    PaginatedMenu menu;

    @Override
    public String getTitle(Player player) {
        return "Jump to page";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<Integer, Button>();
        buttons.put(0, new BackButton(this.menu));
        int index = 10;
        for (int i2 = 1; i2 <= this.menu.getPages(player); ++i2) {
            buttons.put(index++, new JumpToPageButton(i2, this.menu, this.menu.getPage() == i2));
            if ((index - 8) % 9 != 0) continue;
            index += 2;
        }
        return buttons;
    }

    @NonNull
    public PaginatedMenu getMenu() {
        return this.menu;
    }
}

