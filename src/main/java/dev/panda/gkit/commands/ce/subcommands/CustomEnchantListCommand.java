/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.chat.ClickEvent$Action
 *  net.md_5.bungee.api.chat.HoverEvent$Action
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.commands.ce.subcommands;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.customenchant.ICustomEnchant;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.chat.InteractableMessage;
import dev.panda.gkit.utilities.command.BaseCommand;
import dev.panda.gkit.utilities.command.Command;
import dev.panda.gkit.utilities.command.CommandArgs;
import java.util.List;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomEnchantListCommand
extends BaseCommand {
    private final PandaGKit plugin;

    public CustomEnchantListCommand(PandaGKit plugin) {
        this.plugin = plugin;
    }

    @Override
    @Command(name="customenchant.list", permission="escobarkit.customenchant.list", aliases={"cenchant.list", "ce.list"}, inGameOnly=false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        ChatUtil.sendMessage(sender, ChatUtil.NORMAL_LINE);
        ChatUtil.sendMessage(sender, " &3&lCustom Enchant List");
        ChatUtil.sendMessage(sender, "");
        List<ICustomEnchant> enchants = this.plugin.getModuleService().getManagerModule().getCustomEnchantManager().getCustomEnchants();
        if (enchants.isEmpty()) {
            ChatUtil.sendMessage(sender, " &cThere are no custom enchantments.");
        } else {
            for (ICustomEnchant customEnchant : enchants) {
                if (command.isPlayer()) {
                    Player player = command.getPlayer();
                    InteractableMessage.builder().player(player).content(" &b" + (customEnchant.getProperties().getDisplayName() == null ? customEnchant.getName() : customEnchant.getProperties().getDisplayName())).clickEvent(ClickEvent.Action.RUN_COMMAND, "customenchant give " + player.getName() + " " + customEnchant.getName() + " 1").hoverEvent(HoverEvent.Action.SHOW_TEXT, "&bClick to give to yourself &5" + customEnchant.getName() + "&b!").send();
                    continue;
                }
                ChatUtil.sendMessage(sender, " &b" + customEnchant.getName());
            }
        }
        ChatUtil.sendMessage(sender, ChatUtil.NORMAL_LINE);
    }
}

