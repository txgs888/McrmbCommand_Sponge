package com.mcrmb.sponge.auxiliary.mcrmbcommand.command.module;

import com.mcrmb.sponge.auxiliary.mcrmbcommand.command.CommandHandler;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.data.CommandItem;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.util.TextUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;


/**
 * Created by txgs888 on 2017/5/1.
 */
public class BuyCommand implements CommandHandler {
    @Override
    public String getName() {
        return "buy";
    }

    @Override
    public String getDescribe() {
        return "购买物品";
    }

    @Override
    public boolean hasPermission(CommandSource source) {
        return true;
    }

    @Override
    public boolean execute(CommandSource source, String[] args) {
        if (args.length == 1 && source instanceof Player) {
            CommandItem.buyItem((Player) source, args[0]);
            return true;
        } else if (args.length == 2 && source.hasPermission("mcrmbcommand.admin")) {
            Player player = Sponge.getServer().getPlayer(args[1]).<Player>get();
            if (!player.isOnline()) {
                source.sendMessage(TextUtil.of("§2这个玩家不在线."));
                return true;
            }
            CommandItem.buyItem(player, args[0]);
            return true;
        }
        if (source.hasPermission("mcrmbcommand.admin")) {
            source.sendMessage(TextUtil.of("§c/" + McrmbPluginInfo.config.command + " buy <项目ID> §7[玩家名字]"));
        } else {
            source.sendMessage(TextUtil.of("§c/" + McrmbPluginInfo.config.command + " buy <项目ID>"));
        }
        return true;
    }
}
