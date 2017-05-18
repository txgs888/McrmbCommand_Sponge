package com.mcrmb.sponge.auxiliary.mcrmbcommand.command.module;

import com.mcrmb.sponge.auxiliary.mcrmbcommand.command.CommandHandler;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.ConfigManager;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.util.TextUtil;
import org.spongepowered.api.command.CommandSource;

/**
 * Created by txgs888 on 2017/5/18.
 */
public class ReloadCommand implements CommandHandler {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescribe() {
        return "重载插件";
    }

    @Override
    public boolean hasPermission(CommandSource source) {
        return source.hasPermission("mcrmbcommand.admin");
    }

    @Override
    public boolean execute(CommandSource source, String[] args) {
        source.sendMessage(TextUtil.of("§e正在重载..."));
        ConfigManager.init();
        source.sendMessage(TextUtil.of("§a重载完成!"));
        return false;
    }
}
