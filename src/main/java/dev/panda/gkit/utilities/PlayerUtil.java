/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Statistic
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.utilities;

import dev.panda.gkit.utilities.BukkitUtil;
import dev.panda.gkit.utilities.compatibility.sound.CompatibleSound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class PlayerUtil {
    public static boolean isInventoryFull(Player player) {
        return player.getInventory().firstEmpty() < 0;
    }

    public static void decrement(Player player, ItemStack itemStack, boolean sound, boolean cursor) {
        if (sound) {
            CompatibleSound.ANVIL_BREAK.play(player);
        }
        if (itemStack.getAmount() <= 1) {
            if (cursor) {
                player.setItemOnCursor((ItemStack)null);
            } else {
                player.setItemInHand((ItemStack)null);
            }
        } else {
            itemStack.setAmount(itemStack.getAmount() - 1);
        }
        player.updateInventory();
    }

    public static int getTicksLived(Player player) {
        return player.getStatistic(Statistic.valueOf((String)(BukkitUtil.SERVER_VERSION_INT < 13 ? "PLAY_ONE_TICK" : "PLAY_ONE_MINUTE"))) / 20;
    }

    private PlayerUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

