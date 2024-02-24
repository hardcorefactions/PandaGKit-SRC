/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.commands.kit;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.menu.KitMenu;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;

public class KitsCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitsCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkits", aliases={"gkits", "kits", "ekits", "escobarkit"})
    public void onCommand(CommandArgs command) {
        new KitMenu(this.plugin).openMenu(command.getPlayer());
    }
}

