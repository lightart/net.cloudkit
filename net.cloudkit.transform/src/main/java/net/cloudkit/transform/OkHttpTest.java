package net.cloudkit.transform;


import okhttp3.*;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class OkHttpTest {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {

        OkHttpTest okHttpTest = new OkHttpTest();
        try {

            // GZIP 压缩 http://commons.apache.org/proper/commons-compress/examples.html
            InputStream in = Files.newInputStream(Paths.get("F:\\SVN\\docs\\用户需求\\易航线\\主报文.xml"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // BZip2CompressorOutputStream bzOut = new BZip2CompressorOutputStream(baos);
            GzipCompressorOutputStream bzOut = new GzipCompressorOutputStream(baos);
            final byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buffer))) {
                bzOut.write(buffer, 0, n);
            }
            bzOut.close();
            in.close();

            String message = new String(Base64Encrypt.encode(baos.toByteArray()), "UTF-8");
            System.out.println(message);

            /*
            // 解压
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            // BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(new ByteArrayInputStream(baos.toByteArray()));
            GzipCompressorInputStream bzIn = new GzipCompressorInputStream(new ByteArrayInputStream(baos.toByteArray()));
            final byte[] buffer2 = new byte[1024];
            int c = 0;
            while (-1 != (c = bzIn.read(buffer2))) {
                baos2.write(buffer2, 0, c);
            }
            baos2.close();
            bzIn.close();
            System.out.println(new String(baos2.toByteArray(), "UTF-8"));
            */

            // Properties
            Map<String, String>  params1 = new HashMap<>();
            params1.put("clientPlatformCode", "20000");
            params1.put("key", "e5646b8d21d725da2qwefdhcjh81cc2575f9d67e");

            // 获取token
            String val = okHttpTest.post(
                "http://test.21eline.com:8030/api/get_token.html",
                params1
            );
            System.out.println(convertUnicode(val));


            // 上传报文
            Map<String, String>  params2 = new HashMap<>();
            params2.put("main_xml", message);

            String val2 = okHttpTest.post(
                "http://test.21eline.com:8030/api/bg_simple_validate.html",
                params2
            );
            System.out.println(convertUnicode(val2));

            // 查询报关单当前状态
            Map<String, String>  params3 = new HashMap<>();
            params3.put("clientCompanyCode", "20000");
            params3.put("taskId", "10001323231112311223138");
            params3.put("importExportFlag", "e");
            params3.put("clientPlatformCode", "20000");
            params3.put("token", "b3fefd259790d3a");

            String val3 = okHttpTest.post(
                "http://test.21eline.com:8030//api/bg_status.html",
                params3
            );
            System.out.println(convertUnicode(val3));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * get
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * post
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String post(String url, Map<String, String> params) throws IOException {
        // RequestBody body = RequestBody.create(JSON, params);

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for(Map.Entry<String, String> entry : params.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * Convert unicode
     *
     * @param ori
     * @return
     */
    public static String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }
}
