/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.io.BukkitObjectInputStream
 *  org.bukkit.util.io.BukkitObjectOutputStream
 *  org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
 */
package dev.panda.gkit.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public final class BukkitUtil {
    public static final String SERVER_VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);
    public static final int SERVER_VERSION_INT = Integer.parseInt(SERVER_VERSION.replace("1_", "").replaceAll("_R\\d", ""));

    public static String getLocation(Location location) {
        return location == null ? null : location.getWorld().getName() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ();
    }

    public static String serializeLocation(Location location) {
        return location == null ? null : location.getWorld().getName() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ() + ", " + location.getYaw() + ", " + location.getPitch();
    }

    public static Location deserializeLocation(String data) {
        if (data == null) {
            return null;
        }
        String[] splittedData = data.split(", ");
        if (splittedData.length < 6) {
            return null;
        }
        World world = Bukkit.getWorld((String)splittedData[0]);
        double x2 = Double.parseDouble(splittedData[1]);
        double y2 = Double.parseDouble(splittedData[2]);
        double z2 = Double.parseDouble(splittedData[3]);
        float yaw = Float.parseFloat(splittedData[4]);
        float pitch = Float.parseFloat(splittedData[5]);
        return new Location(world, x2, y2, z2, yaw, pitch);
    }

    public static String serializeItemStackArray(ItemStack[] items) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream((OutputStream)outputStream);
            dataOutput.writeInt(items.length);
            ItemStack[] var3 = items;
            int var4 = items.length;
            for (int var5 = 0; var5 < var4; ++var5) {
                ItemStack item = var3[var5];
                dataOutput.writeObject((Object)item);
            }
            dataOutput.close();
            return Base64Coder.encodeLines((byte[])outputStream.toByteArray());
        } catch (Exception var7) {
            var7.printStackTrace();
            return "";
        }
    }

    public static ItemStack[] deserializeItemStackArray(String data) {
        if (data == null) {
            return new ItemStack[0];
        }
        if (data.equals("")) {
            return new ItemStack[0];
        }
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines((String)data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream((InputStream)inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];
            for (int i2 = 0; i2 < items.length; ++i2) {
                items[i2] = (ItemStack)dataInput.readObject();
            }
            dataInput.close();
            return items;
        } catch (Exception var5) {
            var5.printStackTrace();
            return new ItemStack[0];
        }
    }

    private BukkitUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

