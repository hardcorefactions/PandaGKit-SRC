/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.utilities.compatibility.sound;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public final class SoundUtil {
    public static void play(Player player, String sound) {
        if (!sound.isEmpty()) {
            player.playSound(player.getLocation(), Sound.valueOf((String)sound), 1.0f, 1.0f);
        }
    }

    private SoundUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

