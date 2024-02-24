/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.menu.KitSettingsMenu;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;

public class KitSettingsCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitSettingsCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.settings", permission="escobarkit.settings", aliases={"gkit.settings", "kit.settings", "ekit.settings", "escobarkits.settings"})
    public void onCommand(CommandArgs command) {
        new KitSettingsMenu(this.plugin).openMenu(command.getPlayer());
    }
}

