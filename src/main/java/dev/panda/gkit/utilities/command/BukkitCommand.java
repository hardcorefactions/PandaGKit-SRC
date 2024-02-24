/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandException
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.plugin.Plugin
 */
package dev.panda.gkit.utilities.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class BukkitCommand
extends Command {
    private final Plugin ownerPlugin;
    private final CommandExecutor executor;

    protected BukkitCommand(String label, CommandExecutor executor, Plugin owner) {
        super(label);
        this.executor = executor;
        this.ownerPlugin = owner;
        this.usageMessage = "";
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        boolean success;
        if (!this.ownerPlugin.isEnabled()) {
            return false;
        }
        if (!this.testPermission(sender)) {
            return true;
        }
        try {
            success = this.executor.onCommand(sender, (Command)this, commandLabel, args);
        } catch (Throwable var9) {
            throw new CommandException("Unhandled exception executing command '" + commandLabel + "' in plugin " + this.ownerPlugin.getDescription().getFullName(), var9);
        }
        if (!success && this.usageMessage.length() > 0) {
            for (String line : this.usageMessage.replace("<command>", commandLabel).split("\n")) {
                sender.sendMessage(line);
            }
        }
        return success;
    }
}

