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

        ParsingService parsingService = new ParsingService();
        List<Map<String, Object>> dictList = parsingService.getDictionaryList();

        for (Map<String, Object> dictMap : dictList) {

            // https://www.shanbay.com/bdc/vocabulary/1/

            // http://dict.youdao.com/w/a/
            // http://dict.youdao.com/dictvoice?audio=abdomen&type=1
            // http://dict.youdao.com/dictvoice?audio=abdomen&type=2

            // String word = "abacus";
            String word = (String) dictMap.get("word");

            try {

                Document doc = Jsoup.connect("http://dict.youdao.com/w/" + word)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                    .cookie("DICT_UGC", "1c4a57959489c909810f142b1751150c|hongquanli@126.com; domain=.youdao.com")
                    .timeout(3000).get();

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

                // explain
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

                        // write the inputStream to a FileOutputStream
                        FileOutputStream fos = new FileOutputStream(new File("D:/voices/" + word + "_" + i + ".mp3"));
                        /*
                        int read = 0;
                        byte[] temps = new byte[1024];
                        while ((read = is.read(temps)) != -1) {
                            fos.write(temps, 0, read);
                        }
                        */
                        fos.write(bytes);
                        System.out.println("Done!");
                        fos.close();

                        is.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        // is.close();
                    }
                }

                /*
                String pronounce = new ObjectMapper().writeValueAsString(pronounceList);
                String explain = new ObjectMapper().writeValueAsString(explainList);
                String voice = new ObjectMapper().writeValueAsString(voiceList);
                */

                String pronounce = listToString(pronounceList, ",");
                String explain = listToString(explainList, "<br />");
                String voice = listToString(voiceList, ";");

                System.out.println(pronounce);

                parsingService.updateDictionary(word, pronounce, explain, additional, voice);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // break;
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
