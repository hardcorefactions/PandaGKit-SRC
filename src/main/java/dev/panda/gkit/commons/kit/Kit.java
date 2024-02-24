// Decompiled with: Procyon 0.6.0
// Class Version: 8
package dev.panda.gkit.commons.kit;

import org.bukkit.configuration.ConfigurationSection;
import dev.panda.gkit.utilities.BukkitUtil;
import org.bukkit.command.CommandSender;
import dev.panda.gkit.utilities.chat.ChatUtil;

import java.util.ArrayList;
import java.util.Map;
import dev.panda.gkit.listeners.events.ArmorEquipEvent;
import dev.panda.gkit.services.impl.ConfigService;
import org.bukkit.event.Event;
import dev.panda.gkit.listeners.events.ArmorUnEquipEvent;
import org.bukkit.Bukkit;
import dev.panda.gkit.services.impl.LangService;
import dev.panda.gkit.utilities.PlayerUtil;
import dev.panda.gkit.utilities.CooldownUtil;
import java.util.Iterator;
import dev.panda.gkit.utilities.JavaUtil;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.commons.category.Category;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import java.util.List;
import dev.panda.gkit.utilities.file.FileConfig;

public class Kit
{
    private final FileConfig kitsConfig;
    private final String name;
    private boolean enable;
    private boolean showMenu;
    private boolean glow;
    private String displayName;
    private String skullOwner;
    private List<String> available;
    private List<String> noAvailable;
    private List<String> noPermission;
    private Material material;
    private int data;
    private int slot;
    private int cooldown;
    private String permission;
    private ItemStack[] armor;
    private ItemStack[] contents;
    private Category category;
    private boolean playTime;
    private int playTimeTime;
    private boolean usesEnabled;
    private int uses;

    public Kit(final String name) {
        this.kitsConfig = PandaGKit.get().getModuleService().getFileModule().getFile("kits");
        this.name = name;
    }

    public List<String> getAvailableStream(Player player) {
        ArrayList<String> lore = Lists.newArrayList();
        for (String line : this.available) {
            lore.add(line.replace("{cooldown}", JavaUtil.formatDurationInt(this.cooldown)).replace("{uses}", String.valueOf(this.uses)).replace("{uses_left}", String.valueOf(this.getUses(player))).replace("{playtime}", JavaUtil.formatDurationInt(this.playTimeTime)));
        }
        return lore;
    }

    public List<String> getNoAvailableStream(Player player) {
        ArrayList<String> lore = Lists.newArrayList();
        for (String line : this.noAvailable) {
            lore.add(line.replace("{cooldown}", JavaUtil.formatDurationInt(this.cooldown)).replace("{cooldown_left}", JavaUtil.formatDurationLong(CooldownUtil.getCooldown(this.name, player))).replace("{available_in}", this.getAvailableIn(player)).replace("{uses}", String.valueOf(this.uses)).replace("{uses_left}", String.valueOf(this.getUses(player))).replace("{playtime}", JavaUtil.formatDurationInt(this.playTimeTime)).replace("{playtime_left}", JavaUtil.formatDurationInt(this.playTimeTime - PlayerUtil.getTicksLived(player))));
        }
        return lore;
    }

    public String getAvailableIn(final Player player) {
        if (this.isPlayTime() && PlayerUtil.getTicksLived(player) < this.getPlayTimeTime()) {
            return JavaUtil.formatDurationInt(this.playTimeTime - PlayerUtil.getTicksLived(player));
        }
        return CooldownUtil.hasCooldown(this.name, player) ? JavaUtil.formatDurationLong(CooldownUtil.getCooldown(this.name, player)) : LangService.NEVER;
    }

    public void equip(final Player player) {
        for (final ItemStack armor : player.getInventory().getArmorContents()) {
            Bukkit.getPluginManager().callEvent(new ArmorUnEquipEvent(player, armor));
        }
        final ItemStack[] contents = this.contents.clone();
        final ItemStack[] armorContents = this.armor.clone();
        if (!ConfigService.CLEAR_INVENTORY) {
            final ItemStack[] var4 = contents;
            for (int var5 = contents.length, var6 = 0; var6 < var5; ++var6) {
                ItemStack itemStack = var4[var6];
                if (itemStack != null && !itemStack.getType().equals(Material.AIR)) {
                    itemStack = itemStack.clone();
                    final Map<Integer, ItemStack> drops = player.getInventory().addItem(new ItemStack[] { itemStack });
                    for (final ItemStack dropItem : drops.values()) {
                        player.getWorld().dropItem(player.getLocation().add(0.0, 1.0, 0.0), dropItem);
                    }
                }
            }
            int countArmor = 0;
            ItemStack[] var8 = player.getInventory().getArmorContents();
            for (int var6 = var8.length, var9 = 0; var9 < var6; ++var9) {
                final ItemStack itemStack = var8[var9];
                if (itemStack != null && itemStack.getType().equals(Material.AIR)) {
                    ++countArmor;
                }
            }
            if (countArmor == 4) {
                player.getInventory().setArmorContents(armorContents);
            }
            else {
                var8 = armorContents;
                for (int var6 = armorContents.length, var9 = 0; var9 < var6; ++var9) {
                    final ItemStack itemStack = var8[var9];
                    if (itemStack != null && !itemStack.getType().equals(Material.AIR)) {
                        final ItemStack itemStackClone = itemStack.clone();
                        final Map<Integer, ItemStack> drops2 = player.getInventory().addItem(new ItemStack[] { itemStackClone });
                        for (final ItemStack dropItem2 : drops2.values()) {
                            player.getWorld().dropItem(player.getLocation().add(0.0, 1.0, 0.0), dropItem2);
                        }
                    }
                }
            }
        }
        else {
            player.getInventory().setContents(contents);
            player.getInventory().setArmorContents(armorContents);
        }
        player.updateInventory();
        Bukkit.getPluginManager().callEvent(new ArmorEquipEvent(player, player.getInventory().getArmorContents()));
    }

    public void give(final Player player, final boolean admin) {
        if (!admin) {
            if (this.category != null && !this.category.isEnable()) {
                ChatUtil.sendMessage(player, LangService.KIT_CATEGORY_DISABLED.replace("{kit}", this.getName()));
                return;
            }
            if (!this.isEnable()) {
                ChatUtil.sendMessage(player, LangService.KIT_DISABLE.replace("{kit}", this.getName()));
                return;
            }
            if (!player.hasPermission(this.getPermission())) {
                ChatUtil.sendMessage(player, LangService.KIT_NO_PERMISSION);
                return;
            }
            final int playerPlayTime = PlayerUtil.getTicksLived(player);
            if (this.isPlayTime() && playerPlayTime < this.getPlayTimeTime()) {
                ChatUtil.sendMessage(player, LangService.KIT_NO_PLAYTIME.replace("{kit}", this.getName()).replace("{time}", JavaUtil.formatDurationInt(this.getPlayTimeTime() - playerPlayTime)));
                return;
            }
            if (CooldownUtil.hasCooldown(this.getName(), player)) {
                ChatUtil.sendMessage(player, LangService.KIT_COOLDOWN.replace("{kit}", this.getName()).replace("{cooldown_left}", JavaUtil.formatDurationLong(CooldownUtil.getCooldown(this.getName(), player))));
                return;
            }
            if (this.isUsesEnabled()) {
                if (PandaGKit.get().getModuleService().getManagerModule().getKitManager().getKitUses().contains(player.getUniqueId(), this)) {
                    final int playerUses = this.getUses(player);
                    if (playerUses >= this.uses) {
                        ChatUtil.sendMessage(player, LangService.KIT_NO_USES.replace("{kit}", this.getName()));
                        return;
                    }
                    PandaGKit.get().getModuleService().getManagerModule().getKitManager().getKitUses().put(player.getUniqueId(), this, playerUses + 1);
                }
                else {
                    PandaGKit.get().getModuleService().getManagerModule().getKitManager().getKitUses().put(player.getUniqueId(), this, 1);
                }
            }
            if (this.getCooldown() > 0) {
                CooldownUtil.setCooldown(this.getName(), player, this.getCooldown());
            }
        }
        this.equip(player);
        ChatUtil.sendMessage(player, LangService.KIT_EQUIP.replace("{kit}", this.getName()));
    }

    public void save() {
        ConfigurationSection section = this.kitsConfig.getConfiguration().getConfigurationSection("KITS");
        if (section == null) {
            section = this.kitsConfig.getConfiguration().createSection("KITS");
        }
        section.set(this.name + ".ENABLE", this.enable);
        section.set(this.name + ".SHOW_MENU", this.showMenu);
        section.set(this.name + ".ICON.GLOW", this.glow);
        section.set(this.name + ".ICON.DISPLAYNAME", this.displayName);
        section.set(this.name + ".ICON.SKULL_OWNER", this.skullOwner);
        section.set(this.name + ".ICON.DESCRIPTION.AVAILABLE", this.available);
        section.set(this.name + ".ICON.DESCRIPTION.NO_AVAILABLE", this.noAvailable);
        section.set(this.name + ".ICON.DESCRIPTION.NO_PERMISSION", this.noPermission);
        section.set(this.name + ".ICON.MATERIAL", this.material.name());
        section.set(this.name + ".ICON.DATA", this.data);
        section.set(this.name + ".ICON.SLOT", this.slot);
        section.set(this.name + ".COOLDOWN", this.cooldown);
        section.set(this.name + ".PERMISSION", this.permission);
        section.set(this.name + ".ARMOR", BukkitUtil.serializeItemStackArray(this.armor));
        section.set(this.name + ".CONTENTS", BukkitUtil.serializeItemStackArray(this.contents));
        section.set(this.name + ".CATEGORY", (this.category == null) ? "" : this.category.getName());
        section.set(this.name + ".PLAY-TIME.ENABLE", this.playTime);
        section.set(this.name + ".PLAY-TIME.TIME", this.playTimeTime);
        section.set(this.name + ".USES.ENABLE", this.usesEnabled);
        section.set(this.name + ".USES.USES", this.uses);
        this.kitsConfig.save();
        this.kitsConfig.reload();
    }

    public int getUses(final Player player) {
        int uses = 0;
        if (PandaGKit.get().getModuleService().getManagerModule().getKitManager().getKitUses().contains(player.getUniqueId(), this)) {
            final Integer playerUses = PandaGKit.get().getModuleService().getManagerModule().getKitManager().getKitUses().get(player.getUniqueId(), this);
            if (playerUses != null) {
                uses = playerUses;
            }
        }
        return uses;
    }

    public FileConfig getKitsConfig() {
        return this.kitsConfig;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public boolean isShowMenu() {
        return this.showMenu;
    }

    public boolean isGlow() {
        return this.glow;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getSkullOwner() {
        return this.skullOwner;
    }

    public List<String> getAvailable() {
        return this.available;
    }

    public List<String> getNoAvailable() {
        return this.noAvailable;
    }

    public List<String> getNoPermission() {
        return this.noPermission;
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getData() {
        return this.data;
    }

    public int getSlot() {
        return this.slot;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public String getPermission() {
        return this.permission;
    }

    public ItemStack[] getArmor() {
        return this.armor;
    }

    public ItemStack[] getContents() {
        return this.contents;
    }

    public Category getCategory() {
        return this.category;
    }

    public boolean isPlayTime() {
        return this.playTime;
    }

    public int getPlayTimeTime() {
        return this.playTimeTime;
    }

    public boolean isUsesEnabled() {
        return this.usesEnabled;
    }

    public int getUses() {
        return this.uses;
    }

    public void setEnable(final boolean enable) {
        this.enable = enable;
    }

    public void setShowMenu(final boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void setGlow(final boolean glow) {
        this.glow = glow;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public void setSkullOwner(final String skullOwner) {
        this.skullOwner = skullOwner;
    }

    public void setAvailable(final List<String> available) {
        this.available = available;
    }

    public void setNoAvailable(final List<String> noAvailable) {
        this.noAvailable = noAvailable;
    }

    public void setNoPermission(final List<String> noPermission) {
        this.noPermission = noPermission;
    }

    public void setMaterial(final Material material) {
        this.material = material;
    }

    public void setData(final int data) {
        this.data = data;
    }

    public void setSlot(final int slot) {
        this.slot = slot;
    }

    public void setCooldown(final int cooldown) {
        this.cooldown = cooldown;
    }

    public void setPermission(final String permission) {
        this.permission = permission;
    }

    public void setArmor(final ItemStack[] armor) {
        this.armor = armor;
    }

    public void setContents(final ItemStack[] contents) {
        this.contents = contents;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setPlayTime(final boolean playTime) {
        this.playTime = playTime;
    }

    public void setPlayTimeTime(final int playTimeTime) {
        this.playTimeTime = playTimeTime;
    }

    public void setUsesEnabled(final boolean usesEnabled) {
        this.usesEnabled = usesEnabled;
    }

    public void setUses(final int uses) {
        this.uses = uses;
    }
}
