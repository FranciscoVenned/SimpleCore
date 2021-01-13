package franciscovenned.github.com.simplecore.utils;

import franciscovenned.github.com.simplecore.MainClass;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Config extends YamlConfiguration {

    private final String fileName;
    private final JavaPlugin plugin;

    public Config(JavaPlugin plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    public Config(JavaPlugin plugin, String fileName, String fileExtension) {
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : fileExtension);
        this.createFile();
    }

    public String getFileName() {
        return this.fileName;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    private void createFile() {
        File folder = this.plugin.getDataFolder();

        try {
            File file = new File(folder, this.fileName);
            if (!file.exists()) {
                if (this.plugin.getResource(this.fileName) != null) {
                    this.plugin.saveResource(this.fileName, false);
                } else {
                    this.save(file);
                }
            } else {
                this.load(file);
                this.save(file);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void save() {
        File folder = this.plugin.getDataFolder();

        try {
            this.save(new File(folder, this.fileName));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Config)) {
            return false;
        } else {
            Config config;
            label29: {
                config = (Config)o;
                if (this.fileName != null) {
                    if (this.fileName.equals(config.fileName)) {
                        break label29;
                    }
                } else if (config.fileName == null) {
                    break label29;
                }

                return false;
            }

            if (this.plugin != null) {
                return this.plugin.equals(config.plugin);
            } else {
                return config.plugin == null;
            }
        }
    }

    public int hashCode() {
        int result = this.fileName != null ? this.fileName.hashCode() : 0;
        result = 31 * result + (this.plugin != null ? this.plugin.hashCode() : 0);
        return result;
    }
}
