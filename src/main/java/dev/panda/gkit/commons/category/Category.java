/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.configuration.ConfigurationSection
 */
package dev.panda.gkit.commons.category;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.utilities.file.FileConfig;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class Category {
    private final FileConfig categoriesConfig = PandaGKit.get().getModuleService().getFileModule().getFile("categories");
    private final String name;
    private boolean enable;
    private boolean showMenu;
    private boolean glow;
    private String displayName;
    private String skullOwner;
    private List<String> description;
    private Material material;
    private int data;
    private int slot;
    private int rows;
    private boolean shouldPermission;
    private String permission;

    public Category(String name) {
        this.name = name;
    }

    public void save() {
        ConfigurationSection section = this.categoriesConfig.getConfiguration().getConfigurationSection("CATEGORIES");
        if (section == null) {
            section = this.categoriesConfig.getConfiguration().createSection("CATEGORIES");
        }
        section.set(this.name + ".ENABLE", (Object)this.enable);
        section.set(this.name + ".SHOW-MENU", (Object)this.showMenu);
        section.set(this.name + ".ICON.GLOW", (Object)this.glow);
        section.set(this.name + ".ICON.DISPLAYNAME", (Object)this.displayName);
        section.set(this.name + ".ICON.SKULL_OWNER", (Object)this.skullOwner);
        section.set(this.name + ".ICON.DESCRIPTION", this.description);
        section.set(this.name + ".ICON.MATERIAL", (Object)this.material.name());
        section.set(this.name + ".ICON.DATA", (Object)this.data);
        section.set(this.name + ".ICON.SLOT", (Object)this.slot);
        section.set(this.name + ".PERMISSION.ENABLE", (Object)this.shouldPermission);
        section.set(this.name + ".PERMISSION.PERMISSION", (Object)this.permission);
        section.set(this.name + ".ROWS", (Object)this.rows);
        this.categoriesConfig.save();
        this.categoriesConfig.reload();
    }

    public FileConfig getCategoriesConfig() {
        return this.categoriesConfig;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public boolean isShowMenu() {
        return this.showMenu;
    }

    public boolean isGlow() {
        return this.glow;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getSkullOwner() {
        return this.skullOwner;
    }

    public List<String> getDescription() {
        return this.description;
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getData() {
        return this.data;
    }

    public int getSlot() {
        return this.slot;
    }

    public int getRows() {
        return this.rows;
    }

    public boolean isShouldPermission() {
        return this.shouldPermission;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setSkullOwner(String skullOwner) {
        this.skullOwner = skullOwner;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setShouldPermission(boolean shouldPermission) {
        this.shouldPermission = shouldPermission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

