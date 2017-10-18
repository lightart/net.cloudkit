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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Apache HttpComponents http://hc.apache.org/
 * OkHttp http://square.github.io/okhttp/
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2017-07-19 10:11:44
 */
public class SWTest {

    // public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {

        SWTest okHttpTest = new SWTest();
        try {

            // Properties
            Map<String, String> params1 = new HashMap<>(8);
            params1.put("userId", "");
            params1.put("platId", "");
            params1.put("msgType", "");
            params1.put("functionCode", "");
            params1.put("format", "");
            params1.put("compress", "");
            params1.put("signature", "");
            params1.put("data", "");

            String val = okHttpTest.post(
                "https://snapptest.singlewindow.cn/vplatformserver/swProxy/decserver/rest/dec/cusDeclare",
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
