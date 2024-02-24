/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.utilities.menu.pagination;

import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PageButton
extends Button {
    private final int mod;
    private final PaginatedMenu menu;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(CompatibleMaterial.CARPET.getMaterial());
        if (this.mod > 0) {
            itemBuilder.setData(13);
        } else {
            itemBuilder.setData(14);
        }
        if (this.hasNext(player)) {
            itemBuilder.setName(this.mod > 0 ? "&aNext page" : "&cPrevious page");
        } else {
            itemBuilder.setName("&7" + (this.mod > 0 ? "Last page" : "First page"));
        }
        return itemBuilder.build();
    }

    @Override
    public void clicked(Player player, int i2, ClickType clickType, int hb) {
        if (this.hasNext(player)) {
            this.menu.modPage(player, this.mod);
            Button.playNeutral(player);
        } else {
            Button.playFail(player);
        }
    }

    private boolean hasNext(Player player) {
        int pg = this.menu.getPage() + this.mod;
        return pg > 0 && this.menu.getPages(player) >= pg;
    }

    public PageButton(int mod, PaginatedMenu menu) {
        this.mod = mod;
        this.menu = menu;
    }
}

