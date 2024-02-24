package dev.panda.gkit;

import dev.panda.gkit.module.ModuleService;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class PandaGKit extends JavaPlugin {
    private ModuleService moduleService;
    private boolean started;

    public void onEnable() {
        this.moduleService = new ModuleService(this);
        this.moduleService.start(this);
    }

    public void onDisable() {
        this.moduleService.stop(this);
    }

    public static PandaGKit get() {
        return PandaGKit.getPlugin(PandaGKit.class);
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}

