/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.commons.kit;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.BukkitUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.file.FileConfig;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class KitManager {
    private final FileConfig kitsConfig = PandaGKit.get().getModuleService().getFileModule().getFile("kits");
    private final Map<String, Kit> kits = Maps.newHashMap();
    private final Table<String, UUID, Kit> kitEdit = HashBasedTable.create();
    private final Table<UUID, Kit, Integer> kitUses = HashBasedTable.create();
    private final Map<UUID, String> kitCreate = Maps.newHashMap();
    private final PandaGKit plugin;

    public void create(String kitName) {
        Kit kit = new Kit(ChatUtil.strip(kitName));
        kit.setEnable(true);
        kit.setShowMenu(true);
        kit.setGlow(true);
        kit.setDisplayName(kitName);
        kit.setAvailable(Arrays.asList("&7&m--------------------------", "&7Cooldown: &c{cooldown}", "&7Uses: &c{uses_left}&7/&c{uses}", "&7Playtime: &c{playtime}", "", "&aYou can claim this kit now!", "&7Store: &astore.escobar.net", "", "&eRight-Click to preview kit", "&7&m--------------------------"));
        kit.setNoAvailable(Arrays.asList("&7&m--------------------------", "&7Cooldown: &c{cooldown}", "&7Uses: &c{uses_left}&7/&c{uses}", "&7Playtime: &c{playtime}", "", "&7Available in: &c{available_in}", "&7Store: &astore.escobar.net", "", "&eRight-Click to preview kit", "&7&m--------------------------"));
        kit.setNoPermission(Arrays.asList("&7&m--------------------------", "&cYou can purchase this kit in &bstore.escobar.net", "", "&eRight-Click to preview kit", "&7&m--------------------------"));
        kit.setMaterial(Material.ITEM_FRAME);
        kit.setData(0);
        kit.setSlot(this.getRandomSlot());
        kit.setCooldown(0);
        kit.setPermission("escobarkit.gkit." + kitName);
        kit.setArmor(new ItemStack[0]);
        kit.setContents(new ItemStack[0]);
        kit.setPlayTime(false);
        kit.setPlayTimeTime(0);
        kit.setUsesEnabled(false);
        kit.setUses(3);
        kit.save();
        this.kits.put(kitName, kit);
    }

    public void delete(String kitName) {
        Kit kit = this.getByName(kitName);
        ConfigurationSection section = this.kitsConfig.getConfiguration().getConfigurationSection("KITS");
        if (section != null) {
            section.set(kit.getName(), null);
            this.kitsConfig.save();
            this.kitsConfig.reload();
            this.kits.remove(kit.getName());
        }
    }

    public void deleteAll() {
        this.kitsConfig.getConfiguration().set("KITS", (Object)Collections.EMPTY_MAP);
        this.kitsConfig.save();
        this.kitsConfig.reload();
        this.kits.clear();
    }

    private int getRandomSlot() {
        return ThreadLocalRandom.current().nextInt(ConfigService.MENU_ROWS * 9);
    }

    public void load() {
        this.kits.clear();
        ConfigurationSection section = this.kitsConfig.getConfiguration().getConfigurationSection("KITS");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                Kit kit = new Kit(key);
                kit.setEnable(section.getBoolean(key + ".ENABLE"));
                kit.setShowMenu(section.getBoolean(key + ".SHOW_MENU"));
                kit.setGlow(section.getBoolean(key + ".ICON.GLOW"));
                kit.setDisplayName(section.getString(key + ".ICON.DISPLAYNAME"));
                kit.setSkullOwner(section.getString(key + ".ICON.SKULL_OWNER"));
                kit.setAvailable(section.getStringList(key + ".ICON.DESCRIPTION.AVAILABLE"));
                kit.setNoAvailable(section.getStringList(key + ".ICON.DESCRIPTION.NO_AVAILABLE"));
                kit.setNoPermission(section.getStringList(key + ".ICON.DESCRIPTION.NO_PERMISSION"));
                kit.setMaterial(Material.valueOf((String)section.getString(key + ".ICON.MATERIAL")));
                kit.setData(section.getInt(key + ".ICON.DATA"));
                kit.setSlot(section.getInt(key + ".ICON.SLOT"));
                kit.setCooldown(section.getInt(key + ".COOLDOWN"));
                kit.setPermission(section.getString(key + ".PERMISSION"));
                kit.setArmor(BukkitUtil.deserializeItemStackArray(section.getString(key + ".ARMOR")));
                kit.setContents(BukkitUtil.deserializeItemStackArray(section.getString(key + ".CONTENTS")));
                String category = section.getString(key + ".CATEGORY");
                kit.setCategory(category != null && !category.isEmpty() ? this.plugin.getModuleService().getManagerModule().getCategoryManager().getByName(category) : null);
                kit.setPlayTime(section.getBoolean(key + ".PLAY-TIME.ENABLE"));
                kit.setPlayTimeTime(section.getInt(key + ".PLAY-TIME.TIME"));
                kit.setUsesEnabled(section.getBoolean(key + ".USES.ENABLE"));
                kit.setUses(section.getInt(key + ".USES.USES"));
                this.kits.put(key, kit);
            }
        }
    }

    public Kit getByName(String name) {
        Map.Entry<String, Kit> entry;
        Iterator<Map.Entry<String, Kit>> var2 = this.kits.entrySet().iterator();
        do {
            if (var2.hasNext()) continue;
            return null;
        } while (!(entry = var2.next()).getKey().equalsIgnoreCase(name));
        return entry.getValue();
    }

    public KitManager(PandaGKit plugin) {
        this.plugin = plugin;
    }

    public Map<String, Kit> getKits() {
        return this.kits;
    }

    public Table<String, UUID, Kit> getKitEdit() {
        return this.kitEdit;
    }

    public Table<UUID, Kit, Integer> getKitUses() {
        return this.kitUses;
    }

    public Map<UUID, String> getKitCreate() {
        return this.kitCreate;
    }
}

