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
import dev.panda.gkit.commons.kit.menu.KitsMenu;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitCategoryButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitCooldownButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitDisplayButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitDisplayNameButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitGlowButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitIconButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitLootButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitPermissionButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitPlaytimeButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitShowMenuButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitSlotButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitStatusButton;
import dev.panda.gkit.commons.kit.menu.buttons.edit.KitUsesButton;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import dev.panda.gkit.utilities.menu.buttons.BackButton;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class KitEditMenu
extends Menu {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public String getTitle(Player player) {
        return this.kit.getName() + " Kit Edit";
    }

    @Override
    public int getSize() {
        return 45;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(4, new KitDisplayButton(this.kit));
        buttons.put(11, new KitStatusButton(this.kit));
        buttons.put(12, new KitShowMenuButton(this.kit));
        buttons.put(13, new KitDisplayNameButton(this.kit, this.plugin));
        buttons.put(14, new KitIconButton(this.kit));
        buttons.put(15, new KitGlowButton(this.kit));
        buttons.put(20, new KitSlotButton(this.kit, this.plugin));
        buttons.put(21, new KitPermissionButton(this.kit, this.plugin));
        buttons.put(22, new KitCooldownButton(this.kit, this.plugin));
        buttons.put(23, new KitLootButton(this.kit));
        buttons.put(24, new KitCategoryButton(this.kit, this.plugin));
        buttons.put(30, new KitPlaytimeButton(this.kit, this.plugin));
        buttons.put(32, new KitUsesButton(this.kit, this.plugin));
        buttons.put(40, new BackButton(new KitsMenu(this.plugin)));
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

    public KitEditMenu(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

