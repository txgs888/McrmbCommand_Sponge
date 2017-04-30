package com.mcrmb.sponge.auxiliary;

import com.mcrmb.sponge.auxiliary.mcrmb.JavaPlugin;
import com.mcrmb.sponge.auxiliary.mcrmb.McrmbPluginInfo;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 * Created by txgs888 on 2017/4/30.
 */
@Plugin(id = McrmbPluginInfo.ID, name = McrmbPluginInfo.NAME, version = McrmbPluginInfo.VERSION, authors = {McrmbPluginInfo.AUTHORS})
public class McrmbCommandMain extends JavaPlugin {
    private static McrmbCommandMain instance;

    public static McrmbCommandMain instance() {
        return instance;
    }

    @Override
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;
        super.onServerStart(event);
    }
}
