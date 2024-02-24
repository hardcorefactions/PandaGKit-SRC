/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu.buttons;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.menu.KitPreviewMenu;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.CooldownUtil;
import dev.panda.gkit.utilities.PlayerUtil;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitButton
extends Button {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(this.kit.getMaterial());
        itemBuilder.setData(this.kit.getData());
        itemBuilder.setName(this.kit.getDisplayName());
        itemBuilder.setEnchant(this.kit.isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL);
        itemBuilder.setOwner(this.kit.getSkullOwner());
        List<String> lore = !player.hasPermission(this.kit.getPermission()) ? this.kit.getNoPermission() : (!(CooldownUtil.hasCooldown(this.kit.getName(), player) || this.kit.isPlayTime() && PlayerUtil.getTicksLived(player) < this.kit.getPlayTimeTime() || this.kit.isUsesEnabled() && this.kit.getUses(player) >= this.kit.getUses()) ? this.kit.getAvailableStream(player) : this.kit.getNoAvailableStream(player));
        itemBuilder.setLore(lore);
        return itemBuilder.build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType.isRightClick()) {
            new KitPreviewMenu(this.kit, this.plugin).openMenu(player);
        } else {
            this.kit.give(player, false);
        }
    }

    public KitButton(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

