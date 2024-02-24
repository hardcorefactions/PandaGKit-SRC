/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitGiveCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitGiveCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.give", permission="escobarkit.give", aliases={"escobarkits.give", "gkit.give", "kit.give", "ekit.give"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String label = command.getLabel().replace(".give", "");
        String[] args = command.getArgs();
        if (args.length < 2) {
            ChatUtil.sendMessage(sender, "&cUsage: /" + label + " give <player> <kit>");
        } else {
            Player player = Bukkit.getPlayer((String)args[0]);
            if (player == null) {
                ChatUtil.sendMessage(sender, "&cPlayer '" + args[0] + "' not found.");
            } else {
                String kitName = ChatUtil.capitalize(args[1]);
                Kit kit = this.plugin.getModuleService().getManagerModule().getKitManager().getByName(kitName);
                if (kit == null) {
                    ChatUtil.sendMessage(sender, "&cKit '" + kitName + "' not found.");
                } else {
                    kit.give(player, true);
                }
            }
        }
    }
}

