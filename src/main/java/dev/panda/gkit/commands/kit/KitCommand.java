/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commands.kit;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.menu.KitMenu;
import dev.panda.gkit.services.impl.ConfigService;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkits", aliases={"gkit", "kit", "ekit", "escobarkit"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();
        String label = command.getLabel();
        if (args.length < 1) {
            if (!command.isPlayer()) {
                this.sendHelp(sender, label);
            } else if (ConfigService.KIT_MENU) {
                new KitMenu(this.plugin).openMenu(command.getPlayer());
            }
        } else if (args[0].equalsIgnoreCase("help")) {
            this.sendHelp(sender, label);
        } else {
            if (!command.isPlayer()) {
                ChatUtil.sendMessage(sender, "&cThis command in only executable in game.");
                return;
            }
            Player player = command.getPlayer();
            Kit kit = this.plugin.getModuleService().getManagerModule().getKitManager().getByName(args[0]);
            if (kit == null) {
                ChatUtil.sendMessage((CommandSender)player, "&cKit '" + args[0] + "' not found.");
                return;
            }
            kit.give(player, false);
        }
    }

    private void sendHelp(CommandSender sender, String label) {
        ChatUtil.sendMessage(sender, ChatUtil.NORMAL_LINE);
        ChatUtil.sendMessage(sender, " &3&lKit Commands");
        ChatUtil.sendMessage(sender, "");
        ChatUtil.sendMessage(sender, " &f<> &7= &fRequired &7| &f[] &7= &fOptional.");
        ChatUtil.sendMessage(sender, "");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " create <kit> &7- &fCreate a kit");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " delete <kit|all> &7- &fDelete a kit");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " give <sender> <kit> &7- &fGive a kit to sender");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " cooldown <set|remove> <sender> <kit> &7- &fCooldown set/remove to sender");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " reset <player|all> &7- &fReset all players or a player data");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " edit [kit] &7- &fOpen menu to edit all kits");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " settings &7- &fManage PandaGKits settings");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " managecategories [category] &7- &fOpen menu to edit all categories");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " list &7- &fList of all kits");
        ChatUtil.sendMessage(sender, " &7\u25b6 &b/" + label + " reload &7- &fReload all files");
        ChatUtil.sendMessage(sender, ChatUtil.NORMAL_LINE);
    }
}

