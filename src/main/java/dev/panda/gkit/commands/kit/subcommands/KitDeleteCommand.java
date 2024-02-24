/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.ConsoleCommandSender
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class KitDeleteCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitDeleteCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.delete", permission="escobarkit.delete", aliases={"escobarkits.delete", "gkit.delete", "kit.delete", "ekit.delete"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();
        String label = command.getLabel().replace(".delete", "");
        if (args.length < 1) {
            ChatUtil.sendMessage(sender, "&cUsage: /" + label + " delete <kit|all>");
        } else if (args[0].equalsIgnoreCase("all")) {
            if (!(sender instanceof ConsoleCommandSender)) {
                ChatUtil.sendMessage(sender, "&cOnly the console can run this command.");
            } else {
                this.plugin.getModuleService().getManagerModule().getKitManager().deleteAll();
                ChatUtil.sendMessage(sender, "&aAll Kits has been delete.");
            }
        } else {
            String kitName = ChatUtil.capitalize(args[0]);
            Kit kit = this.plugin.getModuleService().getManagerModule().getKitManager().getByName(kitName);
            if (kit == null) {
                ChatUtil.sendMessage(sender, "&cKit '" + kitName + "' already deleted.");
            } else {
                this.plugin.getModuleService().getManagerModule().getKitManager().delete(kitName);
                ChatUtil.sendMessage(sender, "&aKit '&f" + kitName + "&a' has been delete.");
            }
        }
    }
}

