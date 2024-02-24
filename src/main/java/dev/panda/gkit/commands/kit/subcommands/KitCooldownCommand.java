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
import dev.panda.gkit.utilities.CooldownUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCooldownCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitCooldownCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.cooldown", permission="escobarkit.cooldown", aliases={"escobarkits.cooldown", "gkit.cooldown", "kit.cooldown", "ekit.cooldown"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String label = command.getLabel().replace(".cooldown", "");
        String[] args = command.getArgs();
        if (args.length < 3) {
            ChatUtil.sendMessage(sender, "&cUsage: /" + label + " cooldown <set|remove> <player> <kit>");
        } else if (args[0].equalsIgnoreCase("set")) {
            Player player = Bukkit.getPlayer((String)args[1]);
            if (player == null) {
                ChatUtil.sendMessage(sender, "&cPlayer '" + args[1] + "' not found.");
                return;
            }
            String kitName = ChatUtil.capitalize(args[2]);
            Kit kit = this.plugin.getModuleService().getManagerModule().getKitManager().getByName(kitName);
            if (kit == null) {
                ChatUtil.sendMessage(sender, "&cKit '" + kitName + "' not found.");
                return;
            }
            CooldownUtil.setCooldown(kit.getName(), player, kit.getCooldown());
            ChatUtil.sendMessage(sender, "&7Set cooldown of " + kit.getName() + " Kit to player &a" + player.getName());
        } else if (args[0].equalsIgnoreCase("remove")) {
            Player player = Bukkit.getPlayer((String)args[1]);
            if (player == null) {
                ChatUtil.sendMessage(sender, "&cPlayer '" + args[1] + "' not found.");
                return;
            }
            String kitName = ChatUtil.capitalize(args[2]);
            Kit kit = this.plugin.getModuleService().getManagerModule().getKitManager().getByName(kitName);
            if (kit == null) {
                ChatUtil.sendMessage(sender, "&cKit '" + kitName + "' not found.");
                return;
            }
            CooldownUtil.removeCooldown(kit.getName(), player);
            ChatUtil.sendMessage(sender, "&7Remove cooldown of " + kit.getName() + " Kit to player &a" + player.getName());
        } else {
            ChatUtil.sendMessage(sender, "&cCooldown subcommand '" + args[0] + "' not found");
        }
    }
}

