package com.gb.hudson.utill;

import java.io.File;

/**
 * Created by james on 30/12/2015.
 */
public class FileUtill {

    public static String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
