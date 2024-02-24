/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.command.CommandSender
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class KitResetCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitResetCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.reset", permission="escobarkit.reset", aliases={"gkit.reset", "kit.reset", "ekit.reset", "escobarkits.reset"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String label = command.getLabel().replace(".reset", "");
        String[] args = command.getArgs();
        if (args.length < 1) {
            ChatUtil.sendMessage(sender, "&cUsage: /" + label + " reset <player|all>");
        } else if (args[0].equalsIgnoreCase("all")) {
            if (command.isPlayer()) {
                ChatUtil.sendMessage(sender, "&cOnly the console can run this command.");
                return;
            }
            this.plugin.getModuleService().getManagerModule().getDataManager().resetAll(sender);
        } else {
            OfflinePlayer player = Bukkit.getOfflinePlayer((String)args[0]);
            if (!player.hasPlayedBefore()) {
                ChatUtil.sendMessage(sender, "&cThat player could not be found!");
                return;
            }
            this.plugin.getModuleService().getManagerModule().getDataManager().reset(sender, player, false);
        }
    }
}

