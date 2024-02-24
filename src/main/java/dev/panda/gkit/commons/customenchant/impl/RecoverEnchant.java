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

public class RecoverEnchant
extends ICustomEnchant {
    public RecoverEnchant() {
        super("recover", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&4Recover V").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment gives you Regeneration V", "&7and Absorption I for 5 seconds after killing an enemy.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).addCustomProperty("duration", 5).addCustomProperty("regeneration-level", 5).addCustomProperty("absorption-level", 1).build(), new PotionEffectType[0]);
    }

    @Override
    public void onKill(Player player, Player killer) {
        PotionRestorer.builder().player(killer).effect(PotionEffectType.REGENERATION, (Integer)this.getProperties().getCustomProperty("duration"), (Integer)this.getProperties().getCustomProperty("regeneration-level")).effect(PotionEffectType.ABSORPTION, (Integer)this.getProperties().getCustomProperty("duration"), (Integer)this.getProperties().getCustomProperty("absorption-level")).build().run();
    }
}

