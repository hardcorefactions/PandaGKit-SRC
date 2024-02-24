/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.plugin.Plugin
 */
package dev.panda.gkit.module;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.module.Module;
import dev.panda.gkit.module.impl.CommandModule;
import dev.panda.gkit.module.impl.FileModule;
import dev.panda.gkit.module.impl.ListenerModule;
import dev.panda.gkit.module.impl.ManagerModule;
import dev.panda.gkit.module.impl.ServiceModule;
import dev.panda.gkit.utilities.chat.ChatUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import org.bukkit.plugin.Plugin;

public class ModuleService {
    private final FileModule fileModule = new FileModule();
    private final ServiceModule serviceModule;
    private final ManagerModule managerModule;
    private final ListenerModule listenerModule;
    private final CommandModule commandModule;

    public ModuleService(PandaGKit plugin) {
        this.serviceModule = new ServiceModule(plugin);
        this.managerModule = new ManagerModule();
        this.listenerModule = new ListenerModule();
        this.commandModule = new CommandModule();
    }

    public void start(PandaGKit plugin) {
        this.fileModule.onEnable(plugin);
        this.serviceModule.onEnable(plugin);
        for (Module module : Module.getOrderModules()) {
            if (module.getPriority() <= 2) continue;
            module.onEnable(plugin);
        }
        plugin.setStarted(true);
    }

    public void stop(PandaGKit plugin) {
        if (plugin.isStarted()) {
            this.managerModule.getDataManager().save();
        }
    }

    public FileModule getFileModule() {
        return this.fileModule;
    }

    public ServiceModule getServiceModule() {
        return this.serviceModule;
    }

    public ManagerModule getManagerModule() {
        return this.managerModule;
    }

    public ListenerModule getListenerModule() {
        return this.listenerModule;
    }

    public CommandModule getCommandModule() {
        return this.commandModule;
    }

    private static class License {
        private final String license;
        private final String ip;
        private final Plugin pluginClass;
        private final String apiKey;
        private final String server = "http://license.pandacommunity.org:9999";
        private LicenseErrorType errorType;
        private String buyer;
        private String generateDate;
        private boolean valid;

        public License(String license, String ip, Plugin pluginClass) {
            this.license = license;
            this.ip = ip;
            this.pluginClass = pluginClass;
            this.apiKey = "TOPssjRDkpFMyuHYBFCfYtOczILxDgWZIIUttDGCPphMPZqnoZ";
            try {
                String line;
                URL url;
                String pluginName = pluginClass.getDescription().getName();
                try {
                    url = new URL(this.server + "/api/check/request/licenses?keyAPI=" + this.apiKey + "&license=" + license + "&plugin=" + pluginName + "&ip=" + ip);
                } catch (MalformedURLException var12) {
                    var12.printStackTrace();
                    this.valid = false;
                    return;
                }
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    builder.append(line);
                }
                String response = builder.toString();
                if (response.equalsIgnoreCase("API_KEY_NOT_VALID")) {
                    this.errorType = LicenseErrorType.API_KEY_NOT_VALID;
                } else if (response.equalsIgnoreCase("INVALID_LICENSE")) {
                    this.errorType = LicenseErrorType.INVALID_LICENSE;
                } else if (response.equalsIgnoreCase("INVALID_PLUGIN_NAME")) {
                    this.errorType = LicenseErrorType.INVALID_PLUGIN_NAME;
                } else if (response.equalsIgnoreCase("INVALID_IP")) {
                    this.errorType = LicenseErrorType.INVALID_IP;
                } else if (response.equalsIgnoreCase("INVALID_ID")) {
                    this.errorType = LicenseErrorType.MAXIMUM_IP_REACHED;
                } else if (response.equalsIgnoreCase("EXPIRED")) {
                    this.errorType = LicenseErrorType.EXPIRED;
                } else if (response.startsWith("VALID")) {
                    this.errorType = LicenseErrorType.VALID;
                    this.valid = true;
                    String[] split = response.split(";");
                    this.buyer = split[1];
                    this.generateDate = split[3];
                } else {
                    this.errorType = LicenseErrorType.PAGE_ERROR;
                }
            } catch (IOException var13) {
                this.valid = false;
                this.errorType = LicenseErrorType.PAGE_ERROR;
            }
        }

        public List<String> getMessages() {
            return Arrays.asList(ChatUtil.NORMAL_LINE, "<color>Your license is " + (this.valid ? "&avalid" : "&cinvalid") + "<color>.", "", "<color>&l" + this.pluginClass.getDescription().getName(), " &7\u27a5 <color>Author&7: &f" + this.pluginClass.getDescription().getAuthors(), " &7\u27a5 <color>Version&7: &f" + this.pluginClass.getDescription().getVersion(), "", this.valid ? "<color>Thanks for purchase in Panda Development." : " <color>Reason: &f" + this.errorType.toString(), "<color>https://discord.pandacommunity.org/", ChatUtil.NORMAL_LINE);
        }

        public void sendMessage() {
            for (String message : this.getMessages()) {
                ChatUtil.log(message.replace("<color>", "&a"));
            }
        }

        public String getLicense() {
            return this.license;
        }

        public String getIp() {
            return this.ip;
        }

        public Plugin getPluginClass() {
            return this.pluginClass;
        }

        public String getApiKey() {
            return this.apiKey;
        }

        public String getServer() {
            return this.server;
        }

        public LicenseErrorType getErrorType() {
            return this.errorType;
        }

        public String getBuyer() {
            return this.buyer;
        }

        public String getGenerateDate() {
            return this.generateDate;
        }

        public boolean isValid() {
            return this.valid;
        }
    }

    private static enum LicenseErrorType {
        PAGE_ERROR,
        API_KEY_NOT_VALID,
        INVALID_LICENSE,
        INVALID_PLUGIN_NAME,
        INVALID_IP,
        MAXIMUM_IP_REACHED,
        EXPIRED,
        VALID;

    }
}

