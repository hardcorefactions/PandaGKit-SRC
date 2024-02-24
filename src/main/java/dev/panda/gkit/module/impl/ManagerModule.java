/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.module.impl;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.cache.DataManager;
import dev.panda.gkit.commons.category.CategoryManager;
import dev.panda.gkit.commons.customenchant.CustomEnchantManager;
import dev.panda.gkit.commons.kit.KitManager;
import dev.panda.gkit.module.Module;
import dev.panda.gkit.utilities.command.CommandManager;
import lombok.Getter;

@Getter
public class ManagerModule extends Module {
    private DataManager dataManager;
    private CategoryManager categoryManager;
    private KitManager kitManager;
    private CustomEnchantManager customEnchantManager;
    private CommandManager commandManager;

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void onEnable(PandaGKit plugin) {
        this.categoryManager = new CategoryManager();
        this.kitManager = new KitManager(plugin);
        this.dataManager = new DataManager(plugin);
        this.customEnchantManager = new CustomEnchantManager();
        this.commandManager = new CommandManager(plugin);
        this.load();
    }

    public void load() {
        this.categoryManager.load();
        this.kitManager.load();
        this.dataManager.load();
        this.customEnchantManager.load();
    }

}

