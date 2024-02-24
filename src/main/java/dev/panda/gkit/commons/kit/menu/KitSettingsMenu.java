/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commons.kit.menu;

import com.google.common.collect.Maps;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.menu.buttons.settings.KitSettingsEquipButton;
import dev.panda.gkit.commons.kit.menu.buttons.settings.KitSettingsFillButton;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class KitSettingsMenu
extends Menu {
    private final PandaGKit plugin;

    @Override
    public String getTitle(Player player) {
        return "Kit Settings";
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(12, new KitSettingsEquipButton(this.plugin));
        buttons.put(14, new KitSettingsFillButton(this.plugin));
        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    @Override
    public boolean isCancelPlayerInventory() {
        return false;
    }

    public KitSettingsMenu(PandaGKit plugin) {
        this.plugin = plugin;
    }
}

