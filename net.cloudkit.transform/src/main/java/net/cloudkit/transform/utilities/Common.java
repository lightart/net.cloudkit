package net.cloudkit.transform.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DBUtil.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2016年12月27日 上午11:38:34
 */
public class Common {

//    public static void moveHisPath(File file, String historyPath) {
//        Path moveHisPath = Paths.get(historyPath + File.separator + new SimpleDateFormat("yyyy_MM_dd/HH/mm").format(new Date()) + File.separator + file.getName());
//        try {
//            if (Files.notExists(moveHisPath)) {
//                Files.createDirectories(moveHisPath);
//            }
//            Files.move(Paths.get(file.getAbsolutePath()), moveHisPath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//    }

    public static void movePath(File file, String targetPath) {
        Path movePath = Paths.get(targetPath + File.separator + new SimpleDateFormat("yyyy_MM_dd/HH/mm").format(new Date()) + File.separator + file.getName());
        try {
            if (Files.notExists(movePath)) {
                Files.createDirectories(movePath);
            }
            Files.move(Paths.get(file.getAbsolutePath()), movePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
