/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 */
package dev.panda.gkit.utilities.compatibility.material;

import dev.panda.gkit.utilities.BukkitUtil;
import org.bukkit.Material;

public enum CompatibleMaterial {
    HUMAN_SKULL("SKULL_ITEM", "LEGACY_SKULL_ITEM"),
    WOOL("WOOL", "LEGACY_WOOL"),
    SNOW_BALL("SNOW_BALL", "LEGACY_SNOW_BALL"),
    CARPET("CARPET", "LEGACY_CARPET"),
    REDSTONE_TORCH("REDSTONE_TORCH_ON", "LEGACY_REDSTONE_TORCH_ON"),
    STORAGE_MINECART("STORAGE_MINECART", "LEGACY_STORAGE_MINECART"),
    WATCH("WATCH", "LEGACY_WATCH"),
    SIGN("SIGN", "LEGACY_SIGN"),
    STAINED_GLASS_PANE("STAINED_GLASS_PANE", "LEGACY_STAINED_GLASS_PANE"),
    ENCHANTMENT_TABLE("ENCHANTMENT_TABLE", "LEGACY_ENCHANTMENT_TABLE"),
    ENDER_PORTAL_FRAME("ENDER_PORTAL_FRAME", "LEGACY_ENDER_PORTAL_FRAME"),
    MILK_BUCKET("MILK_BUCKET", "LEGACY_MILK_BUCKET"),
    SEEDS("SEEDS", "LEGACY_SEEDS"),
    TRIPWIRE_HOOK("TRIPWIRE_HOOK", "LEGACY_TRIPWIRE_HOOK");

    private final String material8;
    private final String material13;

    private CompatibleMaterial(String material8, String material13) {
        this.material8 = material8;
        this.material13 = material13;
    }

    private CompatibleMaterial(String material8) {
        this(material8, null);
    }

    public Material getMaterial() {
        if (BukkitUtil.SERVER_VERSION_INT <= 12) {
            return this.material8 == null ? Material.valueOf((String)"SKULL_ITEM") : Material.valueOf((String)this.material8);
        }
        return this.material13 == null ? Material.valueOf((String)"LEGACY_SKULL_ITEM") : Material.valueOf((String)this.material13);
    }
}

