/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.utilities.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandArgs {
    private final CommandSender sender;
    private final Command command;
    private final String label;
    private final String[] args;

    protected CommandArgs(CommandSender sender, Command command, String label, String[] args, int subCommand) {
        String[] modArgs = new String[args.length - subCommand];
        for (int i2 = 0; i2 < args.length - subCommand; ++i2) {
            modArgs[i2] = args[i2 + subCommand];
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(label);
        for (int x2 = 0; x2 < subCommand; ++x2) {
            buffer.append("." + args[x2]);
        }
        String cmdLabel = buffer.toString();
        this.sender = sender;
        this.command = command;
        this.label = cmdLabel;
        this.args = modArgs;
    }

    public String getArgs(int index) {
        return this.args[index];
    }

    public int length() {
        return this.args.length;
    }

    public boolean isPlayer() {
        return this.sender instanceof Player;
    }

    public Player getPlayer() {
        return this.sender instanceof Player ? (Player)this.sender : null;
    }

    public CommandSender getSender() {
        return this.sender;
    }

    public Command getCommand() {
        return this.command;
    }

    public String getLabel() {
        return this.label;
    }

    public String[] getArgs() {
        return this.args;
    }
}

