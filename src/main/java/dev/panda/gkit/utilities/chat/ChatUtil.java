/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.WordUtils
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.utilities.chat;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class ChatUtil {
    public static String LONG_LINE = "&7&m----------------------------------------";
    public static String NORMAL_LINE = "&7&m-----------------------------";
    public static String SHORT_LINE = "&7&m---------------";
    public static String BLUE = ChatColor.BLUE.toString();
    public static String AQUA = ChatColor.AQUA.toString();
    public static String YELLOW = ChatColor.YELLOW.toString();
    public static String RED = ChatColor.RED.toString();
    public static String GRAY = ChatColor.GRAY.toString();
    public static String GOLD = ChatColor.GOLD.toString();
    public static String GREEN = ChatColor.GREEN.toString();
    public static String WHITE = ChatColor.WHITE.toString();
    public static String BLACK = ChatColor.BLACK.toString();
    public static String BOLD = ChatColor.BOLD.toString();
    public static String ITALIC = ChatColor.ITALIC.toString();
    public static String UNDER_LINE = ChatColor.UNDERLINE.toString();
    public static String STRIKE_THROUGH = ChatColor.STRIKETHROUGH.toString();
    public static String RESET = ChatColor.RESET.toString();
    public static String MAGIC = ChatColor.MAGIC.toString();
    public static String DARK_BLUE = ChatColor.DARK_BLUE.toString();
    public static String DARK_AQUA = ChatColor.DARK_AQUA.toString();
    public static String DARK_GRAY = ChatColor.DARK_GRAY.toString();
    public static String DARK_GREEN = ChatColor.DARK_GREEN.toString();
    public static String DARK_PURPLE = ChatColor.DARK_PURPLE.toString();
    public static String DARK_RED = ChatColor.DARK_RED.toString();
    public static String PINK = ChatColor.LIGHT_PURPLE.toString();

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)text);
    }

    public static List<String> translate(List<String> list) {
        ArrayList<String> text = Lists.newArrayList();
        for (String line : list) {
            text.add(ChatUtil.translate(line));
        }
        return text;
    }

    public static String strip(String text) {
        return ChatColor.stripColor((String)text);
    }

    public static void sendMessage(CommandSender sender, String text) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            player.sendMessage(ChatUtil.translate(text));
        } else {
            sender.sendMessage(ChatUtil.translate(text));
        }
    }

    public static void broadcast(String text) {
        Bukkit.broadcastMessage((String)ChatUtil.translate(text));
    }

    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(ChatUtil.translate(text));
    }

    public static String capitalize(String str) {
        return WordUtils.capitalize((String)str);
    }

    public static String toReadable(Enum<?> enu) {
        return WordUtils.capitalize((String)enu.name().replace("_", " ").toLowerCase());
    }

    private ChatUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

