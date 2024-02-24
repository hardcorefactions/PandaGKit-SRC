/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Material
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.LeatherArmorMeta
 *  org.bukkit.inventory.meta.SkullMeta
 */
package dev.panda.gkit.utilities.item;

import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material, 1);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
    }

    public ItemBuilder(Material material, int damage) {
        this.itemStack = new ItemStack(material, 1, (short)damage);
    }

    public ItemBuilder(Material material, int amount, int damage) {
        this.itemStack = new ItemStack(material, amount, (short)damage);
    }

    public ItemBuilder setName(String name) {
        if (name != null) {
            name = ChatUtil.translate(name);
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.setDisplayName(name);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if (lore != null) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.setLore(ChatUtil.translate(lore));
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setLore(String ... lore) {
        if (lore != null) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.setLore(ChatUtil.translate(Arrays.asList(lore)));
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setEnchant(boolean enchanted) {
        if (enchanted) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setEnchant(boolean enchanted, int level) {
        if (enchanted) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, level, true);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setEnchant(boolean enchanted, Enchantment enchant, int level) {
        if (enchanted) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.addEnchant(enchant, level, true);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setData(int dur) {
        this.itemStack.setDurability((short)dur);
        return this;
    }

    public ItemBuilder setOwner(String owner) {
        if (this.itemStack.getType() == CompatibleMaterial.HUMAN_SKULL.getMaterial()) {
            SkullMeta meta = (SkullMeta)this.itemStack.getItemMeta();
            meta.setOwner(owner);
            this.itemStack.setItemMeta((ItemMeta)meta);
        }
        return this;
    }

    public ItemBuilder setArmorColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)this.itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        this.itemStack.setItemMeta((ItemMeta)leatherArmorMeta);
        return this;
    }

    public ItemStack build() {
        return this.itemStack;
    }
}

