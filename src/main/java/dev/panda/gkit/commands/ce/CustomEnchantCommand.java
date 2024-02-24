/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 */
package dev.panda.gkit.commands.ce;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commands.ce.subcommands.CustomEnchantGiveCommand;
import dev.panda.gkit.commands.ce.subcommands.CustomEnchantListCommand;
import dev.panda.gkit.commands.ce.subcommands.CustomEnchantMenuCommand;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class CustomEnchantCommand
extends BaseCommand {
    public CustomEnchantCommand(PandaGKit plugin) {
        new CustomEnchantMenuCommand(plugin);
        new CustomEnchantGiveCommand(plugin);
        new CustomEnchantListCommand(plugin);
    }

    @Override
    @Command(name="customenchant", permission="escobarkit.customenchant", aliases={"cenchant", "ce"})
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String label = command.getLabel();
        ChatUtil.sendMessage(sender, ChatUtil.NORMAL_LINE);
        ChatUtil.sendMessage(sender, " &3&lCustom Enchant Help");
        ChatUtil.sendMessage(sender, "");
        ChatUtil.sendMessage(sender, " &b/" + label + " menu &7- &fOpen Custom Enchant Menu");
        ChatUtil.sendMessage(sender, " &b/" + label + " give <player|all> <ce> <amount> &7- &fGive Custom Enchant Book");
        ChatUtil.sendMessage(sender, " &b/" + label + " list &7- &fList of all Custom Enchants");
        ChatUtil.sendMessage(sender, ChatUtil.NORMAL_LINE);
    }
}

