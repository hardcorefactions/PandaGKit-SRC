/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class KitReloadCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitReloadCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.reload", permission="escobarkit.reload", aliases={"escobarkits.reload", "gkit.reload", "kit.reload", "ekit.reload"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        this.plugin.getModuleService().getServiceModule().load(true);
        this.plugin.getModuleService().getManagerModule().load();
        ChatUtil.sendMessage(sender, "&aAll files has been reloaded!");
    }
}

