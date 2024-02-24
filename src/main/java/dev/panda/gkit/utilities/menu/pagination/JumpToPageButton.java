/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.utilities.menu.pagination;

import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.pagination.PaginatedMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class JumpToPageButton
extends Button {
    private final int page;
    private final PaginatedMenu menu;
    private final boolean current;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(this.current ? Material.ENCHANTED_BOOK : Material.BOOK, this.page);
        itemBuilder.setName(ChatUtil.translate("&cPage " + this.page));
        if (this.current) {
            itemBuilder.setLore("", ChatUtil.translate("&aCurrent page"));
        }
        return itemBuilder.build();
    }

    @Override
    public void clicked(Player player, int i2, ClickType clickType, int hb) {
        this.menu.modPage(player, this.page - this.menu.getPage());
        Button.playNeutral(player);
    }

    public JumpToPageButton(int page, PaginatedMenu menu, boolean current) {
        this.page = page;
        this.menu = menu;
        this.current = current;
    }
}

