/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.plugin.Plugin
 */
package dev.panda.gkit.module.impl;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.listeners.ArmorListener;
import dev.panda.gkit.listeners.BookListener;
import dev.panda.gkit.listeners.CategoryListener;
import dev.panda.gkit.listeners.KitListener;
import dev.panda.gkit.listeners.SignListener;
import dev.panda.gkit.module.Module;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.menu.ButtonListener;
import org.bukkit.plugin.Plugin;

public class ListenerModule
extends Module {
    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void onEnable(PandaGKit plugin) {
        if (ConfigService.CUSTOM_ENCHANT) {
            new BookListener(plugin);
            new ArmorListener(plugin);
        }
        if (ConfigService.KIT_SIGN) {
            new SignListener(plugin);
        }
        if (ConfigService.KIT_SUB_COMMAND) {
            new KitListener(plugin);
            new CategoryListener(plugin);
        }
        new ButtonListener((Plugin)plugin);
    }
}

