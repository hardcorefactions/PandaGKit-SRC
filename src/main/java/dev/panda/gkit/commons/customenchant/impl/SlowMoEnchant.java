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

public class SlowMoEnchant
extends ICustomEnchant {
    public SlowMoEnchant() {
        super("slow-mo", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&8Slow Mo II").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment has a chance of 25% to give", "&7your enemy slowness.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).setLevel(2).addCustomProperty("chance", 25.0).addCustomProperty("duration", 5).build(), new PotionEffectType[0]);
    }

    @Override
    public double onHit(Player player, Player damager, double damage) {
        double chance = (Double)this.getProperties().getCustomProperty("chance");
        if (!JavaUtil.getChance(chance)) {
            return damage;
        }
        PotionRestorer.builder().effect(PotionEffectType.SLOW, (Integer)this.getProperties().getCustomProperty("duration"), this.getProperties().getLevel()).player(player).build().run();
        return damage;
    }
}

