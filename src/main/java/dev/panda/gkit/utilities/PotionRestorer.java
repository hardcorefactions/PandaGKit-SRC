/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package dev.panda.gkit.utilities;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import dev.panda.gkit.utilities.PotionUtil;
import dev.panda.gkit.utilities.TaskUtil;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionRestorer {
    private Player player;
    private Table<PotionEffectType, Integer, Integer> effects;

    public void run() {
        if (this.player != null && this.player.isOnline() && !this.effects.isEmpty()) {
            for (Table.Cell<PotionEffectType, Integer, Integer> effect : this.effects.cellSet()) {
                PotionEffectType effectType = effect.getRowKey();
                int seconds = effect.getColumnKey();
                int amplifier = effect.getValue();
                if (this.player.hasPotionEffect(effectType)) {
                    PotionEffect potionEffect = PotionUtil.getPotion(this.player, effectType);
                    this.player.removePotionEffect(effectType);
                    TaskUtil.runLater(() -> this.player.addPotionEffect(potionEffect), (long)seconds * 20L + 5L);
                }
                PotionUtil.addPotion(this.player, effectType, seconds, amplifier);
            }
        }
    }

    public static PotionRestorerBuilder builder() {
        return new PotionRestorerBuilder();
    }

    public PotionRestorer(Player player, Table<PotionEffectType, Integer, Integer> effects) {
        this.player = player;
        this.effects = effects;
    }

    public static class PotionRestorerBuilder {
        private Player player;
        private final Table<PotionEffectType, Integer, Integer> effects = HashBasedTable.create();

        public PotionRestorerBuilder player(Player player) {
            this.player = player;
            return this;
        }

        public PotionRestorerBuilder effect(PotionEffectType effectType, int duration, int amplifier) {
            this.effects.put(effectType, duration, amplifier);
            return this;
        }

        public PotionRestorerBuilder effect(PotionEffectType effectType, int duration) {
            this.effects.put(effectType, duration, 1);
            return this;
        }

        public PotionRestorerBuilder effect(PotionEffectType effectType) {
            this.effects.put(effectType, 5, 1);
            return this;
        }

        public PotionRestorer build() {
            return new PotionRestorer(this.player, this.effects);
        }
    }
}

