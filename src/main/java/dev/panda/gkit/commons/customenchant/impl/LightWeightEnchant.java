/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffectType
 */
package dev.panda.gkit.commons.customenchant.impl;

import dev.panda.gkit.commons.customenchant.CustomEnchantProperties;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.utilities.JavaUtil;
import dev.panda.gkit.utilities.PotionRestorer;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class LightWeightEnchant
extends ICustomEnchant {
    public LightWeightEnchant() {
        super("light-weight", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&eLight Weight I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment has a chance of 5% to give", "&7you haste.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).setLevel(1).addCustomProperty("chance", 5.0).addCustomProperty("duration", 4).build(), new PotionEffectType[0]);
    }

    @Override
    public double onHit(Player player, Player damager, double damage) {
        double chance = (Double)this.getProperties().getCustomProperty("chance");
        if (!JavaUtil.getChance(chance)) {
            return damage;
        }
        PotionRestorer.builder().effect(PotionEffectType.FAST_DIGGING, (Integer)this.getProperties().getCustomProperty("duration"), this.getProperties().getLevel()).player(damager).build().run();
        return damage;
    }
}

