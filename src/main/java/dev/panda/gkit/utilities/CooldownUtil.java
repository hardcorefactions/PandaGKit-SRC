/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.utilities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.UUID;
import org.bukkit.entity.Player;

public class CooldownUtil {
    private static final Table<String, UUID, Long> cooldown = HashBasedTable.create();

    public static boolean hasCooldown(String kit, Player player) {
        return cooldown.contains(kit, player.getUniqueId()) && cooldown.get(kit, player.getUniqueId()) > System.currentTimeMillis();
    }

    public static void setCooldown(String kit, Player player, int time) {
        cooldown.put(kit, player.getUniqueId(), System.currentTimeMillis() + (long)time * 1000L);
    }

    public static long getCooldown(String kit, Player player) {
        return cooldown.get(kit, player.getUniqueId()) - System.currentTimeMillis();
    }

    public static void removeCooldown(String kit, Player player) {
        cooldown.remove(kit, player.getUniqueId());
    }

    public static Table<String, UUID, Long> getCooldown() {
        return cooldown;
    }
}

