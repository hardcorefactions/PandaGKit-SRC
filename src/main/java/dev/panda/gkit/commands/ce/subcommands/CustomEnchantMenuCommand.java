/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.commands.ce.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.menu.CustomEnchantMenu;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;

public class CustomEnchantMenuCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public CustomEnchantMenuCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="customenchant.menu", permission="escobarkit.customenchant.menu", aliases={"cenchant.menu", "ce.menu"})
    public void onCommand(CommandArgs command) {
        new CustomEnchantMenu(this.plugin).openMenu(command.getPlayer());
    }
}

