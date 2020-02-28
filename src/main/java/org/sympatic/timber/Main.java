package org.sympatic.timber;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.sympatic.timber.commands.TimberCommand;
import org.sympatic.timber.config.ConfigurationFile;
import org.sympatic.timber.listeners.TimberListener;
import org.sympatic.timber.state.TimberState;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Sympatic.
 * A lightweight timber plugin.
 * Inspired by the UHC scenario Timber.
 *
 * Mining a tree causes the whole tree to fall down.
 * Using an axe causes the tree to fall down faster.
 * The better the axe is, the faster the tree falls down.
 */
public class Main extends JavaPlugin {

    @Getter private ConfigurationFile configurationFile;
    @Getter @Setter private TimberState timberState;

    /**
     * Triggers when plugin is enabled.
     */
    @Override
    public void onEnable() {
        registerConfigurations();
        registerListeners();
        registerCommands();

        getLogger().log(Level.INFO, "Timber plugin has been enabled!");
        getLogger().log(Level.INFO, "Timber functionaility has been " + (timberState.getString()));
    }

    /**
     * Triggers when plugin is disabled.
     */
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Timber functionaility has been disabled");
        getLogger().log(Level.INFO, "Timber plugin has been disabled!");
    }

    /**
     * Registers the configuration files.
     */
    private void registerConfigurations() {
        this.configurationFile = new ConfigurationFile(this, "config.yml");
        this.configurationFile.createConfiguration();

        try {
            this.configurationFile.loadConfiguration();
            this.timberState = configurationFile.getConfig().getBoolean("enabled") ? TimberState.ENABLED : TimberState.DISABLED;
        } catch (IOException | InvalidConfigurationException e) {
            getLogger().log(Level.SEVERE, "Could not load configuration file: " + configurationFile.getFileName() + ". File has not been registered.");
            e.printStackTrace();

            this.getPluginLoader().disablePlugin(this);
            return;
        }

        getLogger().log(Level.INFO, "Configuration for Timber have been registered!");
    }

    /**
     * Registers all listeners to the plugin.
     */
    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new TimberListener(this), this);

        getLogger().log(Level.INFO, "Listeners for Timber have been registered!");
    }

    /**
     * Registers all commands to the plugin.
     */
    private void registerCommands() {
        this.getCommand("timber").setExecutor(new TimberCommand(this));

        getLogger().log(Level.INFO, "Commands for Timber have been registered");
    }

}
