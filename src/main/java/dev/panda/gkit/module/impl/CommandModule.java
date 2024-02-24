/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.module.impl;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commands.ce.CustomEnchantCommand;
import dev.panda.gkit.commands.kit.KitCommand;
import dev.panda.gkit.commands.kit.KitsCommand;
import dev.panda.gkit.commands.kit.subcommands.KitCooldownCommand;
import dev.panda.gkit.commands.kit.subcommands.KitCreateCommand;
import dev.panda.gkit.commands.kit.subcommands.KitDeleteCommand;
import dev.panda.gkit.commands.kit.subcommands.KitEditCommand;
import dev.panda.gkit.commands.kit.subcommands.KitGiveCommand;
import dev.panda.gkit.commands.kit.subcommands.KitListCommand;
import dev.panda.gkit.commands.kit.subcommands.KitManageCategoriesCommand;
import dev.panda.gkit.commands.kit.subcommands.KitReloadCommand;
import dev.panda.gkit.commands.kit.subcommands.KitResetCommand;
import dev.panda.gkit.commands.kit.subcommands.KitSettingsCommand;
import dev.panda.gkit.module.Module;
import dev.panda.gkit.services.impl.ConfigService;

public class CommandModule
extends Module {
    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public void onEnable(PandaGKit plugin) {
        if (ConfigService.CUSTOM_ENCHANT) {
            new CustomEnchantCommand(plugin);
        }
        if (ConfigService.KIT_MENU) {
            new KitsCommand(plugin);
        }
        if (ConfigService.KIT_SUB_COMMAND) {
            new KitCreateCommand(plugin);
            new KitDeleteCommand(plugin);
            new KitGiveCommand(plugin);
            new KitCooldownCommand(plugin);
            new KitResetCommand(plugin);
            new KitSettingsCommand(plugin);
            new KitEditCommand(plugin);
            new KitListCommand(plugin);
            new KitReloadCommand(plugin);
            new KitManageCategoriesCommand(plugin);
        }
        if (ConfigService.KIT_COMMAND) {
            new KitCommand(plugin);
        }
    }
}

