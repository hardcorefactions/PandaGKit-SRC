/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.commons.kit.Kit;
import org.bukkit.entity.Player;

public final class PandaGKitAPI {
    private static final PandaGKit plugin = PandaGKit.get();

    public static Kit getKit(String name) {
        return plugin.getModuleService().getManagerModule().getKitManager().getByName(name);
    }

    public static void applyKit(Player player, Kit kit) {
        if (kit != null && player != null && player.isOnline()) {
            kit.give(player, false);
        }
    }

    public static void applyKitForced(Player player, Kit kit) {
        if (kit != null && player != null && player.isOnline()) {
            kit.give(player, true);
        }
    }

    public static ICustomEnchant getCustomEnchantment(String name) {
        return plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByName(name);
    }

    public static void giveCustomEnchant(Player player, ICustomEnchant enchant, int amount) {
        if (enchant != null && player != null && player.isOnline()) {
            enchant.give(player, amount);
        }
    }

    public static void giveCustomEnchant(Player player, ICustomEnchant enchant) {
        PandaGKitAPI.giveCustomEnchant(player, enchant, 1);
    }

    public static void registerCustomEnchantment(ICustomEnchant customEnchant) {
        plugin.getModuleService().getManagerModule().getCustomEnchantManager().registerCustomEnchant(customEnchant);
    }

    private PandaGKitAPI() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

