/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.plugin.java.JavaPlugin
 */
package dev.panda.gkit.utilities.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileConfig {
    private final File file;
    private FileConfiguration configuration;
    private boolean newFile;

    public FileConfig(JavaPlugin plugin, String fileName) {
        this.file = new File(plugin.getDataFolder(), fileName);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            if (plugin.getResource(fileName) == null) {
                try {
                    this.file.createNewFile();
                    this.newFile = true;
                } catch (IOException var4) {
                    plugin.getLogger().severe("Failed to create new file " + fileName);
                }
            } else {
                plugin.saveResource(fileName, false);
            }
        }
        this.configuration = YamlConfiguration.loadConfiguration((File)this.file);
    }

    public double getDouble(String path) {
        return this.configuration.contains(path) ? this.configuration.getDouble(path) : 0.0;
    }

    public int getInt(String path) {
        return this.configuration.contains(path) ? this.configuration.getInt(path) : 0;
    }

    public boolean getBoolean(String path) {
        return this.configuration.contains(path) ? this.configuration.getBoolean(path) : false;
    }

    public long getLong(String path) {
        return this.configuration.contains(path) ? this.configuration.getLong(path) : 0L;
    }

    public String getString(String path) {
        return this.configuration.contains(path) ? ChatColor.translateAlternateColorCodes((char)'&', (String)this.configuration.getString(path)) : null;
    }

    public String getString(String path, String callback, boolean colorize) {
        if (this.configuration.contains(path)) {
            return colorize ? ChatColor.translateAlternateColorCodes((char)'&', (String)this.configuration.getString(path)) : this.configuration.getString(path);
        }
        return callback;
    }

    public List<String> getReversedStringList(String path) {
        List<String> list = this.getStringList(path);
        if (list == null) {
            return Arrays.asList("ERROR: STRING LIST NOT FOUND!");
        }
        int size = list.size();
        ArrayList<String> toReturn = new ArrayList<String>();
        for (int i2 = size - 1; i2 >= 0; --i2) {
            toReturn.add(list.get(i2));
        }
        return toReturn;
    }

    public List<String> getStringList(String path) {
        if (!this.configuration.contains(path)) {
            return Arrays.asList("ERROR: STRING LIST NOT FOUND!");
        }
        ArrayList<String> strings = new ArrayList<String>();
        for (String string : this.configuration.getStringList(path)) {
            strings.add(ChatColor.translateAlternateColorCodes((char)'&', (String)string));
        }
        return strings;
    }

    public List<String> getStringListOrDefault(String path, List<String> toReturn) {
        if (!this.configuration.contains(path)) {
            return toReturn;
        }
        ArrayList<String> strings = new ArrayList<String>();
        for (String string : this.configuration.getStringList(path)) {
            strings.add(ChatColor.translateAlternateColorCodes((char)'&', (String)string));
        }
        return strings;
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException var2) {
            Bukkit.getLogger().severe("Could not save config file " + this.file.toString());
            var2.printStackTrace();
        }
    }

    public void reload() {
        this.configuration = YamlConfiguration.loadConfiguration((File)this.file);
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfiguration() {
        return this.configuration;
    }

    public boolean isNewFile() {
        return this.newFile;
    }
}

