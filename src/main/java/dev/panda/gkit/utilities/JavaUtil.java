/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.time.DurationFormatUtils
 */
package dev.panda.gkit.utilities;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.time.DurationFormatUtils;

public final class JavaUtil {
    public static Integer tryParseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    public static String formatDurationInt(int input) {
        return DurationFormatUtils.formatDurationWords((long)((long)input * 1000L), (boolean)true, (boolean)true);
    }

    public static String formatDurationLong(long input) {
        return DurationFormatUtils.formatDurationWords((long)input, (boolean)true, (boolean)true);
    }

    public static String formatLongMin(long time) {
        long totalSecs = time / 1000L;
        return String.format("%02d:%02d", totalSecs / 60L, totalSecs % 60L);
    }

    public static String formatLongHour(long time) {
        long totalSecs = time / 1000L;
        long seconds = totalSecs % 60L;
        long minutes = totalSecs % 3600L / 60L;
        long hours = totalSecs / 3600L;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static long formatLong(String input) {
        if (input != null && !input.isEmpty()) {
            long result = 0L;
            StringBuilder number = new StringBuilder();
            for (int i2 = 0; i2 < input.length(); ++i2) {
                String str;
                char c2 = input.charAt(i2);
                if (Character.isDigit(c2)) {
                    number.append(c2);
                    continue;
                }
                if (!Character.isLetter(c2) || (str = number.toString()).isEmpty()) continue;
                result += JavaUtil.convertLong(Integer.parseInt(str), c2);
                number = new StringBuilder();
            }
            return result;
        }
        return -1L;
    }

    private static long convertLong(int value, char unit) {
        switch (unit) {
            case 'M': {
                return (long)value * TimeUnit.DAYS.toMillis(30L);
            }
            case 'd': {
                return (long)value * TimeUnit.DAYS.toMillis(1L);
            }
            case 'h': {
                return (long)value * TimeUnit.HOURS.toMillis(1L);
            }
            case 'm': {
                return (long)value * TimeUnit.MINUTES.toMillis(1L);
            }
            case 's': {
                return (long)value * TimeUnit.SECONDS.toMillis(1L);
            }
            case 'y': {
                return (long)value * TimeUnit.DAYS.toMillis(365L);
            }
        }
        return -1L;
    }

    public static int formatInt(String input) {
        if (input != null && !input.isEmpty()) {
            int result = 0;
            StringBuilder number = new StringBuilder();
            for (int i2 = 0; i2 < input.length(); ++i2) {
                String str;
                char c2 = input.charAt(i2);
                if (Character.isDigit(c2)) {
                    number.append(c2);
                    continue;
                }
                if (!Character.isLetter(c2) || (str = number.toString()).isEmpty()) continue;
                result += JavaUtil.convertInt(Integer.parseInt(str), c2);
                number = new StringBuilder();
            }
            return result;
        }
        return -1;
    }

    private static int convertInt(int value, char unit) {
        switch (unit) {
            case 'd': {
                return value * 60 * 60 * 24;
            }
            case 'h': {
                return value * 60 * 60;
            }
            case 'm': {
                return value * 60;
            }
            case 's': {
                return value;
            }
        }
        return -1;
    }

    public static int randomExcluding(int start, int end, int ... exclude) {
        int ex;
        int random = start + ThreadLocalRandom.current().nextInt(end - start + 1 - exclude.length);
        int[] var4 = exclude;
        int var5 = exclude.length;
        for (int var6 = 0; var6 < var5 && random >= (ex = var4[var6]); ++random, ++var6) {
        }
        return random;
    }

    public static boolean getChance(double minimalChance) {
        return ThreadLocalRandom.current().nextDouble(99.0) + 1.0 >= 100.0 - minimalChance;
    }

    private JavaUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

