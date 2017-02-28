package net.cloudkit.dictionary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientTest {

    public static void main(String[] args) {

        while (true) {
            ParsingService parsingService = new ParsingService();
            List<Map<String, Object>> dictList = parsingService.getDictionaryList2();

            for (Map<String, Object> dictMap : dictList) {
                System.out.println("==================================================");

                // https://www.shanbay.com/bdc/vocabulary/1/
                // http://dict.cn/popular
                // http://www.iciba.com/period

                // http://dict.youdao.com/w/a/
                // http://dict.youdao.com/dictvoice?audio=abdomen&type=1
                // http://dict.youdao.com/dictvoice?audio=abdomen&type=2

                // String word = "abacus";
                String word = (String) dictMap.get("word");

                try {

                    // http://dict.youdao.com/w/
                    Document doc = Jsoup.connect("http://youdao.com/w/eng/" + word)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                        // .cookie("DICT_UGC", "1c4a57959489c909810f142b1751150c|123@126.com; domain=.youdao.com")
                        //
//                        .cookie("_ntes_nnid", "6600c803461f228b3afa612420546746,1487843452268")
//                        .cookie("OUTFOX_SEARCH_USER_ID_NCOO", "1983990659.9118056")
//                        .cookie("YOUDAO_EAD_UUID", "7a31e5c7-2ec7-44a4-904f-af098cd1d652")
//                        .cookie("tabRecord.authTrans", "#authDictTrans; search-popup-show=-1")
//                        .cookie("OUTFOX_SEARCH_USER_ID", "396130949@183.14.30.233")
//                        .cookie("JSESSIONID", "abc3Cnbju3eqC8-qnr9Pv")
//                        .cookie("DICT_UGC", "be3af0da19b5c5e6aa4e17bd8d90b28a|")
//                        .cookie("webDict_HdAD", "\"{\"req\":\"http://dict.youdao.com\",\"width\":960,\"height\":240,\"showtime\":5000,\"fadetime\":500,\"notShowInterval\":3,\"notShowInDays\":false,\"lastShowDate\":\"Mon Nov 08 2010\"}\"")
//                        .cookie("___rl__test__cookies", "1488168882179")
//                        .cookie("PICUGC_FLASH", "")
//                        .cookie("PICUGC_SESSION", "90fbe28fda40b5ff04f343a261160ce83d3439fc-")
                        .timeout(5000)
                        .get();

                    // pronounce
                    // String title = doc.title();
                    List<String> pronounceList = new ArrayList<>();
                    Elements pronounceElements = doc.select("div#phrsListTab span.pronounce");
                    // for (Element element:elements) {}
                    for (int i = 0; i < pronounceElements.size(); i++) {
                        System.out.println(pronounceElements.get(i).text());
                        pronounceList.add(pronounceElements.get(i).text());
                        if (i == 1) {
                            break;
                        }
                    }

           /*     // explain
                List<String> explainList = new ArrayList<>();
                Elements explainElements = doc.getElementsByClass("trans-container");
                for (int i = 0; i < explainElements.size(); i++) {
                    Elements explainElementItems = explainElements.get(i).getElementsByTag("li");
                    for (int j = 0; j < explainElementItems.size(); j++) {
                        System.out.println(explainElementItems.get(j).text());
                        explainList.add(explainElementItems.get(j).text());
                    }
                    if (i == 0) {
                        break;
                    }
                }

                // additional
                String additional = null;
                Elements additionalElements = doc.select("div#phrsListTab p.additional");
                for (int i = 0; i < additionalElements.size(); i++) {
                    System.out.println(additionalElements.get(i).text());
                    additional = additionalElements.get(i).text();
                    if (i == 0) {
                        break;
                    }
                }

                // voice
                List<String> voiceList = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    try {
                        URL url = new URL("http://dict.youdao.com/dictvoice?audio=" + word + "&type=" + i);
                        InputStream is = url.openStream();

                        ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
                        byte[] temp = new byte[1024];
                        int len = 0;
                        while ((len = is.read(temp, 0, 1024)) != -1) {
                            byteArrOut.write(temp, 0, len);
                        }
                        byteArrOut.flush();
                        byte[] bytes = byteArrOut.toByteArray();
                        System.out.println(new String(Base64Encrypt.encode(bytes)));
                        voiceList.add(new String(Base64Encrypt.encode(bytes)));

                        *//*
                        // write the inputStream to a FileOutputStream
                        FileOutputStream fos = new FileOutputStream(new File("D:/voices/" + word + "_" + i + ".mp3"));
                        #
                        int read = 0;
                        byte[] temps = new byte[1024];
                        while ((read = is.read(temps)) != -1) {
                            fos.write(temps, 0, read);
                        }
                        #
                        fos.write(bytes);
                        System.out.println("Done!");
                        fos.close();
                        *//*

                        is.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        // is.close();
                    }
                }

                *//*
                String pronounce = new ObjectMapper().writeValueAsString(pronounceList);
                String explain = new ObjectMapper().writeValueAsString(explainList);
                String voice = new ObjectMapper().writeValueAsString(voiceList);
                *//*

                String pronounce = listToString(pronounceList, ",");
                String explain = listToString(explainList, "<br />");
                String voice = listToString(voiceList, ";");

                System.out.println(pronounce);

                parsingService.updateDictionary(word, pronounce, explain, additional, voice);*/

                    String pronounce = listToString(pronounceList, ",");
                    parsingService.updateDictionary2(word, pronounce);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // break;

                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }
}
