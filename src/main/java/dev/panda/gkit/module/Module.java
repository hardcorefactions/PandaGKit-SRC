/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.module;

import dev.panda.gkit.PandaGKit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Module {
    private int priority;
    private static final List<Module> modules = new ArrayList<Module>();

    public Module() {
        modules.add(this);
    }

    public static List<Module> getOrderModules() {
        List<Module> modules = Module.getModules();
        modules.sort(Comparator.comparingInt(Module::getPriority));
        return modules;
    }

    public abstract void onEnable(PandaGKit var1);

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static List<Module> getModules() {
        return modules;
    }
}

