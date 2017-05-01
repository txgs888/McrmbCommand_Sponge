package com.mcrmb.sponge.auxiliary.util;

import com.mcrmb.sponge.auxiliary.McrmbCommandMain;

import java.io.*;

/**
 * Created by txgs888 on 2017/4/30.
 */
public class FileUtil {
    public static boolean saveResource(String name, File save, boolean replace) {
        try {
            if (!save.exists() || replace) {
                InputStream inputStream = McrmbCommandMain.instance().getClass().getClassLoader().getResourceAsStream(name);
                OutputStream outputStream = new FileOutputStream(save);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                outputStream.write(bytes);
                outputStream.flush();
                inputStream.close();
                outputStream.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static String readFile(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }


    }
}
