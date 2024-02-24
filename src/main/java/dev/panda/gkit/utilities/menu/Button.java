/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.utilities.menu;

import dev.panda.gkit.utilities.compatibility.sound.CompatibleSound;
import dev.panda.gkit.utilities.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class Button {
    public static Button placeholder(final Material material, final int data, final String title) {
        return new Button(){

            @Override
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(material).setData(data).setName(title).build();
            }
        };
    }

    public static void playFail(Player player) {
        CompatibleSound.DIG_GRASS.play(player, 20.0f, 0.1f);
    }

    public static void playSuccess(Player player) {
        CompatibleSound.NOTE_PIANO.play(player, 20.0f, 15.0f);
    }

    public static void playNeutral(Player player) {
        CompatibleSound.CLICK.play(player, 20.0f, 1.0f);
    }

    public abstract ItemStack getButtonItem(Player var1);

    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
    }

    public boolean shouldUpdate(Player player, int slot, ClickType clickType) {
        return false;
    }

    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return true;
    }

    public boolean shouldShift(Player player, int slot, ClickType clickType) {
        return true;
    }
}

