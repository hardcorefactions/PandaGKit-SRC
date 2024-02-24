/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package dev.panda.gkit.utilities;

import dev.panda.gkit.PandaGKit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class TaskUtil {
    private static final JavaPlugin plugin = PandaGKit.get();

    public static void run(Runnable runnable) {
        Bukkit.getServer().getScheduler().runTask((Plugin)plugin, runnable);
    }

    public static void runTimer(Runnable runnable, long delay, long timer) {
        Bukkit.getServer().getScheduler().runTaskTimer((Plugin)plugin, runnable, delay, timer);
    }

    public static void runTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer((Plugin)plugin, delay, timer);
    }

    public static void runTimerAsync(Runnable runnable, long delay, long timer) {
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously((Plugin)plugin, runnable, delay, timer);
    }

    public static void runTimerAsync(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimerAsynchronously((Plugin)plugin, delay, timer);
    }

    public static void runLater(Runnable runnable, long delay) {
        Bukkit.getServer().getScheduler().runTaskLater((Plugin)plugin, runnable, delay);
    }

    public static void runLaterAsync(Runnable runnable, long delay) {
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously((Plugin)plugin, runnable, delay);
    }

    public static void runLaterSync(Runnable runnable, long delay) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)plugin, runnable, delay);
    }

    public static void runTaskTimerAsynchronously(Runnable runnable, int delay) {
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously((Plugin)plugin, runnable, 20L * (long)delay, 20L * (long)delay);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously((Plugin)plugin, runnable);
    }

    private TaskUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

