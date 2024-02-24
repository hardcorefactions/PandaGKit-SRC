/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.potion.PotionEffectType
 */
package dev.panda.gkit.commons.customenchant.impl;

import dev.panda.gkit.commons.customenchant.CustomEnchantProperties;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

public class OverLoadEnchant
extends ICustomEnchant {
    public OverLoadEnchant() {
        super("over-load", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&eOver Load I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment gives you health boost", "&7for being able to survive.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).setLevel(1).build(), PotionEffectType.HEALTH_BOOST);
    }
}

