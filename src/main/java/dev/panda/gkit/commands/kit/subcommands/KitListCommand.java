/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import java.util.Map;
import org.bukkit.command.CommandSender;

public class KitListCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitListCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.list", permission="escobarkit.list", aliases={"escobarkits.list", "gkit.list", "kit.list", "ekit.list"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String label = command.getLabel().replace(".list", "");
        ChatUtil.sendMessage(sender, ChatUtil.NORMAL_LINE);
        ChatUtil.sendMessage(sender, " &3&lGKit List");
        ChatUtil.sendMessage(sender, "");
        Map<String, Kit> kits = this.plugin.getModuleService().getManagerModule().getKitManager().getKits();
        if (kits.isEmpty()) {
            ChatUtil.sendMessage(sender, " &cThere is no gkit create");
            ChatUtil.sendMessage(sender, " &7/" + label + " create <kit>");
        } else {
            for (Kit kit : kits.values()) {
                ChatUtil.sendMessage(sender, " &b" + kit.getName() + " Kit");
            }
        }
        ChatUtil.sendMessage(sender, ChatUtil.NORMAL_LINE);
    }
}

