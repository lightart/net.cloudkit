package singlewindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtils {

    public static String readTxtFile(String filePath) {
        StringBuffer txt = new StringBuffer();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            txt.append(readTxtFile(file, encoding));
        } catch (Exception e) {
            //System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return txt.toString();
    }

    public static String readTxtFile(File file, String encoding) {
        StringBuffer txt = new StringBuffer();
        try {
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    txt.append(lineTxt);
                }
                read.close();
            } else {
                //System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {

        }
        return txt.toString();
    }
}
