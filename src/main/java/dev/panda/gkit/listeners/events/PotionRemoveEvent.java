/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.event.HandlerList
 *  org.bukkit.potion.PotionEffect
 */
package dev.panda.gkit.listeners.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public class PotionRemoveEvent
extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final PotionEffect effect;

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public PotionEffect getEffect() {
        return this.effect;
    }

    public PotionRemoveEvent(Player player, PotionEffect effect) {
        this.player = player;
        this.effect = effect;
    }
}

