package org.sympatic.timber.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.sympatic.timber.Main;

import java.io.File;
import java.io.IOException;

@Data
@RequiredArgsConstructor
public class ConfigurationFile {

    /**
     * Main class instance.
     */
    private final Main main;

    /**
     * File name for config.
     */
    private final String fileName;

    /**
     * File of config.
     */
    private File file;
    private FileConfiguration config;

    /**
     * Creates the configuration file.
     */
    public void createConfiguration() {
        file = new File(main.getDataFolder(), fileName);

        if (!file.exists() && file.getParentFile().mkdirs()) {
            main.saveResource(fileName, false);
        }
    }

    /**
     * Loads the configuration.
     */
    public void loadConfiguration() throws IOException, InvalidConfigurationException {
        config = new YamlConfiguration();
        config.load(file);
    }


}
