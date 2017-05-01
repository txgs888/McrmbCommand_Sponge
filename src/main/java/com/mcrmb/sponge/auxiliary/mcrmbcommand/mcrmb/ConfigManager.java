package com.mcrmb.sponge.auxiliary.mcrmbcommand.mcrmb;

import com.mcrmb.sponge.auxiliary.mcrmbcommand.McrmbCommandMain;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.data.CommandItem;
import com.mcrmb.sponge.auxiliary.mcrmbcommand.util.FileUtil;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by txgs888 on 2017/4/30.
 */
public class ConfigManager {
    private static HashMap<String, CommandItem> items;
    private static ConfigurationNode messageNode;

    public static CommandItem getItem(String id) {
        return items.get(id);
    }

    public static boolean containsItem(String id) {
        return items.containsKey(id);
    }

    public static String getMessage(String key) {
        return messageNode.getNode(key).getString();
    }

    public static void init() {
        items = new HashMap<>();
        File dataFile = McrmbCommandMain.instance().getPath().resolve("config.yml").toFile();
        FileUtil.saveResource("config.yml", dataFile, false);
        ConfigurationNode config = null;
        try {
            config = YAMLConfigurationLoader.builder().setFile(dataFile).build().load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageNode = config.getNode("message");//配置文件message节点
        ConfigurationNode itemsNode = config.getNode("items");//配置文件items节点
        for (Map.Entry<Object, ? extends ConfigurationNode> entry : itemsNode.getChildrenMap().entrySet()) { //遍历所有items内的节点
            CommandItem item = CommandItem.create(entry.getValue());
            items.put((String) entry.getKey(), item);
        }
        McrmbPluginInfo.config.command = config.getNode("command").getString("bcmd");
    }
}
