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

public class NurseryEnchant
extends ICustomEnchant {
    public NurseryEnchant() {
        super("nursery", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&aNursery I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment heals you while", "&7walking.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).addCustomProperty("hearts", 0.5).addCustomProperty("chance", 10.0).build(), new PotionEffectType[0]);
    }

    @Override
    public void onMove(Player player) {
        if (JavaUtil.getChance((Double)this.getProperties().getCustomProperty("chance"))) {
            double hearts = (Double)this.getProperties().getCustomProperty("hearts");
            double newHearts = player.getHealth() + hearts;
            player.setHealth(Math.min(newHearts, player.getMaxHealth()));
        }
    }
}

