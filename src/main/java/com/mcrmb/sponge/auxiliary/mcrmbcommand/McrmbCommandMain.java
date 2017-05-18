package com.mcrmb.sponge.auxiliary.mcrmbcommand;

import com.mcrmb.sponge.auxiliary.mcrmbcommand.command.CommandProxy;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.command.module.BuyCommand;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.command.module.HelpCommand;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.command.module.ReloadCommand;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.ConfigManager;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.JavaPlugin;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.McrmbPluginInfo;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

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
        registerCommand();
        info("加载成功!");
    }

    private CommandProxy commandProxy;

    public CommandProxy getCommandProxy() {
        return commandProxy;
    }

    private void registerCommand() {
        this.commandProxy = new CommandProxy();
        CommandSpec basics = CommandSpec.builder()
                .description(Text.of("§2mcrmb.com"))
                .executor(commandProxy)
                .arguments(
                        GenericArguments.remainingRawJoinedStrings(Text.of("arg")))
                .build();
        Sponge.getCommandManager().register(this, basics, McrmbPluginInfo.config.command);
        getCommandProxy().register(new HelpCommand());
        getCommandProxy().register(new BuyCommand());
        getCommandProxy().register(new ReloadCommand());
    }
}
