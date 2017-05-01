package com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class JavaPlugin {
    private PluginContainer plugin;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        this.plugin = Sponge.getPluginManager().getPlugin(McrmbPluginInfo.ID).get();
        this.logger = plugin.getLogger();
        getPath().toFile().mkdirs();

    }

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path path;

    @Inject
    private Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path configPath;

    public PluginContainer getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public Path getPath() {
        return path;
    }

    public Path getConfigPath() {
        return configPath;
    }

}
