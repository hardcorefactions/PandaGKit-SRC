/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package dev.panda.gkit.utilities;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class PotionUtil {
    public static void addPotion(Player player, PotionEffectType potionEffectType) {
        PotionUtil.addPotion(player, potionEffectType, 1);
    }

    public static void addPotion(Player player, PotionEffectType potionEffectType, int duration) {
        PotionUtil.addPotion(player, potionEffectType, duration, 1);
    }

    public static void addPotion(Player player, PotionEffectType potionEffectType, int duration, int level) {
        PotionUtil.addPotion(player, potionEffectType, duration, level, false);
    }

    public static void addPotion(Player player, PotionEffectType potionEffectType, int duration, int level, boolean remove) {
        PotionUtil.addPotion(player, potionEffectType, duration, level, remove, false);
    }

    public static void addPotion(Player player, PotionEffectType potionEffectType, int duration, int level, boolean remove, boolean checkLevel) {
        boolean canRemove = false;
        if (checkLevel && PotionUtil.getPotion(player, potionEffectType) != null) {
            PotionEffect potionEffect = PotionUtil.getPotion(player, potionEffectType);
            int potionAmplifier = potionEffect.getAmplifier() + 1;
            if (level < potionAmplifier) {
                return;
            }
            canRemove = true;
        }
        if (remove || canRemove) {
            player.removePotionEffect(potionEffectType);
        }
        player.addPotionEffect(new PotionEffect(potionEffectType, 20 * (duration + 1), level - 1));
    }

    public static PotionEffect getPotion(Player player, PotionEffectType potionEffectType) {
        PotionEffect potionEffect;
        Iterator var2 = player.getActivePotionEffects().iterator();
        do {
            if (var2.hasNext()) continue;
            return null;
        } while (!(potionEffect = (PotionEffect)var2.next()).getType().equals((Object)potionEffectType));
        return potionEffect;
    }

    public static List<PotionEffectType> getPotionsExcluding(Player player, PotionEffectType ... potions) {
        ArrayList<PotionEffectType> toReturn = new ArrayList<PotionEffectType>(PotionUtil.getPotionTypes(player));
        toReturn.removeAll(Arrays.asList(potions));
        return toReturn;
    }

    public static List<PotionEffectType> getPotionTypes(Player player) {
        ArrayList<PotionEffectType> toReturn = Lists.newArrayList();
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            toReturn.add(potionEffect.getType());
        }
        return toReturn;
    }

    public static boolean isLeveleable(PotionEffectType effect) {
        return effect != PotionEffectType.INVISIBILITY && effect != PotionEffectType.CONFUSION && effect != PotionEffectType.FIRE_RESISTANCE && effect != PotionEffectType.WATER_BREATHING && effect != PotionEffectType.BLINDNESS && effect != PotionEffectType.NIGHT_VISION;
    }

    private PotionUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

