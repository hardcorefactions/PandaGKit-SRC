/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.potion.PotionEffectType
 */
package dev.panda.gkit.commons.customenchant;

import com.google.common.collect.Lists;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.CustomEnchantProperties;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.PlayerUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.file.FileConfig;
import dev.panda.gkit.utilities.item.ItemBuilder;
import java.util.ArrayList;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

public abstract class ICustomEnchant
implements Listener {
    private final String name;
    private final CustomEnchantProperties properties;
    private final PotionEffectType[] effects;

    public ICustomEnchant(String name, CustomEnchantProperties defaultProperties, PotionEffectType ... effects) {
        this.name = name;
        this.effects = effects;
        FileConfig booksFile = PandaGKit.get().getModuleService().getFileModule().getFile("books");
        this.properties = booksFile.isNewFile() ? defaultProperties.save(name, booksFile) : new CustomEnchantProperties(name, booksFile);
    }

    public ItemStack getItem() {
        return new ItemBuilder(this.getProperties().getMaterial()).setData(this.getProperties().getData()).setName(this.getProperties().getDisplayName()).setLore(this.getProperties().getDescription()).setEnchant(this.getProperties().isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL).build();
    }

    public ItemStack getItem(int amount) {
        return new ItemBuilder(this.getProperties().getMaterial()).setData(this.getProperties().getData()).setName(this.getProperties().getDisplayName()).setLore(this.getProperties().getDescription()).setEnchant(this.getProperties().isGlow(), ConfigService.ENCHANTMENT, ConfigService.ENCHANTMENT_LEVEL).setAmount(amount).build();
    }

    public void give(Player player, int amount) {
        if (PlayerUtil.isInventoryFull(player)) {
            player.getWorld().dropItem(player.getLocation(), this.getItem(amount));
        } else {
            player.getInventory().addItem(new ItemStack[]{this.getItem(amount)});
        }
    }

    public void giveAll(int amount) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (PlayerUtil.isInventoryFull(online)) {
                online.getWorld().dropItem(online.getLocation(), this.getItem(amount));
                continue;
            }
            online.getInventory().addItem(new ItemStack[]{this.getItem(amount)});
        }
    }

    public void apply(ItemStack itemStack) {
        ArrayList<String> lore = Lists.newArrayList();
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert (itemMeta != null);
        if (itemMeta.hasLore()) {
            lore.addAll(Objects.requireNonNull(itemMeta.getLore()));
        }
        if (!lore.contains(ChatUtil.translate(this.getProperties().getDisplayName()))) {
            lore.add(ChatUtil.translate(this.getProperties().getDisplayName()));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
    }

    public void onMove(Player player) {
    }

    public void onKill(Player player, Player killer) {
    }

    public double onHit(Player player, Player damager, double damage) {
        return damage;
    }

    public String getName() {
        return this.name;
    }

    public CustomEnchantProperties getProperties() {
        return this.properties;
    }

    public PotionEffectType[] getEffects() {
        return this.effects;
    }
}

