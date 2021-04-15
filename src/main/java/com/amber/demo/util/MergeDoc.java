package com.amber.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MergeDoc {

    public static Map<String, String> getFilesDatas(String filePath){
        Map<String, String> files = new HashMap<>();
        File file = new File(filePath);
        String[] fileNameLists = file.list();
        File[] filePathLists = file.listFiles();
        for(int i=0;i<filePathLists.length;i++){
            if(filePathLists[i].isFile()){
                try {
                    String fileDatas = readFile(filePathLists[i]);
                    files.put(fileNameLists[i], fileDatas);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return files;
    }

    public static String readFile(File path) throws IOException{
        InputStream is = new FileInputStream(path);
        byte[] bytes = new byte[5120000];
        int len = is.read(bytes);
        String str = null;
        while(len!=-1){
            str = new String(bytes, 0, len);
            len = is.read(bytes);
        }
        is.close();
        return str;
    }

}
