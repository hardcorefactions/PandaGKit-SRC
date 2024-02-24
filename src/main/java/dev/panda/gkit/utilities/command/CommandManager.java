// Decompiled with: Procyon 0.6.0
// Class Version: 8
package dev.panda.gkit.utilities.command;

import org.bukkit.plugin.Plugin;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Set;
import org.bukkit.Bukkit;
import java.util.Collection;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.help.GenericCommandHelpTopic;
import java.util.Comparator;
import org.bukkit.help.HelpTopic;
import java.util.TreeSet;
import org.bukkit.help.HelpTopicComparator;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.Player;
import dev.panda.gkit.utilities.chat.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.lang.reflect.Field;
import org.bukkit.plugin.SimplePluginManager;
import java.util.HashMap;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.reflect.Method;
import java.util.Map;
import org.bukkit.command.CommandExecutor;

public class CommandManager implements CommandExecutor
{
    private final Map<String, Map.Entry<Method, Object>> commandMap;
    private final JavaPlugin plugin;
    private CommandMap map;

    public CommandManager(final JavaPlugin plugin) {
        this.commandMap = new HashMap<String, Map.Entry<Method, Object>>();
        this.plugin = plugin;
        if (plugin.getServer().getPluginManager() instanceof SimplePluginManager) {
            final SimplePluginManager manager = (SimplePluginManager)plugin.getServer().getPluginManager();
            try {
                final Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);
                this.map = (CommandMap)field.get(manager);
            }
            catch (final SecurityException | NoSuchFieldException | IllegalAccessException | IllegalArgumentException var4) {
                var4.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        return this.handleCommand(sender, cmd, label, args);
    }

    public boolean handleCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        int i = args.length;
        while (i >= 0) {
            final StringBuffer buffer = new StringBuffer();
            buffer.append(label.toLowerCase());
            for (int x = 0; x < i; ++x) {
                buffer.append("." + args[x].toLowerCase());
            }
            final String cmdLabel = buffer.toString();
            if (this.commandMap.containsKey(cmdLabel)) {
                final Method method = (Method)this.commandMap.get(cmdLabel).getKey();
                final Object methodObject = this.commandMap.get(cmdLabel).getValue();
                final dev.panda.gkit.utilities.command.Command command = method.getAnnotation(dev.panda.gkit.utilities.command.Command.class);
                if (!command.permission().equals("") && !sender.hasPermission(command.permission())) {
                    sender.sendMessage(ChatUtil.translate("&cNo permission."));
                    return true;
                }
                if (command.inGameOnly() && !(sender instanceof Player)) {
                    sender.sendMessage(ChatUtil.translate("&cThis command in only executable in game."));
                    return true;
                }
                try {
                    method.invoke(methodObject, new CommandArgs(sender, cmd, label, args, cmdLabel.split("\\.").length - 1));
                }
                catch (final IllegalAccessException | InvocationTargetException | IllegalArgumentException var12) {
                    var12.printStackTrace();
                }
                return true;
            }
            else {
                --i;
            }
        }
        return true;
    }

    public void registerCommands(final Object obj) {
        for (final Method m : obj.getClass().getMethods()) {
            if (m.getAnnotation(dev.panda.gkit.utilities.command.Command.class) != null) {
                final dev.panda.gkit.utilities.command.Command command = m.getAnnotation(dev.panda.gkit.utilities.command.Command.class);
                if (m.getParameterTypes().length <= 1 && m.getParameterTypes()[0] == CommandArgs.class) {
                    this.registerCommand(command, command.name(), m, obj);
                    for (final String alias : command.aliases()) {
                        this.registerCommand(command, alias, m, obj);
                    }
                }
                else {
                    System.out.println("Unable to register command " + m.getName() + ". Unexpected method arguments");
                }
            }
        }
    }

    public void registerHelp() {
        final Set<HelpTopic> help = new TreeSet<HelpTopic>(HelpTopicComparator.helpTopicComparatorInstance());
        for (final String s : this.commandMap.keySet()) {
            if (!s.contains(".")) {
                final Command cmd = this.map.getCommand(s);
                final HelpTopic topic = new GenericCommandHelpTopic(cmd);
                help.add(topic);
            }
        }
        final IndexHelpTopic topic2 = new IndexHelpTopic(this.plugin.getName(), "All commands for " + this.plugin.getName(), null, help, "Below is a list of all " + this.plugin.getName() + " commands:");
        Bukkit.getServer().getHelpMap().addTopic(topic2);
    }

    public void unregisterCommands(final Object obj) {
        for (final Method m : obj.getClass().getMethods()) {
            if (m.getAnnotation(dev.panda.gkit.utilities.command.Command.class) != null) {
                final dev.panda.gkit.utilities.command.Command command = m.getAnnotation(dev.panda.gkit.utilities.command.Command.class);
                this.commandMap.remove(command.name().toLowerCase());
                this.commandMap.remove(this.plugin.getName() + ":" + command.name().toLowerCase());
                this.map.getCommand(command.name().toLowerCase()).unregister(this.map);
            }
        }
    }

    public void registerCommand(final dev.panda.gkit.utilities.command.Command command, final String label, final Method m, final Object obj) {
        this.commandMap.put(label.toLowerCase(), new AbstractMap.SimpleEntry<Method, Object>(m, obj));
        this.commandMap.put(this.plugin.getName() + ':' + label.toLowerCase(), new AbstractMap.SimpleEntry<Method, Object>(m, obj));
        final String cmdLabel = label.replace(".", ",").split(",")[0].toLowerCase();
        if (this.map.getCommand(cmdLabel) == null) {
            final Command cmd = new BukkitCommand(cmdLabel, this, this.plugin);
            this.map.register(this.plugin.getName(), cmd);
        }
        if (!command.description().equalsIgnoreCase("") && cmdLabel.equals(label)) {
            this.map.getCommand(cmdLabel).setDescription(command.description());
        }
        if (!command.usage().equalsIgnoreCase("") && cmdLabel.equals(label)) {
            this.map.getCommand(cmdLabel).setUsage(command.usage());
        }
    }
}
