/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.potion.PotionEffectType
 */
package dev.panda.gkit.commons.customenchant.impl;

import dev.panda.gkit.commons.customenchant.CustomEnchantProperties;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.utilities.JavaUtil;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class HellForgedEnchant
extends ICustomEnchant {
    public HellForgedEnchant() {
        super("hell-forged", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&3Hell Forged I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment fixes your armor", "&7while walking.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).addCustomProperty("durability", 1).addCustomProperty("chance", 50.0).build(), new PotionEffectType[0]);
    }

    @Override
    public void onMove(Player player) {
        if (JavaUtil.getChance((Double)this.getProperties().getCustomProperty("chance"))) {
            int durability = (Integer)this.getProperties().getCustomProperty("durability");
            for (ItemStack item : player.getInventory().getArmorContents()) {
                if (!item.getType().name().endsWith("_HELMET") && !item.getType().name().endsWith("_CHESTPLATE") && !item.getType().name().endsWith("_LEGGINGS") && !item.getType().name().endsWith("_BOOTS")) continue;
                int newDamage = item.getDurability() - durability;
                item.setDurability((short)Math.max(newDamage, 0));
            }
        }
    }
}

