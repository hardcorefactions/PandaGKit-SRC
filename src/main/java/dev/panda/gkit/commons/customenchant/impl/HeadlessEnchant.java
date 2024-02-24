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
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.item.ItemBuilder;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class HeadlessEnchant
extends ICustomEnchant {
    public HeadlessEnchant() {
        super("headless", CustomEnchantProperties.builder().setGlow(true).setDisplayName("&4Headless I").setDescription(Arrays.asList("&6&m---------------------", "&7This enchantment gives you the head", "&7of your enemy after killing them.", "", "&eDrag to your armor to apply", "&6&m---------------------")).setMaterial(Material.BOOK).setData(0).addCustomProperty("head-displayName", "&4{player}&c's head.").build(), new PotionEffectType[0]);
    }

    @Override
    public void onKill(Player player, Player killer) {
        ItemStack head = new ItemBuilder(CompatibleMaterial.HUMAN_SKULL.getMaterial(), 3).setName(String.valueOf(this.getProperties().getCustomProperty("head-displayName")).replace("{player}", player.getName())).setOwner(player.getName()).build();
        player.getWorld().dropItemNaturally(player.getLocation(), head);
    }
}

