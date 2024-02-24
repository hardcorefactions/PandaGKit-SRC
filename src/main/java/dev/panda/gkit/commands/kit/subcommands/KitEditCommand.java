/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.menu.KitEditMenu;
import dev.panda.gkit.commons.kit.menu.KitsMenu;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitEditCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitEditCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.edit", permission="escobarkit.edit", aliases={"escobarkits.edit", "gkit.edit", "kit.edit", "ekit.edit"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();
        if (args.length < 1) {
            new KitsMenu(this.plugin).openMenu(command.getPlayer());
        } else {
            String kitName = ChatUtil.capitalize(args[0]);
            Kit kit = this.plugin.getModuleService().getManagerModule().getKitManager().getByName(kitName);
            if (kit == null) {
                ChatUtil.sendMessage((CommandSender)player, "&cKit '" + kitName + "' not found.");
            } else {
                new KitEditMenu(kit, this.plugin).openMenu(player);
            }
        }
    }
}

