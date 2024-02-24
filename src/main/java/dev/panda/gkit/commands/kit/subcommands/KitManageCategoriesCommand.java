/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commands.kit.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.commons.category.menu.CategoriesMenu;
import dev.panda.gkit.commons.category.menu.CategoryEditMenu;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitManageCategoriesCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public KitManageCategoriesCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="escobarkit.managecategories", permission="escobarkit.managecategories", aliases={"escobarkits.managecategories", "gkit.managecategories", "kit.managecategories", "ekit.managecategories", "escobarkit.managecategory"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();
        if (args.length < 1) {
            new CategoriesMenu(this.plugin).openMenu(command.getPlayer());
        } else {
            String categoryName = ChatUtil.capitalize(args[0]);
            Category category = this.plugin.getModuleService().getManagerModule().getCategoryManager().getByName(categoryName);
            if (category == null) {
                ChatUtil.sendMessage((CommandSender)player, "&cCategory '" + categoryName + "' not found.");
            } else {
                new CategoryEditMenu(category, this.plugin).openMenu(player);
            }
        }
    }
}

