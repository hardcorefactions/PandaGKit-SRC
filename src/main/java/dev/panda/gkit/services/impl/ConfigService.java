/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.enchantments.Enchantment
 */
package dev.panda.gkit.services.impl;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.services.Service;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.file.FileConfig;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class ConfigService
extends Service {
    private final FileConfig mainConfig = this.getPlugin().getModuleService().getFileModule().getFile("config");
    public static String LICENSE;
    public static boolean KIT_COMMAND;
    public static boolean KIT_SUB_COMMAND;
    public static boolean KIT_MENU;
    public static boolean KIT_SIGN;
    public static boolean CUSTOM_ENCHANT;
    public static boolean CLEAR_INVENTORY;
    public static String MENU_TITLE;
    public static int MENU_ROWS;
    public static boolean MENU_FILL_ENABLE;
    public static Material MENU_FILL_MATERIAL;
    public static int MENU_FILL_DATA;
    public static Enchantment ENCHANTMENT;
    public static int ENCHANTMENT_LEVEL;

    public ConfigService(PandaGKit plugin) {
        super(plugin);
    }

    @Override
    public void initialize() {
        LICENSE = this.mainConfig.getString("LICENSE");
        KIT_COMMAND = this.mainConfig.getBoolean("KIT_COMMAND");
        KIT_SUB_COMMAND = this.mainConfig.getBoolean("KIT_SUB_COMMAND");
        KIT_MENU = this.mainConfig.getBoolean("KIT_MENU");
        KIT_SIGN = this.mainConfig.getBoolean("KIT_SIGN");
        CUSTOM_ENCHANT = this.mainConfig.getBoolean("CUSTOM_ENCHANT");
        CLEAR_INVENTORY = this.mainConfig.getBoolean("CLEAR_INVENTORY");
        MENU_TITLE = this.mainConfig.getString("MENU.TITLE");
        MENU_ROWS = this.mainConfig.getInt("MENU.ROWS");
        MENU_FILL_ENABLE = this.mainConfig.getBoolean("MENU.FILL.ENABLE");
        try {
            MENU_FILL_MATERIAL = Material.valueOf((String)this.mainConfig.getString("MENU.FILL.MATERIAL").toUpperCase());
        } catch (Exception var2) {
            MENU_FILL_MATERIAL = Material.AIR;
            ChatUtil.log("&cInvalid material for menu fill material. Using AIR instead.");
        }
        MENU_FILL_DATA = this.mainConfig.getInt("MENU.FILL.DATA");
        ENCHANTMENT = Enchantment.getByName((String)this.mainConfig.getString("GLOW.ENCHANTMENT").toUpperCase());
        ENCHANTMENT_LEVEL = this.mainConfig.getInt("GLOW.LEVEL");
    }
}

