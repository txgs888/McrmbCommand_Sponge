package com.mcrmb.sponge.auxiliary.mcrmb;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class McrmbPluginInfo {
    public final static String ID = "mcrmb_command";
    public final static String NAME = "McrmbCommand";
    public final static String VERSION = "1.0.0";
    public final static String AUTHORS = "mcrmb.com";

    public static CommentedConfigurationNode commentedConfig;

    public static class config {

    }


    public static void initMcrmbCore() {
        commentedConfig = ConfigManager.get().getConfig();
    }

    public static void save() {
        try {
            //使用反射保存config类中的所有变量
            Class<?> configClass = Class.forName("com.mcrmb.sponge.auxiliary.mcrmb.McrmbPluginInfo$config");
            Field[] fields = configClass.getDeclaredFields();
            CommentedConfigurationNode node = ConfigManager.get().getConfig();
            for (Field field : fields) {
                try {
                    node.getNode(field.getName()).setValue(field.get(configClass));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            ConfigManager.get().save();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
