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

public class LifeStealEnchant
extends ICustomEnchant {
    public LifeStealEnchant() {
        super("lifesteal", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&6Life Steal I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment has a chance of 2% to steal", "&7health from your enemy.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).addCustomProperty("chance", 2.0).addCustomProperty("hearts", 2).build(), new PotionEffectType[0]);
    }

    @Override
    public double onHit(Player player, Player damager, double damage) {
        double chance = (Double)this.getProperties().getCustomProperty("chance");
        if (!JavaUtil.getChance(chance)) {
            return damage;
        }
        double newHealthPlayer = player.getHealth() - (double)((Integer)this.getProperties().getCustomProperty("hearts") * 2);
        double newHealthDamager = damager.getHealth() + (double)((Integer)this.getProperties().getCustomProperty("hearts") * 2);
        damager.setHealth(Math.min(newHealthDamager, damager.getMaxHealth()));
        player.setHealth(newHealthPlayer < 0.0 ? 0.0 : newHealthPlayer);
        return damage;
    }
}

