/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.module.impl;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.module.Module;
import dev.panda.gkit.services.Service;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.services.impl.LangService;

public class ServiceModule
extends Module {
    private final PandaGKit plugin;

    public ServiceModule(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void onEnable(PandaGKit plugin) {
        new ConfigService(plugin);
        new LangService(plugin);
        this.load(false);
    }

    public void load(boolean reload) {
        if (reload) {
            this.plugin.getModuleService().getFileModule().reload();
        }
        for (Service service : Service.getServices()) {
            service.initialize();
        }
    }
}

