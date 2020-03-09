package com.ql;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 统计代码行数
 * @Author qiuliang
 * @Time 2020-03-09 10:28
 * @Version 1.0
 **/
public class StatisticsMain {

    /**
     * 代码文件路径列表
     */
    private static List<String> codeFilePath = new ArrayList<>();

    /**
     * 代码总行数
     */
    private static int lines = 0;

    public static void main(String[] args) {
        //代码根目录
        String root = args[0];
        //忽略代码文件后缀 形如 .xxx
        String ignoreFileSuffix = args[1];
        //忽略目录
        String ignoreDirectory = args[2];
//        String root = "/Users/qiuliang/workspaces/nginx";
//        String ignoreFileSuffix = ".hgtags";
//        String ignoreDirectory = ".git";
        getCodeFilePath(root,ignoreDirectory);
        codeFilePath.forEach(file -> statisticsCodeNumber(new File(file),ignoreFileSuffix));
        System.out.println("code lines:"+ lines);
    }

    /**
     * 统计代码行数
     * @param file 代码文件
     * @param ignoreSuffix 忽略的代码文件后缀
     * @return
     */
    private static int statisticsCodeNumber(File file, String ignoreSuffix){
        System.out.println("file.getPath():"+file.getPath());
        if(ignoreSuffix != null && !"".equals(ignoreSuffix) && file.getName().endsWith(ignoreSuffix)){
            return lines;
        }
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis));
            while ((br.readLine())!= null) {
                lines++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis != null){
                    fis.close();
                }
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    /**
     * 生成代码文件路径列表
     * @param path 代码根目录
     * @param ignoreDirectory 忽略目录
     */
    public static void getCodeFilePath(String path, String ignoreDirectory) {
        File file = new File(path);
        File[] filesArr = file.listFiles();
        if (filesArr == null) {
            return;
        } else {
            for (File item : filesArr) {
                if (item.isDirectory() && ignoreDirectory != null && ignoreDirectory.equals(item.getName())) {
                    continue;
                } else if(item.isDirectory()){
                    getCodeFilePath(item.getPath(), ignoreDirectory);
                } else{
                    codeFilePath.add(item.getPath());
                }
            }
        }
    }
}
