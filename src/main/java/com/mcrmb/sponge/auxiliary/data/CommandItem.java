package com.mcrmb.sponge.auxiliary.data;

import ninja.leaping.configurate.ConfigurationNode;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/30.
 */
public class CommandItem {

    public static void main(String[] args) {
    }

    public static CommandItem create(ConfigurationNode node) {
        CommandItem item = new CommandItem();
        try {
            Class<CommandItem> itemClass = CommandItem.class;
            for (Field field : itemClass.getDeclaredFields()) {
                field.setAccessible(true); //绕过java权限检测
                field.set(item, node.getNode(field.getName()).getString()); //设置属性内容

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
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
