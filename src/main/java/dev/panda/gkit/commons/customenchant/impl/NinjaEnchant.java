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

public class NinjaEnchant
extends ICustomEnchant {
    public NinjaEnchant() {
        super("ninja", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&4Ninja I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment has a chance of 0,5% to deal", "&7triple damage to your enemy.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).addCustomProperty("chance", 0.5).addCustomProperty("factor", 3.0).build(), new PotionEffectType[0]);
    }

    @Override
    public double onHit(Player player, Player damager, double damage) {
        double chance = (Double)this.getProperties().getCustomProperty("chance");
        return !JavaUtil.getChance(chance) ? damage : damage * (Double)this.getProperties().getCustomProperty("factor");
    }
}

