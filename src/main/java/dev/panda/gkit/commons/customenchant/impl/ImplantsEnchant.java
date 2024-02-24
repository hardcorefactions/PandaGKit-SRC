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

public class ImplantsEnchant
extends ICustomEnchant {
    public ImplantsEnchant() {
        super("implants", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&bImplants I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment feeds you", "&7while walking.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).addCustomProperty("hunger", 0.5).addCustomProperty("chance", 50.0).build(), new PotionEffectType[0]);
    }

    @Override
    public void onMove(Player player) {
        if (JavaUtil.getChance((Double)this.getProperties().getCustomProperty("chance"))) {
            int hunger = (int)((Double)this.getProperties().getCustomProperty("hunger") * 2.0);
            int newFood = player.getFoodLevel() + hunger;
            player.setFoodLevel(Math.min(newFood, 20));
        }
    }
}

