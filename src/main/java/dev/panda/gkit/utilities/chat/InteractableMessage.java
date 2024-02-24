/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.ClickEvent
 *  net.md_5.bungee.api.chat.ClickEvent$Action
 *  net.md_5.bungee.api.chat.HoverEvent
 *  net.md_5.bungee.api.chat.HoverEvent$Action
 *  net.md_5.bungee.api.chat.TextComponent
 *  org.bukkit.entity.Player
 */
package dev.panda.gkit.utilities.chat;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class InteractableMessage {
    private Player player;
    private String content;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;

    public static InteractableMessage builder() {
        return new InteractableMessage();
    }

    public InteractableMessage player(Player player) {
        this.player = player;
        return this;
    }

    public InteractableMessage clickEvent(ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
        return this;
    }

    public InteractableMessage clickEvent(ClickEvent.Action action, String content) {
        this.clickEvent = new ClickEvent(action, content.startsWith("/") ? content : "/" + content);
        return this;
    }

    public InteractableMessage hoverEvent(HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
        return this;
    }

    public InteractableMessage hoverEvent(HoverEvent.Action action, String content) {
        this.hoverEvent = new HoverEvent(action, TextComponent.fromLegacyText((String)ChatColor.translateAlternateColorCodes((char)'&', (String)content)));
        return this;
    }

    public InteractableMessage content(String content) {
        this.content = content;
        return this;
    }

    public void send() {
        if (this.player != null && this.player.isOnline() && this.content != null && !this.content.isEmpty()) {
            ArrayList<TextComponent> components = Lists.newArrayList();
            for (BaseComponent component : TextComponent.fromLegacyText((String)ChatColor.translateAlternateColorCodes((char)'&', (String)this.content))) {
                if (!(component instanceof TextComponent)) {
                    return;
                }
                TextComponent textComponent = (TextComponent)component;
                if (this.clickEvent != null) {
                    textComponent.setClickEvent(this.clickEvent);
                }
                if (this.hoverEvent != null) {
                    textComponent.setHoverEvent(this.hoverEvent);
                }
                components.add(textComponent);
            }
            this.player.spigot().sendMessage(components.toArray(new BaseComponent[0]));
        }
    }

    public InteractableMessage(Player player, String content, ClickEvent clickEvent, HoverEvent hoverEvent) {
        this.player = player;
        this.content = content;
        this.clickEvent = clickEvent;
        this.hoverEvent = hoverEvent;
    }

    public InteractableMessage() {
    }
}

