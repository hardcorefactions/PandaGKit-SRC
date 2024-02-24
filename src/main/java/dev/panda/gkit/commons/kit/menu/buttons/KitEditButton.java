/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit.menu.buttons;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.menu.KitEditMenu;
import dev.panda.gkit.commons.kit.menu.KitsMenu;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import dev.panda.gkit.utilities.menu.Button;
import dev.panda.gkit.utilities.menu.impl.PromptMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class KitEditButton
extends Button {
    private final Kit kit;
    private final PandaGKit plugin;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(this.kit.getMaterial()).setData(this.kit.getData()).setName("&d" + this.kit.getName() + " Kit").setLore("&7&m------------------", "&eLeft-Click to edit this kit", "&eRight-Click to delete this kit", "&7&m------------------").setEnchant(this.kit.isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL).setOwner(this.kit.getSkullOwner()).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        KitEditButton.playNeutral(player);
        if (clickType.isRightClick()) {
            new PromptMenu(plr -> {
                this.plugin.getModuleService().getManagerModule().getKitManager().delete(this.kit.getName());
                ChatUtil.sendMessage((CommandSender)plr, "&aKit '&f" + this.kit.getName() + "&a' has been delete.");
            }, new KitsMenu(this.plugin), new ItemBuilder(CompatibleMaterial.WOOL.getMaterial()).setData(13).setName("&a&lRemove " + this.kit.getName() + " Kit").setLore("&7&m------------------", "&eClick to remove this kit", "&7&m------------------").build(), new ItemBuilder(CompatibleMaterial.WOOL.getMaterial()).setData(14).setName("&c&lCancel").setLore("&7&m------------------", "&eClick to cancel this process", "&7&m------------------").build(), "Confirm Remove Kit " + this.kit.getName()).openMenu(player);
        } else {
            new KitEditMenu(this.kit, this.plugin).openMenu(player);
        }
    }

    public KitEditButton(Kit kit, PandaGKit plugin) {
        this.kit = kit;
        this.plugin = plugin;
    }
}

