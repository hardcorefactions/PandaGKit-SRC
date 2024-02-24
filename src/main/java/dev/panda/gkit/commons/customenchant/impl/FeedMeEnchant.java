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
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class FeedMeEnchant
extends ICustomEnchant {
    public FeedMeEnchant() {
        super("feed-me", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&aFeed Me I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment has a chance of 50% to feed", "&7yourself.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).addCustomProperty("chance", 50.0).addCustomProperty("hunger", 2).build(), new PotionEffectType[0]);
    }

    @Override
    public double onHit(Player player, Player damager, double damage) {
        double chance = (Double)this.getProperties().getCustomProperty("chance");
        if (!JavaUtil.getChance(chance)) {
            return damage;
        }
        int newHunger = player.getFoodLevel() + (Integer)this.getProperties().getCustomProperty("hunger");
        damager.setFoodLevel(Math.min(newHunger, 20));
        return damage;
    }
}

