package net.cloudkit.transform;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/8.
 */
public class XFileSearchTest {
    public static void main(String[] args) {

        try {
            Path searchPath = Paths.get("E:/DECL");
            if (Files.notExists(searchPath)) {
                Files.createDirectories(searchPath);
            }

            // 遍历回执文件夹
            File[] files = searchPath.toFile().listFiles();
            for (File file : files) {
                // 文件目录则退出
                if (file.isDirectory()) {
                    continue;
                }
                // 读取回执文件内容
                StringBuffer data = new StringBuffer("");
                BufferedReader reader = null;
                String line;
                try {
                    reader = Files.newBufferedReader(file.toPath(), Charset.forName("GBK"));
                    while ((line = reader.readLine()) != null) {

                        // 8766
                        if(line.contains("<CHANNEL>R</CHANNEL>")) {
                            Path pathTo = Paths.get(searchPath + "_TEMP" + File.separator + file.getName());
                            if (Files.notExists(pathTo)) {
                                Files.createDirectories(pathTo);
                            }
                            Files.move(Paths.get(file.getAbsolutePath()), pathTo, StandardCopyOption.REPLACE_EXISTING);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    Path pathTo = Paths.get(searchPath + "_FIELD" + File.separator + file.getName());
//                    if (Files.notExists(pathTo)) {
//                        Files.createDirectories(pathTo);
//                    }
//                    Files.move(Paths.get(file.getAbsolutePath()), pathTo, StandardCopyOption.REPLACE_EXISTING);
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
