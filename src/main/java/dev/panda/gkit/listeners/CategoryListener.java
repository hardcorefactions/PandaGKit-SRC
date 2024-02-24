/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
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
import dev.panda.gkit.commons.category.Category;
import dev.panda.gkit.commons.category.CategoryManager;
import dev.panda.gkit.commons.category.menu.CategoriesMenu;
import dev.panda.gkit.commons.category.menu.CategoryEditMenu;
import dev.panda.gkit.utilities.JavaUtil;
import dev.panda.gkit.utilities.TaskUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.compatibility.sound.CompatibleSound;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryListener
implements Listener {
    private final PandaGKit plugin;

    public CategoryListener(PandaGKit plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onCategoryDisplayName(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        CategoryManager categoryManager = this.plugin.getModuleService().getManagerModule().getCategoryManager();
        if (categoryManager.getCategoryEdit().contains("categoryDisplayName", player.getUniqueId())) {
            event.setCancelled(true);
            Category category = categoryManager.getCategoryEdit().get("categoryDisplayName", player.getUniqueId());
            assert category != null;
            String name = category.getDisplayName();
            String message = event.getMessage();
            if (message.equals("cancel")) {
                TaskUtil.runLater(() -> new CategoryEditMenu(category, this.plugin).openMenu(player), 1L);
                categoryManager.getCategoryEdit().remove("categoryDisplayName", player.getUniqueId());
                CompatibleSound.VILLAGER_NO.play(player);
                ChatUtil.sendMessage(player, "&cCategory display name process has been cancelled.");
                return;
            }
            category.setDisplayName(message);
            category.save();
            TaskUtil.runLater(() -> new CategoryEditMenu(category, this.plugin).openMenu(player), 1L);
            categoryManager.getCategoryEdit().remove("categoryDisplayName", player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            ChatUtil.sendMessage(player, "&eCategory display name has been changed from '&r" + name + "&e' to '&r" + message + "&e'.");
        }
    }

    @EventHandler
    private void onCategoryPermission(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        CategoryManager categoryManager = this.plugin.getModuleService().getManagerModule().getCategoryManager();
        if (categoryManager.getCategoryEdit().contains("categoryPermission", player.getUniqueId())) {
            event.setCancelled(true);
            Category category = categoryManager.getCategoryEdit().get("categoryPermission", player.getUniqueId());
            assert category != null;
            String permission = category.getPermission();
            String message = event.getMessage();
            if (message.equals("cancel")) {
                TaskUtil.runLater(() -> new CategoryEditMenu(category, this.plugin).openMenu(player), 1L);
                categoryManager.getCategoryEdit().remove("categoryPermission", player.getUniqueId());
                CompatibleSound.VILLAGER_NO.play(player);
                ChatUtil.sendMessage(player, "&cCategory permission process has been cancelled.");
                return;
            }
            category.setPermission(message);
            category.save();
            TaskUtil.runLater(() -> new CategoryEditMenu(category, this.plugin).openMenu(player), 1L);
            categoryManager.getCategoryEdit().remove("categoryPermission", player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            ChatUtil.sendMessage(player, "&eCategory permission has been changed from '&r" + permission + "&e' to '&r" + message + "&e'.");
        }
    }

    @EventHandler
    private void onCategoryLore(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        CategoryManager categoryManager = this.plugin.getModuleService().getManagerModule().getCategoryManager();
        if (categoryManager.getCategoryEdit().contains("categoryDescription", player.getUniqueId())) {
            event.setCancelled(true);
            Category category = categoryManager.getCategoryEdit().get("categoryPermission", player.getUniqueId());
            Object[] args = event.getMessage().split(" ");
            if (args[0].equals("add")) {
                if (args.length < 2) {
                    ChatUtil.sendMessage(player, "&cPlease enter a message.");
                    return;
                }
                assert category != null;
                List<String> lore = category.getDescription();
                String addLore = StringUtils.join(args, ' ', 1, args.length);
                lore.add(ChatUtil.translate(addLore));
                category.setDescription(lore);
                category.save();
                ChatUtil.sendMessage(player, "&eSuccessfully added '&f" + addLore + "&e' to lore.");
            } else if (args[0].equals("remove")) {
                if (args.length < 2) {
                    ChatUtil.sendMessage(player, "&cPlease enter a number or type 'all' to remove all lore.");
                    return;
                }
                assert category != null;
                List<String> lore = category.getDescription();
                if (args[1].equals("all")) {
                    if (lore.isEmpty()) {
                        ChatUtil.sendMessage(player, "&cThis category doesn't have a description.");
                        return;
                    }
                    lore.clear();
                    category.setDescription(lore);
                    category.save();
                    ChatUtil.sendMessage(player, "&eSuccessfully remove all the lore lines.");
                    return;
                }
                int index = JavaUtil.tryParseInt((String)args[1]);
                if (lore.size() <= index || index < 0) {
                    ChatUtil.sendMessage(player, "&cLore number '" + index + "' not found.");
                    return;
                }
                lore.remove(index);
                category.setDescription(lore);
                category.save();
                ChatUtil.sendMessage(player, "&eSuccessfully removed lore with number &a" + index + "&e.");
            } else if (args[0].equals("list")) {
                ChatUtil.sendMessage(player, ChatUtil.NORMAL_LINE);
                ChatUtil.sendMessage(player, "&2&lLore List");
                ChatUtil.sendMessage(player, "");
                assert category != null;
                List<String> lore = category.getDescription();
                if (lore.isEmpty()) {
                    ChatUtil.sendMessage(player, "&cThis category doesn't have a description.");
                } else {
                    AtomicInteger count = new AtomicInteger();
                    lore.forEach(lines -> {
                        ChatUtil.sendMessage(player, "&7[&a" + count + "&7] &r" + lines);
                        count.getAndIncrement();
                    });
                }
                ChatUtil.sendMessage(player, ChatUtil.NORMAL_LINE);
            } else if (args[0].equals("cancel")) {
                TaskUtil.runLater(() -> new CategoryEditMenu(category, this.plugin).openMenu(player), 1L);
                categoryManager.getCategoryEdit().remove("categoryDescription", player.getUniqueId());
                CompatibleSound.VILLAGER_NO.play(player);
                ChatUtil.sendMessage(player, "&cAbility lore process has been cancelled.");
            } else {
                ChatUtil.sendMessage(player, "&cPlease type a correct command message.");
            }
        }
    }

    @EventHandler
    private void onCategoryCreate(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        CategoryManager categoryManager = this.plugin.getModuleService().getManagerModule().getCategoryManager();
        String mode = categoryManager.getCategoryCreate().get(player.getUniqueId());
        if (mode != null && mode.equals("categoryCreate")) {
            event.setCancelled(true);
            String message = event.getMessage();
            if (message.equals("cancel")) {
                ChatUtil.sendMessage(player, "&cYou have cancelled adding a category process!");
                new CategoriesMenu(this.plugin).openMenu(player);
                CompatibleSound.VILLAGER_NO.play(player);
                return;
            }
            Category category = categoryManager.getByName(message);
            if (category != null) {
                ChatUtil.sendMessage(player, "&cCategory '" + category.getName() + "' already created.");
                return;
            }
            categoryManager.create(message);
            ChatUtil.sendMessage(player, "&aCategory '&f" + message + "&a' has been create.");
            categoryManager.getCategoryCreate().remove(player.getUniqueId());
            CompatibleSound.VILLAGER_YES.play(player);
            new CategoryEditMenu(categoryManager.getByName(message), this.plugin).openMenu(player);
        }
    }
}

