// Decompiled with: Procyon 0.6.0
// Class Version: 8
package dev.panda.gkit.cache;

import com.google.common.collect.Table;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.CooldownUtil;
import dev.panda.gkit.utilities.chat.ChatUtil;
import dev.panda.gkit.utilities.file.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DataManager
{
    private final PandaGKit plugin;
    private final FileConfig dataConfig;

    public void save() {
        if (!CooldownUtil.getCooldown().isEmpty()) {
            for (Table.Cell<String, UUID, Long> cell : CooldownUtil.getCooldown().cellSet()) {
                this.dataConfig.getConfiguration().set("DATA." + cell.getColumnKey() + ".COOLDOWNS." + cell.getRowKey(), cell.getValue());
            }
        }
        for (Table.Cell<UUID, Kit, Integer> cell : this.plugin.getModuleService().getManagerModule().getKitManager().getKitUses().cellSet()) {
            this.dataConfig.getConfiguration().set("DATA." + cell.getRowKey().toString() + ".USES." + cell.getColumnKey().getName(), cell.getValue());
        }
        this.dataConfig.save();
    }

    public void load() {
        final FileConfiguration configuration = this.dataConfig.getConfiguration();
        for (final String uuid : Objects.requireNonNull(configuration.getConfigurationSection("DATA")).getKeys(false)) {
            if (configuration.contains("DATA." + uuid + ".COOLDOWNS")) {
                for (final String kitName : Objects.requireNonNull(configuration.getConfigurationSection("DATA." + uuid + ".COOLDOWNS")).getKeys(false)) {
                    final long cooldown = this.dataConfig.getLong("DATA." + uuid + ".COOLDOWNS." + kitName);
                    CooldownUtil.getCooldown().put(kitName, UUID.fromString(uuid), cooldown);
                }
            }
            if (configuration.contains("DATA." + uuid + ".USES")) {
                for (final String kitName : Objects.requireNonNull(configuration.getConfigurationSection("DATA." + uuid + ".USES")).getKeys(false)) {
                    final int uses = this.dataConfig.getInt("DATA." + uuid + ".USES." + kitName);
                    final Kit kit = this.plugin.getModuleService().getManagerModule().getKitManager().getByName(kitName);
                    if (kit == null) {
                        configuration.set("DATA." + uuid + ".USES." + kitName, null);
                    }
                    else {
                        this.plugin.getModuleService().getManagerModule().getKitManager().getKitUses().put(UUID.fromString(uuid), kit, uses);
                    }
                }
            }
        }
        this.dataConfig.save();
    }

    public void resetAll(final CommandSender sender) {
        this.save();
        final FileConfiguration configuration = this.dataConfig.getConfiguration();
        final AtomicInteger resetted = new AtomicInteger(0);
        for (final String uuid : Objects.requireNonNull(configuration.getConfigurationSection("DATA")).getKeys(false)) {
            final OfflinePlayer target = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            if (this.reset(sender, target, true)) {
                resetted.incrementAndGet();
            }
        }
        if (resetted.get() == 0) {
            ChatUtil.sendMessage(sender, "&8[&c&l!&8] &7Data not found. (already reset?)");
        }
        else {
            configuration.set("DATA", Collections.EMPTY_MAP);
            this.dataConfig.save();
            this.dataConfig.reload();
            ChatUtil.sendMessage(sender, "&8[&a&l!&8] &7Successfully reset &a" + resetted.get() + "&7 players data.");
        }
    }

    public boolean reset(final CommandSender sender, final OfflinePlayer offlinePlayer, final boolean all) {
        final FileConfiguration configuration = this.dataConfig.getConfiguration();
        final UUID uuid = offlinePlayer.getUniqueId();
        boolean reset = false;
        if (CooldownUtil.getCooldown().containsColumn(uuid)) {
            reset = true;
            CooldownUtil.getCooldown().cellSet().removeIf(cell -> cell.getColumnKey().equals(uuid));
        }
        if (this.plugin.getModuleService().getManagerModule().getKitManager().getKitUses().containsRow(uuid)) {
            reset = true;
            this.plugin.getModuleService().getManagerModule().getKitManager().getKitUses().cellSet().removeIf(cell -> cell.getRowKey().equals(uuid));
        }
        if (!all) {
            ChatUtil.sendMessage(sender, "&8[&a&l!&8] &7Successfully reset &a" + offlinePlayer.getName() + "&7 data.");
            configuration.set("DATA." + uuid, null);
        }
        return reset;
    }

    public DataManager(final PandaGKit plugin) {
        this.dataConfig = PandaGKit.get().getModuleService().getFileModule().getFile("data");
        this.plugin = plugin;
    }
}
