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

public class NutritionEnchant
extends ICustomEnchant {
    public NutritionEnchant() {
        super("nutrition", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&aNutrition I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment gives you nutrition", "&7so your food decreases slower.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).setLevel(1).build(), PotionEffectType.SATURATION);
    }
}

