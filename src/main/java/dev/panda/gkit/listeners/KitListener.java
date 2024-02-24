/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.AsyncPlayerChatEvent
 *  org.bukkit.plugin.Plugin
 */
package dev.panda.gkit.listeners;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.commons.kit.KitManager;
import dev.panda.gkit.commons.kit.menu.KitEditMenu;
import dev.panda.gkit.commons.kit.menu.KitsMenu;
import dev.panda.gkit.utilities.JavaUtil;
import dev.panda.gkit.utilities.TaskUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.sound.CompatibleSound;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class KitListener
implements Listener {
    private final PandaGKit plugin;

    public KitListener(PandaGKit plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    @EventHandler
    private void onKitDisplayName(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        KitManager kitManager = this.plugin.getModuleService().getManagerModule().getKitManager();
        if (kitManager.getKitEdit().contains("kitDisplayName", player.getUniqueId())) {
            event.setCancelled(true);
            Kit kit = kitManager.getKitEdit().get("kitDisplayName", player.getUniqueId());
            String name = kit.getDisplayName();
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
                kitManager.getKitEdit().remove("kitDisplayName", player.getUniqueId());
                CompatibleSound.VILLAGER_NO.play(player);
                ChatUtil.sendMessage((CommandSender)player, "&cKit displayName process has been cancelled.");
                return;
            }
            kit.setDisplayName(message);
            kit.save();
            TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
            kitManager.getKitEdit().remove("kitDisplayName", player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            ChatUtil.sendMessage((CommandSender)player, "&eKit displayName has been changed from '&r" + name + "&e' to '&r" + message + "&e'.");
        }
    }

    @EventHandler
    private void onKitPermission(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        KitManager kitManager = this.plugin.getModuleService().getManagerModule().getKitManager();
        if (kitManager.getKitEdit().contains("kitPermission", player.getUniqueId())) {
            event.setCancelled(true);
            Kit kit = kitManager.getKitEdit().get("kitPermission", player.getUniqueId());
            String permission = kit.getPermission();
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
                kitManager.getKitEdit().remove("kitPermission", player.getUniqueId());
                CompatibleSound.VILLAGER_NO.play(player);
                ChatUtil.sendMessage((CommandSender)player, "&cKit permission process has been cancelled.");
                return;
            }
            kit.setPermission(message);
            kit.save();
            TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
            kitManager.getKitEdit().remove("kitPermission", player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            ChatUtil.sendMessage((CommandSender)player, "&eKit permission has been changed from '&r" + permission + "&e' to '&r" + message + "&e'.");
        }
    }

    @EventHandler
    private void onKitCooldown(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        KitManager kitManager = this.plugin.getModuleService().getManagerModule().getKitManager();
        if (kitManager.getKitEdit().contains("kitCooldown", player.getUniqueId())) {
            event.setCancelled(true);
            Kit kit = kitManager.getKitEdit().get("kitCooldown", player.getUniqueId());
            int cooldown = kit.getCooldown();
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
                kitManager.getKitEdit().remove("kitCooldown", player.getUniqueId());
                CompatibleSound.VILLAGER_NO.play(player);
                ChatUtil.sendMessage((CommandSender)player, "&cKit cooldown process has been cancelled.");
                return;
            }
            kit.setCooldown(JavaUtil.formatInt(message));
            kit.save();
            TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
            kitManager.getKitEdit().remove("kitCooldown", player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            ChatUtil.sendMessage((CommandSender)player, "&eKit cooldown has been changed from '&r" + JavaUtil.formatDurationInt(cooldown) + "&e' to '&r" + JavaUtil.formatDurationInt(kit.getCooldown()) + "&e'.");
        }
    }

    @EventHandler
    private void onKitCreate(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        KitManager kitManager = this.plugin.getModuleService().getManagerModule().getKitManager();
        String mode = kitManager.getKitCreate().get(player.getUniqueId());
        if (mode != null && mode.equals("kitCreate")) {
            event.setCancelled(true);
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                ChatUtil.sendMessage((CommandSender)player, "&cYou have cancelled adding a kit process!");
                new KitsMenu(this.plugin).openMenu(player);
                CompatibleSound.VILLAGER_NO.play(player);
                return;
            }
            Kit kit = kitManager.getByName(message);
            if (kit != null) {
                ChatUtil.sendMessage((CommandSender)player, "&cKit '" + kit.getName() + "' already created.");
                return;
            }
            kitManager.create(message);
            ChatUtil.sendMessage((CommandSender)player, "&aKit '&f" + message + "&a' has been create.");
            kitManager.getKitCreate().remove(player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            new KitEditMenu(kitManager.getByName(message), this.plugin).openMenu(player);
        }
    }

    @EventHandler
    private void onKitPlaytime(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        KitManager kitManager = this.plugin.getModuleService().getManagerModule().getKitManager();
        if (kitManager.getKitEdit().contains("kitPlaytime", player.getUniqueId())) {
            event.setCancelled(true);
            Kit kit = kitManager.getKitEdit().get("kitPlaytime", player.getUniqueId());
            int playtime = kit.getPlayTimeTime();
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
                kitManager.getKitEdit().remove("kitPlaytime", player.getUniqueId());
                CompatibleSound.VILLAGER_NO.play(player);
                ChatUtil.sendMessage((CommandSender)player, "&cKit playtime process has been cancelled.");
                return;
            }
            kit.setPlayTimeTime(JavaUtil.formatInt(message));
            kit.save();
            TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
            kitManager.getKitEdit().remove("kitPlaytime", player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            ChatUtil.sendMessage((CommandSender)player, "&eKit playtime has been changed from '&r" + JavaUtil.formatDurationInt(playtime) + "&e' to '&r" + JavaUtil.formatDurationInt(kit.getPlayTimeTime()) + "&e'.");
        }
    }

    @EventHandler
    private void onKitUses(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        KitManager kitManager = this.plugin.getModuleService().getManagerModule().getKitManager();
        if (kitManager.getKitEdit().contains("kitUses", player.getUniqueId())) {
            event.setCancelled(true);
            Kit kit = kitManager.getKitEdit().get("kitUses", player.getUniqueId());
            int uses = kit.getUses();
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
                kitManager.getKitEdit().remove("kitUses", player.getUniqueId());
                CompatibleSound.VILLAGER_NO.play(player);
                ChatUtil.sendMessage((CommandSender)player, "&cKit uses process has been cancelled.");
                return;
            }
            Integer newUses = JavaUtil.tryParseInt(message);
            if (newUses == null) {
                ChatUtil.sendMessage((CommandSender)player, "&cPlease enter a valid number!");
                return;
            }
            kit.setUses(newUses);
            kit.save();
            TaskUtil.runLater(() -> new KitEditMenu(kit, this.plugin).openMenu(player), 1L);
            kitManager.getKitEdit().remove("kitUses", player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            ChatUtil.sendMessage((CommandSender)player, "&eKit uses has been changed from '&r" + uses + "&e' to '&r" + kit.getUses() + "&e'.");
        }
    }
}

