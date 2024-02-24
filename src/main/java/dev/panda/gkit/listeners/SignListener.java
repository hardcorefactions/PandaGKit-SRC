/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockState
 *  org.bukkit.block.Sign
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.block.SignChangeEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.plugin.Plugin
 */
package dev.panda.gkit.listeners;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.kit.Kit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class SignListener
implements Listener {
    private final PandaGKit plugin;

    public SignListener(PandaGKit plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    @EventHandler
    private void onColorSign(SignChangeEvent event) {
        if (event.getPlayer().hasPermission("escobarkit.sign")) {
            String[] lines = event.getLines();
            for (int i2 = 0; i2 <= 3; ++i2) {
                event.setLine(i2, ChatUtil.translate(lines[i2]));
            }
        }
    }

    @EventHandler
    private void onKitSign(PlayerInteractEvent event) {
        if (event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            BlockState state = block.getState();
            if (state instanceof Sign) {
                Sign sign = (Sign)state;
                String line1 = ChatUtil.strip(sign.getLine(1));
                String line2 = ChatUtil.strip(sign.getLine(2));
                if (line1.contains("Kit")) {
                    Kit kit = this.plugin.getModuleService().getManagerModule().getKitManager().getByName(line2);
                    if (kit == null) {
                        return;
                    }
                    if (line2.contains(kit.getName())) {
                        kit.give(player, false);
                    }
                }
            }
        }
    }
}

