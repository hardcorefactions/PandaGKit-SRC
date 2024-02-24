/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commands.ce.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.utilities.JavaUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomEnchantGiveCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public CustomEnchantGiveCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="customenchant.give", permission="escobarkit.customenchant.give", aliases={"cenchant.give", "ce.give"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String label = command.getLabel().replace(".give", "");
        String[] args = command.getArgs();
        if (args.length < 3) {
            ChatUtil.sendMessage(sender, "&cUsage: /" + label + " give <player> <ce> <amount>");
        } else if (args[0].equalsIgnoreCase("all")) {
            String customEnchantName = args[1].toUpperCase();
            ICustomEnchant customEnchant = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByName(customEnchantName);
            if (customEnchant == null) {
                ChatUtil.sendMessage(sender, "&cCustom Enchant '" + customEnchantName + "' not found.");
            } else {
                Integer amount = JavaUtil.tryParseInt(args[2]);
                if (amount == null) {
                    ChatUtil.sendMessage(sender, "&cAmount must be a number.");
                } else if (amount <= 0) {
                    ChatUtil.sendMessage(sender, "&cAmount must be positive.");
                } else {
                    customEnchant.giveAll(amount);
                }
            }
        } else {
            Player player = Bukkit.getPlayer((String)args[0]);
            if (player == null) {
                ChatUtil.sendMessage(sender, "&cPlayer '" + args[0] + "' not found.");
            } else {
                String customEnchantName = args[1].toUpperCase();
                ICustomEnchant customEnchant = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getByName(customEnchantName);
                if (customEnchant == null) {
                    ChatUtil.sendMessage(sender, "&cCustom Enchant '" + customEnchantName + "' not found.");
                } else {
                    Integer amount = JavaUtil.tryParseInt(args[2]);
                    if (amount == null) {
                        ChatUtil.sendMessage(sender, "&cAmount must be a number.");
                    } else if (amount <= 0) {
                        ChatUtil.sendMessage(sender, "&cAmount must be positive.");
                    } else {
                        customEnchant.give(player, amount);
                    }
                }
            }
        }
    }
}

