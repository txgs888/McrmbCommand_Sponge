package com.mcrmb.sponge.auxiliary.mcrmbcommand.data;

import com.mcrmb.sponge.McrmbCoreAPI;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.McrmbCommandMain;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb.ConfigManager;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.util.TextUtil;
import com.mcrmb.sponge.result.PayResult;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/30.
 */
public class CommandItem {

    public static CommandItem create(ConfigurationNode node) {
        CommandItem item = new CommandItem();
        try {
            Class<CommandItem> itemClass = CommandItem.class;
            for (Field field : itemClass.getDeclaredFields()) {
                field.setAccessible(true); //绕过java权限检测
                field.set(item, node.getNode(field.getName()).getValue()); //设置属性内容
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public static void executeCommand(Player player, String cmd) {
        try {
            String type = cmd.contains(":") ? cmd.substring(0, cmd.indexOf(":")) : "console";
            String text = cmd.contains(":") ? cmd.substring(cmd.indexOf(":", cmd.length())) : cmd;
            switch (type) {
                case "player": {
                    Sponge.getCommandManager().process(player, text.replace("{player}", player.getName()));
                    break;
                }
                case "message": {
                    player.sendMessage(Text.of(text));
                    break;
                }
                default: {
                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), text.replace("{player}", player.getName()));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void buyItem(Player player, String id) {
        if (!ConfigManager.containsItem(id)) {
            player.sendMessage(TextUtil.of(ConfigManager.getMessage("not-item")));
            return;
        }
        CommandItem commandItem = ConfigManager.getItem(id);
        if (!commandItem.getPermission().equalsIgnoreCase("none") && !player.hasPermission(commandItem.getPermission())) {
            player.sendMessage(TextUtil.of(ConfigManager.getMessage("not-permission")));
            return;
        }
        Task.builder().name("buy-item-" + id).async().execute(() -> {

            PayResult result = McrmbCoreAPI.pay(player.getName(), commandItem.getPrice(), id + "|" + commandItem.getDisplay());
            if (result == null) {
                player.sendMessage(TextUtil.of(ConfigManager.getMessage("exception")));
                return;
            }
            if (result.getCode() == 101) { //购买成功
                Task.builder().name("execute-command-" + id).execute(() -> {
                    for (String command : commandItem.getCommand()) {
                        executeCommand(player, command);
                    }
                    player.sendMessage(TextUtil.of(ConfigManager.getMessage("success")
                            .replace("{money}", String.valueOf(result.getMoney()))));
                }).submit(McrmbCommandMain.instance());
            } else if (result.getCode() == 102) { //余额不足
                player.sendMessage(TextUtil.of(ConfigManager.getMessage("not-money")
                        .replace("{money}", String.valueOf(result.getMoney()))
                        .replace("{need}", String.valueOf(result.getNeed()))));
            } else if (result.getCode() == 103) { //未充值过
                player.sendMessage(TextUtil.of(ConfigManager.getMessage("not-pay")));
            }
        }).submit(McrmbCommandMain.instance());
    }

    private String id;
    private String display;
    private String permission;
    private int price;
    private List<String> command;

    public String getId() {
        return id;
    }

    public String getDisplay() {
        return display;
    }

    public String getPermission() {
        return permission;
    }

    public int getPrice() {
        return price;
    }

    public List<String> getCommand() {
        return command;
    }
}
