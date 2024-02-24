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
import dev.panda.gkit.utilities.PotionRestorer;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class KillerEnchant
extends ICustomEnchant {
    public KillerEnchant() {
        super("killer", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&4Killer V").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment gives you Regeneration V", "&7for 5 seconds after killing an enemy.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).setLevel(5).addCustomProperty("duration", 5).build(), new PotionEffectType[0]);
    }

    @Override
    public void onKill(Player player, Player killer) {
        PotionRestorer.builder().player(killer).effect(PotionEffectType.REGENERATION, (Integer)this.getProperties().getCustomProperty("duration"), this.getProperties().getLevel()).build().run();
    }
}

