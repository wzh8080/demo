package com.example.demo;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FindKeyLog {

    public static void main(String[] args) {

        // .log 所在文件夹
        String path = "D:\\Test\\EDS-log-46";

        String key = "\"sharesfrosts\"";

//        unzip("D:\\Test\\EDS-log-46\\log.2024-01-14.0.log.zip");
        getFileList(path, key);
    }

    // 解压zip
    public static void unzip(String zipFile){
        // 要解压文件路径
        File file = new File(zipFile);
        if(!file.exists() || !file.isFile()){
            return;
        }
        // 文件当前文件夹
        String basePath = file.getParent();
        // 缓冲区大小
        int buffersize = 1024;
        int count = 0;
        // 缓冲区
        byte[] buffer = new byte[buffersize];
        try {
            // Zip文件
            ZipFile zfile = new ZipFile(file);
            // 获取Zip包里的所有元素
            Enumeration<ZipEntry> zips = (Enumeration<ZipEntry>) zfile.entries();
            // 遍历Zip包里的所有元素
            while(zips.hasMoreElements()){
                // 获取文件
                ZipEntry entry = zips.nextElement();
                // 文件名
                String name = entry.getName();
                // 解压后文件的全路径
                String filePath = basePath + File.separator + name;
                // 处理zip文件包含文件夹
                if(entry.isDirectory()){
                    System.out.println("isDirectory");
                    File path = new File(filePath);
                    if(!path.exists()){
                        path.mkdir();
                    }
                    continue;
                }
                // 获取压缩文件输入流
                InputStream in = zfile.getInputStream(entry);
                // 带缓冲的输入流
                BufferedInputStream bis = new BufferedInputStream(in);
                // 带缓冲的输入流
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                // 从输入流中读取字节并写入输出流中
                while((count = bis.read(buffer, 0, buffersize)) != -1){
                    bos.write(buffer, 0, count);
                }
                // 释放资源
                bos.flush();
                bos.close();
                bos.close();

            }
            // 关闭文件
            zfile.close();
        } catch (ZipException e) {

        } catch (IOException e) {

        }
    }

    /**
     * 依次读取指定目录下的所有以.log为结尾的文件
     * */
    private static void getFileList(String path,String key) {
        File[] fileList = new File(path).listFiles();
        for (int i = 0; i < fileList.length; i++) {
            File file = fileList[i];
            if (file.isFile()) {
                if (file.getName().endsWith(".log")) {
//                    System.out.println("解析文件："+file.getName());
                    // 逐行读取文件内容
                    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            line = line.toLowerCase();
                            if (line.contains(key)) {
                                System.out.println(line);
                                System.out.println(file.getName());
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
