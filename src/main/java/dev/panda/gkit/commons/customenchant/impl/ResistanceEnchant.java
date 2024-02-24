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

public class ResistanceEnchant
extends ICustomEnchant {
    public ResistanceEnchant() {
        super("resistance", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&7Resistance I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment gives you resistance", "&7for being able to survive hits.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).setLevel(1).build(), PotionEffectType.DAMAGE_RESISTANCE);
    }
}

