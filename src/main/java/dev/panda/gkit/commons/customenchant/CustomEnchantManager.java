/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.event.Listener
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 */
package dev.panda.gkit.commons.customenchant;

import com.google.common.collect.Lists;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.CustomEnchantTask;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.utilities.ClassUtil;
import dev.panda.gkit.utilities.TaskUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class CustomEnchantManager {
    private List<ICustomEnchant> customEnchants = Lists.newArrayList();

    public CustomEnchantManager() {
        TaskUtil.runTimer(new CustomEnchantTask(), 5L, 5L);
    }

    public void registerCustomEnchant(ICustomEnchant customEnchant) {
        this.registerEnchant(customEnchant);
    }

    public void load() {
        this.customEnchants.clear();
        for (Class<?> classes : ClassUtil.getClassesInPackage("dev.panda.gkit.commons.customenchant.impl")) {
            if (!ICustomEnchant.class.isAssignableFrom(classes)) continue;
            try {
                this.registerEnchant((ICustomEnchant)classes.newInstance());
            } catch (IllegalAccessException | InstantiationException var4) {
                var4.printStackTrace();
            }
        }
    }

    void registerEnchant(ICustomEnchant customEnchant) {
        this.customEnchants.add(customEnchant);
        Bukkit.getPluginManager().registerEvents((Listener)customEnchant, (Plugin)PandaGKit.get());
    }

    public ICustomEnchant getByName(String name) {
        ICustomEnchant customEnchant;
        Iterator<ICustomEnchant> var2 = this.customEnchants.iterator();
        do {
            if (var2.hasNext()) continue;
            return null;
        } while (!(customEnchant = var2.next()).getName().equalsIgnoreCase(name));
        return customEnchant;
    }

    public ICustomEnchant getByItem(ItemStack item) {
        ICustomEnchant customEnchant;
        Iterator<ICustomEnchant> var2 = this.customEnchants.iterator();
        do {
            if (var2.hasNext()) continue;
            return null;
        } while (!(customEnchant = var2.next()).getItem().isSimilar(item));
        return customEnchant;
    }

    public List<ICustomEnchant> getByArmorItem(ItemStack item) {
        ArrayList<ICustomEnchant> customEnchants = Lists.newArrayList();
        if (item.hasItemMeta() && item.getItemMeta() != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore() != null) {
            for (ICustomEnchant customEnchant : this.customEnchants) {
                for (String lines : Objects.requireNonNull(item.getItemMeta().getLore())) {
                    String line = ChatUtil.strip(lines);
                    if (!line.equals(ChatUtil.strip(ChatUtil.translate(customEnchant.getProperties().getDisplayName())))) continue;
                    customEnchants.add(customEnchant);
                }
            }
            return customEnchants;
        }
        return customEnchants;
    }

    public List<ICustomEnchant> getCustomEnchants() {
        return this.customEnchants;
    }
}

