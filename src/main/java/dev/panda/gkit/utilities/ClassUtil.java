/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package dev.panda.gkit.utilities;

import dev.panda.gkit.PandaGKit;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ClassUtil {
    public static Collection<Class<?>> getClassesInPackage(String packageName) {
        JarFile jarFile;
        ArrayList classes = new ArrayList();
        CodeSource codeSource = ((Object)((Object)PandaGKit.get())).getClass().getProtectionDomain().getCodeSource();
        URL resource = codeSource.getLocation();
        String relPath = packageName.replace('.', '/');
        String resPath = resource.getPath().replace("%20", " ");
        String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        try {
            jarFile = new JarFile(jarPath);
        } catch (IOException var16) {
            throw new IllegalStateException("Unexpected IOException reading JAR File '" + jarPath + "'", var16);
        }
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            String className = null;
            if (entryName.endsWith(".class") && entryName.startsWith(relPath) && entryName.length() > relPath.length() + "/".length()) {
                className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
            }
            if (className == null) continue;
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException var15) {
                var15.printStackTrace();
            }
            if (clazz == null) continue;
            classes.add(clazz);
        }
        try {
            jarFile.close();
        } catch (IOException var14) {
            var14.printStackTrace();
        }
        return Collections.unmodifiableCollection(classes);
    }

    private ClassUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

