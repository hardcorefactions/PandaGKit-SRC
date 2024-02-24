/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.utilities;

import dev.panda.gkit.PandaGKit;
import dev.panda.gkit.utilities.chat.ChatUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public final class ServerUtil {
    public static String getIP() {
        String ipAddress;
        try {
            String line;
            URL url = new URL("http://checkip.amazonaws.com/%22");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
            ipAddress = builder.toString();
        } catch (UnknownHostException var6) {
            ipAddress = "NONE";
            ChatUtil.log("[PandaLicense] Problem on the IP page.");
        } catch (IOException var7) {
            ipAddress = "NONE";
            ChatUtil.log("[PandaLicense] Error in check your host IP.");
        }
        return ipAddress + ":" + PandaGKit.get().getServer().getPort();
    }

    private ServerUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

