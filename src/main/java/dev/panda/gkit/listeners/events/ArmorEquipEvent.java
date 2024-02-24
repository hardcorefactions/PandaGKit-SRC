/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Cancellable
 *  org.bukkit.event.Event
 *  org.bukkit.event.HandlerList
 *  org.bukkit.inventory.ItemStack
 */
package dev.panda.gkit.listeners.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ArmorEquipEvent
extends Event
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final ItemStack[] armor;
    private boolean isCancelled;

    public ArmorEquipEvent(Player player, ItemStack[] armor) {
        this.player = player;
        this.armor = armor;
        this.isCancelled = false;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean b2) {
        this.isCancelled = b2;
    }

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

    public ItemStack[] getArmor() {
        return this.armor;
    }
}

