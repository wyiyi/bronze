package com.amber.demo.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MergeDoc {
    public static void main(String[] args) {
        Map<String, String> map = getFilesDatas("F:\\南昌相关文档\\60接口");
        for(String key : map.keySet()){
            String value = map.get(key);
            System.out.println("文件名："+key+"   内容："+value);
        }
    }

    public static Map<String, String> getFilesDatas(String filePath){
        Map<String, String> files = new HashMap<>();
        File file = new File(filePath); //需要获取的文件的路径
        String[] fileNameLists = file.list(); //存储文件名的String数组
        File[] filePathLists = file.listFiles(); //存储文件路径的String数组
        for(int i=0;i<filePathLists.length;i++){
            if(filePathLists[i].isFile()){
                try {//读取指定文件路径下的文件内容
                    String fileDatas = readFile(filePathLists[i]);
                    //把文件名作为key,文件内容为value 存储在map中
                    files.put(fileNameLists[i], fileDatas);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return files;
    }

    public static String readFile(File path) throws IOException{
        //创建一个输入流对象
        InputStream is = new FileInputStream(path);
        //定义一个缓冲区
        byte[] bytes = new byte[5120000];// 1kb
        //通过输入流使用read方法读取数据
        int len = is.read(bytes);
        //System.out.println("字节数:"+len);
        String str = null;
        while(len!=-1){
            //把数据转换为字符串
            str = new String(bytes, 0, len);
            //System.out.println(str);
            //继续进行读取
            len = is.read(bytes);
        }
        //释放资源
        is.close();
        return str;
    }

}
