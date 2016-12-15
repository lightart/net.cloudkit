import java.io.*;
import java.nio.charset.Charset;

public class FileTest {

    public static void reading() {
        try {
            // 创建文件输入流对象
            FileInputStream is = new FileInputStream("TestFile.txt");
            // 设定读取的字节数
            int n = 512;
            byte buffer[] = new byte[n];
            // 读取输入流
            while ((is.read(buffer, 0, n) != -1) && (n > 0)) {
                System.out.print(new String(buffer));
            }
            System.out.println();
            // 关闭输入流
            is.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writing() {
        try {
            System.out.print("输入要保存文件的内容：");
            int count, n = 512;
            byte buffer[] = new byte[n];
            // 读取标准输入流
            count = System.in.read(buffer);
            // 创建文件输出流对象
            FileOutputStream os = new FileOutputStream("WriteFile.txt");
            // 写入输出流
            os.write(buffer, 0, count);
            // 关闭输出流
            os.close();
            System.out.println("已保存到WriteFile.txt!");
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {

        /*
        InputStream is = Files.newInputStream(Paths.get("E:/20161104151834754715.20161104151834754715"), StandardOpenOption.READ);
        FileOutputStream fos = new FileOutputStream("E:/20161104151834754715.20161104151834754715.xml");

        // 设定读取的字节数
        byte[] buffer = new byte[1024];
        // ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取输入流
        while ((is.read(buffer, 0, buffer.length) != -1) && (buffer.length > 0)) {
            // System.out.print(new String(buffer));
            fos.write(buffer);
            // bf = ByteBuffer.wrap(buffer);
        }

        // 关闭输入流
        is.close();
        fos.close();

        // Base64Encoder().encode(buffer)

        // FileInputStream fis = new FileInputStream()
        // Files.newBufferedReader(Paths.get("E:/20161104151834754715.20161104151834754715"));
        */

        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        */

        File file = new File("E:/20161104151834754715.20161104151834754715");
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer1 = new byte[(int) file.length()];
        inputFile.read(buffer1);
        inputFile.close();

        System.out.println(new String(buffer1, Charset.forName(getCharset("E:/20161104151834754715.20161104151834754715"))));

        // System.out.println(getCharset("E:/20161104151834754715.20161104151834754715"));

        /*
        FileInputStream fInputStream = new FileInputStream(file);
        // code为上面方法里返回的编码方式
        InputStreamReader inputStreamReader = new InputStreamReader(fInputStream, code);
        BufferedReader in = new BufferedReader(inputStreamReader);

        String strTmp = "";
        // 按行读取
        while (( strTmp = in.readLine()) != null) {
            sBuffer.append(strTmp + "/n");
        }
        return sBuffer.toString();
        */
    }

    /**
     * 判断文件的编码格式
     * @param fileName
     * @return 文件编码格式
     * @throws Exception
     */
    public static String getCharset(String fileName) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String charset;
        switch (p) {
            case 0xefbb:
                charset = "UTF-8";
                break;
            case 0xfffe:
                charset = "Unicode";
                break;
            case 0xfeff:
                charset = "UTF-16BE";
                break;
            default:
                charset = "GBK";
        }
        return charset;
    }
}
