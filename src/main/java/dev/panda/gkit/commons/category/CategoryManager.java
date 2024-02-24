/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.configuration.ConfigurationSection
 */
package dev.panda.gkit.commons.category;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.services.impl.ConfigService;
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

public class CategoryManager {
    private final FileConfig categoriesConfig = PandaGKit.get().getModuleService().getFileModule().getFile("categories");
    private final Map<String, Category> categories = Maps.newHashMap();
    private final Table<String, UUID, Category> categoryEdit = HashBasedTable.create();
    private final Map<UUID, String> categoryCreate = Maps.newHashMap();

    public void create(String categoryName) {
        Category category = new Category(ChatUtil.strip(categoryName));
        category.setEnable(true);
        category.setShowMenu(true);
        category.setGlow(true);
        category.setDisplayName(categoryName);
        category.setDescription(Arrays.asList("&7&m--------------------------", "&7Left-Click here to view the GKits.", "&7&m--------------------------"));
        category.setMaterial(Material.PAPER);
        category.setData(0);
        category.setSlot(this.getRandomSlot());
        category.setShouldPermission(false);
        category.setPermission("escobarkit.category." + categoryName);
        category.setRows(3);
        category.save();
        this.categories.put(categoryName, category);
    }

    public void delete(String categoryName) {
        Category category = this.getByName(categoryName);
        ConfigurationSection section = this.categoriesConfig.getConfiguration().getConfigurationSection("CATEGORIES");
        if (section != null) {
            section.set(category.getName(), null);
            this.categoriesConfig.save();
            this.categoriesConfig.reload();
            this.categories.remove(category.getName());
        }
    }

    public void deleteAll() {
        this.categoriesConfig.getConfiguration().set("CATEGORIES", (Object)Collections.EMPTY_MAP);
        this.categoriesConfig.save();
        this.categoriesConfig.reload();
        this.categories.clear();
    }

    private int getRandomSlot() {
        return ThreadLocalRandom.current().nextInt(ConfigService.MENU_ROWS * 9);
    }

    public void load() {
        this.categories.clear();
        ConfigurationSection section = this.categoriesConfig.getConfiguration().getConfigurationSection("CATEGORIES");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                Category category = new Category(key);
                category.setEnable(section.getBoolean(key + ".ENABLE"));
                category.setShowMenu(section.getBoolean(key + ".SHOW-MENU"));
                category.setGlow(section.getBoolean(key + ".ICON.GLOW"));
                category.setDisplayName(section.getString(key + ".ICON.DISPLAYNAME"));
                category.setSkullOwner(section.getString(key + ".ICON.SKULL_OWNER"));
                category.setDescription(section.getStringList(key + ".ICON.DESCRIPTION"));
                category.setMaterial(Material.valueOf((String)section.getString(key + ".ICON.MATERIAL")));
                category.setData(section.getInt(key + ".ICON.DATA"));
                category.setSlot(section.getInt(key + ".ICON.SLOT"));
                category.setShouldPermission(section.getBoolean(key + ".PERMISSION.ENABLE"));
                category.setPermission(section.getString(key + ".PERMISSION.PERMISSION"));
                category.setRows(section.getInt(key + ".ROWS"));
                this.categories.put(key, category);
            }
        }
    }

    public Category getByName(String name) {
        Map.Entry<String, Category> entry;
        Iterator<Map.Entry<String, Category>> var2 = this.categories.entrySet().iterator();
        do {
            if (var2.hasNext()) continue;
            return null;
        } while (!(entry = var2.next()).getKey().equalsIgnoreCase(name));
        return entry.getValue();
    }

    public Map<String, Category> getCategories() {
        return this.categories;
    }

    public Table<String, UUID, Category> getCategoryEdit() {
        return this.categoryEdit;
    }

    public Map<UUID, String> getCategoryCreate() {
        return this.categoryCreate;
    }
}

