/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.utilities.command;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.utilities.command.CommandArgs;

public abstract class BaseCommand {
    public BaseCommand() {
        PandaGKit.get().getModuleService().getManagerModule().getCommandManager().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs var1);
}

