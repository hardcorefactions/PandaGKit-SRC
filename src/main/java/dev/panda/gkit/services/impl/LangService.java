/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.services.impl;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.services.Service;
import dev.panda.gkit.utilities.file.FileConfig;

public class LangService
extends Service {
    private final FileConfig langConfig = this.getPlugin().getModuleService().getFileModule().getFile("lang");
    public static String NEVER;
    public static String KIT_EQUIP;
    public static String KIT_COOLDOWN;
    public static String KIT_DISABLE;
    public static String KIT_NO_PERMISSION;
    public static String KIT_CATEGORY_DISABLED;
    public static String KIT_NO_PLAYTIME;
    public static String KIT_NO_USES;
    public static String CATEGORY_NO_PERMISSION;
    public static String CATEGORY_EMPTY;
    public static String CATEGORY_DISABLED;

    public LangService(PandaGKit plugin) {
        super(plugin);
    }

    @Override
    public void initialize() {
        NEVER = this.langConfig.getString("NEVER");
        KIT_EQUIP = this.langConfig.getString("KIT.EQUIP");
        KIT_COOLDOWN = this.langConfig.getString("KIT.COOLDOWN");
        KIT_DISABLE = this.langConfig.getString("KIT.DISABLE");
        KIT_NO_PERMISSION = this.langConfig.getString("KIT.NO_PERMISSION");
        KIT_CATEGORY_DISABLED = this.langConfig.getString("KIT.CATEGORY_DISABLED");
        KIT_NO_PLAYTIME = this.langConfig.getString("KIT.NO_PLAYTIME");
        KIT_NO_USES = this.langConfig.getString("KIT.NO_USES");
        CATEGORY_NO_PERMISSION = this.langConfig.getString("CATEGORY.NO_PERMISSION");
        CATEGORY_EMPTY = this.langConfig.getString("CATEGORY.EMPTY");
        CATEGORY_DISABLED = this.langConfig.getString("CATEGORY.DISABLED");
    }
}

