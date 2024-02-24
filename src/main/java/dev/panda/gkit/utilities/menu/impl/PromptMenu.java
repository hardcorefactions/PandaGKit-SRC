/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.utilities.menu.impl;

import com.google.common.collect.Maps;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.Menu;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PromptMenu
extends Menu {
    private final PromptAction callback;
    private final Menu backMenu;
    private final ItemStack confirmButton;
    private final ItemStack cancelButton;
    private final String title;

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(this.title);
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(11, new ConfirmButton(this.callback, this.confirmButton, this.backMenu));
        buttons.put(15, new CancelButton(this.cancelButton, this.backMenu));
        return buttons;
    }

    public PromptMenu(PromptAction callback, Menu backMenu, ItemStack confirmButton, ItemStack cancelButton, String title) {
        this.callback = callback;
        this.backMenu = backMenu;
        this.confirmButton = confirmButton;
        this.cancelButton = cancelButton;
        this.title = title;
    }

    public static interface PromptAction {
        public void run(Player var1);
    }

    private static class ConfirmButton
    extends Button {
        private final PromptAction action;
        private final ItemStack item;
        private final Menu back;

        @Override
        public ItemStack getButtonItem(Player player) {
            return this.item;
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            ConfirmButton.playSuccess(player);
            this.action.run(player);
            this.back.openMenu(player);
        }

        public ConfirmButton(PromptAction action, ItemStack item, Menu back) {
            this.action = action;
            this.item = item;
            this.back = back;
        }
    }

    private static class CancelButton
    extends Button {
        private final ItemStack item;
        private final Menu back;

        @Override
        public ItemStack getButtonItem(Player player) {
            return this.item;
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            CancelButton.playNeutral(player);
            this.back.openMenu(player);
        }

        public CancelButton(ItemStack item, Menu back) {
            this.item = item;
            this.back = back;
        }
    }
}

