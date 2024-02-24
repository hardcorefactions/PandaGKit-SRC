/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.services;

import dev.panda.gkit.PandaGKit;
import java.util.ArrayList;
import java.util.List;

public abstract class Service {
    private final PandaGKit plugin;
    private static final List<Service> services = new ArrayList<Service>();

    public Service(PandaGKit plugin) {
        this.plugin = plugin;
        services.add(this);
    }

    public abstract void initialize();

    public PandaGKit getPlugin() {
        return this.plugin;
    }

    public static List<Service> getServices() {
        return services;
    }
}

