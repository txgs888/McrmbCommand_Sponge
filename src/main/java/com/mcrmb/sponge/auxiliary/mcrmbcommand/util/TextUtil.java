package com.mcrmb.sponge.auxiliary.mcrmbcommand.util;

import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import org.spongepowered.api.text.Text;

/**
 * Created by txgs888 on 2017/4/29.
 */
public class TextUtil {
    public static Text of(String text) {
        return Text.of(McrmbPluginInfo.config.prefix + text);
    }
}
