/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.module.impl;

import com.google.common.collect.Maps;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.module.Module;
import dev.panda.gkit.utilities.file.FileConfig;
import java.util.Map;

public class FileModule
extends Module {
    private final Map<String, FileConfig> files = Maps.newHashMap();

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void onEnable(PandaGKit plugin) {
        this.files.put("config", new FileConfig(plugin, "config.yml"));
        this.files.put("kits", new FileConfig(plugin, "kits.yml"));
        this.files.put("books", new FileConfig(plugin, "books.yml"));
        this.files.put("data", new FileConfig(plugin, "data.yml"));
        this.files.put("lang", new FileConfig(plugin, "lang.yml"));
        this.files.put("categories", new FileConfig(plugin, "categories.yml"));
    }

    public FileConfig getFile(String name) {
        return this.files.get(name);
    }

    public void reload() {
        for (FileConfig file : this.files.values()) {
            file.reload();
        }
    }

    public Map<String, FileConfig> getFiles() {
        return this.files;
    }
}

