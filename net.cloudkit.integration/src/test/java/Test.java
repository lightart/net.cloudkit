import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2016/8/30.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("D:\\Local\\FTP\\DECL\\Download\\TCS300009141_131169456570239544_1"), Charset.defaultCharset());
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
