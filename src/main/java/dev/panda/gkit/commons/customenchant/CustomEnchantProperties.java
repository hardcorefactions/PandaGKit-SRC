/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  lombok.NonNull
 *  org.bukkit.Material
 *  org.bukkit.configuration.ConfigurationSection
 */
package dev.panda.gkit.commons.customenchant;

import dev.panda.gkit.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.gkit.utilities.file.FileConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class CustomEnchantProperties {
    private Map<String, Object> customProperties;
    private FileConfig file;
    private boolean glow;
    private Material material;
    private String displayName;
    private List<String> description;
    private int data;
    private Integer level;

    public static CustomEnchantPropertiesBuilder builder() {
        return new CustomEnchantPropertiesBuilder();
    }

    public Object getCustomProperty(String key) {
        return this.customProperties.get(key);
    }

    public CustomEnchantProperties(String name, FileConfig file) {
        this.customProperties = new HashMap<>();
        ConfigurationSection section = file.getConfiguration().getConfigurationSection("BOOKS." + name);
        if (section != null) {
            this.file = file;
            this.glow = section.getBoolean("icon.glow");
            this.displayName = section.getString("icon.displayName");
            this.description = section.getStringList("icon.description");
            String materialString = section.getString("icon.material");
            this.material = materialString != null ? Material.getMaterial(materialString) : CompatibleMaterial.SNOW_BALL.getMaterial();
            this.data = section.getInt("icon.data");
            if (section.contains("level")) {
                this.level = section.getInt("level");
            }
            Map<String, Object> fileMap = section.getValues(true);
            fileMap.remove("icon");
            fileMap.remove("message");
            fileMap.remove("icon.glow");
            fileMap.remove("icon.displayName");
            fileMap.remove("icon.description");
            fileMap.remove("icon.material");
            fileMap.remove("icon.data");
            fileMap.remove("level");
            this.customProperties.putAll(fileMap);
        }
    }

    public CustomEnchantProperties save(String name, @NonNull FileConfig file) {
        this.file = file;
        ConfigurationSection section = file.getConfiguration().getConfigurationSection("BOOKS." + name);
        if (section == null) {
            section = file.getConfiguration().createSection("BOOKS." + name);
        }
        section.set("icon.glow", this.glow);
        section.set("icon.displayName", this.displayName);
        section.set("icon.description", this.description);
        section.set("icon.material", this.material.name());
        section.set("icon.data", this.data);
        if (this.level != null) {
            section.set("level", this.level);
        }
        ConfigurationSection finalSection = section;
        this.customProperties.forEach(finalSection::set);
        file.save();
        return this;
    }

    public String toString() {
        return "CustomEnchantProperties(customProperties=" + this.getCustomProperties() + ", file=" + this.getFile() + ", glow=" + this.isGlow() + ", material=" + this.getMaterial() + ", displayName=" + this.getDisplayName() + ", description=" + this.getDescription() + ", data=" + this.getData() + ", level=" + this.getLevel() + ")";
    }

    public void setCustomProperties(Map<String, Object> customProperties) {
        this.customProperties = customProperties;
    }

    public void setFile(FileConfig file) {
        this.file = file;
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public CustomEnchantProperties(Map<String, Object> customProperties, FileConfig file, boolean glow, Material material, String displayName, List<String> description, int data, Integer level) {
        this.customProperties = customProperties;
        this.file = file;
        this.glow = glow;
        this.material = material;
        this.displayName = displayName;
        this.description = description;
        this.data = data;
        this.level = level;
    }

    public static class CustomEnchantPropertiesBuilder {
        private final Map<String, Object> customProperties = new HashMap<>();
        private FileConfig file;
        private boolean glow;
        private String displayName;
        private List<String> description;
        private Material material;
        private int data;
        private int level;

        public CustomEnchantPropertiesBuilder addCustomProperty(String key, Object value) {
            this.customProperties.put(key, value);
            return this;
        }

        public CustomEnchantPropertiesBuilder setFile(FileConfig file) {
            this.file = file;
            return this;
        }

        public CustomEnchantPropertiesBuilder setLevel(int level) {
            this.level = level;
            return this;
        }

        public CustomEnchantPropertiesBuilder setGlow(boolean glow) {
            this.glow = glow;
            return this;
        }

        public CustomEnchantPropertiesBuilder setDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public CustomEnchantPropertiesBuilder setDescription(List<String> description) {
            this.description = description;
            return this;
        }

        public CustomEnchantPropertiesBuilder setMaterial(String material) {
            this.material = Material.getMaterial(material);
            return this;
        }

        public CustomEnchantPropertiesBuilder setMaterial(Material material) {
            this.material = material;
            return this;
        }

        public CustomEnchantPropertiesBuilder setData(int data) {
            this.data = data;
            return this;
        }

        public CustomEnchantProperties build() {
            return new CustomEnchantProperties(this.customProperties, this.file, this.glow, this.material, this.displayName, this.description, this.data, this.level);
        }
    }
}

