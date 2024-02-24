/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.KitManager;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class KitCreateCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitCreateCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.create", permission="escobarkit.create", aliases={"escobarkits.create", "gkit.create", "kit.create", "ekit.create"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();
        String label = command.getLabel().replace(".create", "");
        if (args.length < 1) {
            ChatUtil.sendMessage(sender, "&cUsage: /" + label + " create <kit>");
        } else {
            String kitName;
            KitManager kitManager = this.plugin.getModuleService().getManagerModule().getKitManager();
            Kit kit = kitManager.getByName(kitName = ChatUtil.capitalize(args[0]));
            if (kit != null) {
                ChatUtil.sendMessage(sender, "&cKit '" + kitName + "' already created.");
            } else {
                kitManager.create(kitName);
                ChatUtil.sendMessage(sender, "&aKit '&f" + kitName + "&a' has been create.");
            }
        }
    }
}

