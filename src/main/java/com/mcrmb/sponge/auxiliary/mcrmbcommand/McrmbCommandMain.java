package com.mcrmb.sponge.auxiliary.mcrmbcommand;

import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.ConfigManager;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.JavaPlugin;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.McrmbPluginInfo;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

/**
 * Created by txgs888 on 2017/4/30.
 */
@Plugin(id = McrmbPluginInfo.ID, name = McrmbPluginInfo.NAME, version = McrmbPluginInfo.VERSION, authors = {McrmbPluginInfo.AUTHORS}, dependencies = {@Dependency(id = "mcrmb")})
public class McrmbCommandMain extends JavaPlugin {
    private static McrmbCommandMain instance;

    public static void info(String log) {
        instance().getLogger().info(log);
    }

    public static McrmbCommandMain instance() {
        return instance;
    }

    @Override
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;
        super.onServerStart(event);
        ConfigManager.init();
        info("加载成功!");
    }
}