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

public class MoltenEnchant
extends ICustomEnchant {
    public MoltenEnchant() {
        super("molten", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&bMolten I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment has a chance of 2% to set", "&7on fire your enemy.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).setLevel(1).addCustomProperty("chance", 2.0).addCustomProperty("duration", 5).build(), new PotionEffectType[0]);
    }

    @Override
    public double onHit(Player player, Player damager, double damage) {
        double chance = (Double)this.getProperties().getCustomProperty("chance");
        if (!JavaUtil.getChance(chance)) {
            return damage;
        }
        player.setFireTicks((Integer)this.getProperties().getCustomProperty("duration") * 20);
        return damage;
    }
}

