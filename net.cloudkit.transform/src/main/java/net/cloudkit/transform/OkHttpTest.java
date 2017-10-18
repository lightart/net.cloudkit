/*
 * Copyright (C) 2015 The CloudKit Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.cloudkit.transform;

import okhttp3.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Apache HttpComponents http://hc.apache.org/
 * OkHttp http://square.github.io/okhttp/
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2017-07-19 10:11:44
 */
public class OkHttpTest {

    // public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {

        OkHttpTest okHttpTest = new OkHttpTest();
        try {

//            // TODO 获取token
//            // Properties
//            Map<String, String>  params1 = new HashMap<>();
//            params1.put("clientPlatformCode", "20000");
//            params1.put("key", "e5646b8d21d725da2qwefdhcjh81cc2575f9d67e");
//
//            String val = okHttpTest.post(
//                "http://test.21eline.com:8030/api/get_token.html",
//                params1
//            );
//            System.out.println(convertUnicode(val));
//
//            // TODO 上传报文
//            // GZIP 压缩 http://commons.apache.org/proper/commons-compress/examples.html
//            InputStream in = Files.newInputStream(Paths.get("F:\\SVN\\docs\\用户需求\\易航线\\主报文.xml"));
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            // BZip2CompressorOutputStream bzOut = new BZip2CompressorOutputStream(baos);
//            GzipCompressorOutputStream bzOut = new GzipCompressorOutputStream(baos);
//            final byte[] buffer = new byte[1024];
//            int n = 0;
//            while (-1 != (n = in.read(buffer))) {
//                bzOut.write(buffer, 0, n);
//            }
//            bzOut.close();
//            in.close();
//
//            String message = new String(Base64Encrypt.encode(baos.toByteArray()), "UTF-8");
//            System.out.println(message);
//
//            Map<String, String>  params2 = new HashMap<>();
//            params2.put("main_xml", message);
//
//            String val2 = okHttpTest.post(
//                "http://test.21eline.com:8030/api/bg_simple_validate.html",
//                params2
//            );
//            System.out.println(convertUnicode(val2));
//
//            // TODO 查询报关单当前状态
//            Map<String, String>  params3 = new HashMap<>();
//            params3.put("clientCompanyCode", "test");
//            params3.put("taskId", "10001323231112311223138");
//            params3.put("importExportFlag", "e");
//            params3.put("clientPlatformCode", "20000");
//            params3.put("token", "f9f91871d91a8aa");
//
//            String val3 = okHttpTest.post(
//                "http://test.21eline.com:8030/api/bg_status.html",
//                params3
//            );
//            System.out.println(convertUnicode(val3));
//
//            // TODO 查询已上传的报关单数据
//            Map<String, String>  params4 = new HashMap<>();
//            params4.put("clientCompanyCode", "test");
//            params4.put("taskId", "10001323231112311223138");
//            params4.put("importExportFlag", "e");
//            params4.put("clientPlatformCode", "20000");
//            params4.put("token", "f9f91871d91a8aa");
//
//            String val4 = okHttpTest.post(
//                "http://test.21eline.com:8030/api/get_bg_main.html",
//                params4
//            );
//            System.out.println(convertUnicode(val4));
//
//            // 解压
//            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
//            GzipCompressorInputStream bzIn = new GzipCompressorInputStream(new ByteArrayInputStream(Base64Encrypt.decode(val4.getBytes())));
//            final byte[] buffer2 = new byte[1024];
//            int c = 0;
//            while (-1 != (c = bzIn.read(buffer2))) {
//                baos2.write(buffer2, 0, c);
//            }
//            baos2.close();
//            bzIn.close();
//            System.out.println(new String(baos2.toByteArray(), "UTF-8"));


            InputStream in = Files.newInputStream(Paths.get("F:\\SVN\\docs\\用户需求\\中外运\\报关单导入.xml"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buffer))) {
                baos.write(buffer, 0, n);
            }
            baos.close();
            in.close();

            String message = new String(Base64Encrypt.encode(baos.toByteArray()), "UTF-8");
            System.out.println(message);
            // String m = new String(Base64Encrypt.decode(message.getBytes()), "UTF-8");
            // System.out.println(m);

            Map<String, String> params1 = new HashMap<>();
            params1.put("Sender", "Guangmaoyun");
            params1.put("Receiver", "Sino");
            params1.put("MessageType", "xml_BGD");
            params1.put("Version", "");
            params1.put("DocumentId", "");
            params1.put("BizKey", "");
            params1.put("BatchNo", "");
            params1.put("MessageStatus", "");
            params1.put("ContentType", "");
            params1.put("Content", message);

            String val = okHttpTest.post(
                "http://gatewaytest.transgd.com.cn:5656/invoke/LL_SZWLC_GW.customsDec.http/receive",
                params1
            );
            System.out.println(convertUnicode(val));
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
        // MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            /**
             // MultipartBody
             .addPart(
             Headers.of("Content-Disposition", "form-data; name=\"file\"; filename=\"" + fileName + "\""),
             RequestBody.create(MEDIA_TYPE_PNG, file)
             )
             .addPart(
             Headers.of("Content-Disposition", "form-data; name=\"imagetype\""),
             RequestBody.create(null, imageType)
             )
             .addPart(
             Headers.of("Content-Disposition", "form-data; name=\"userphone\""),
             RequestBody.create(null, userPhone)
             )
             builder.addFormDataPart(entry.getKey(), entry.getValue());
             */

            // FormBody
            builder.add(entry.getKey(), entry.getValue());
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
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }

        }
        return outBuffer.toString();
    }
}
