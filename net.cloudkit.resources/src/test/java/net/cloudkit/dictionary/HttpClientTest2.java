package net.cloudkit.dictionary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientTest2 {

    public static void main(String[] args) {

        ParsingService parsingService = new ParsingService();
        List<Map<String, Object>> dictList = parsingService.getDictionaryList();


        // 169999
        // https://www.shanbay.com/bdc/vocabulary/1/

        int a = 1;
        while (true) {

            try {
                Document doc = Jsoup.connect("https://www.shanbay.com/bdc/vocabulary/" + a + "/")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                    .timeout(3000).get();

                String word = null;
                Elements wordElements = doc.select("div#learning_word div.word h1.content");
                for (int i = 0; i < wordElements.size(); i++) {
                    System.out.println(wordElements.get(i).ownText());
                    word = wordElements.get(i).ownText();
                }

                List<String> voiceList = new ArrayList<>();
                Elements voiceUKElements = doc.select("span.audio.uk");
                for (int i = 0; i < voiceUKElements.size(); i++) {
                    String voiceURI = voiceUKElements.get(i).attr("data");
                    System.out.println(voiceUKElements.get(i).attr("data"));

                    try {
                        URL url = new URL(voiceURI);
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
                        is.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        // is.close();
                    }
                }


                Elements voiceUSElements = doc.select("span.audio.us");
                for (int i = 0; i < voiceUSElements.size(); i++) {
                    String voiceURI = voiceUKElements.get(i).attr("data");
                    System.out.println(voiceUKElements.get(i).attr("data"));

                    try {
                        URL url = new URL(voiceURI);
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
                        is.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        // is.close();
                    }
                }

                List<String> explainList = new ArrayList<>();
                Elements explainElements = doc.select("div#learning_word div.word div.cndf span.text");
                for (int i = 0; i < explainElements.size(); i++) {
                    System.out.println(explainElements.get(i).text());
                    explainList.add(explainElements.get(i).text());
                }

//            List<String> deList = new ArrayList<>();
//            Elements deElements = doc.select("div#learning_word div.word span.part-of-speech, div#learning_word li.definition span.content");
//            for (int i = 0; i < deElements.size(); i++) {
//                System.out.println(deElements.get(i).text());
//            }

                List<String> deList = new ArrayList<>();
                StringBuffer deStringBuffer = new StringBuffer();
                Elements deElements = doc.select("div#learning_word div#review-definitions div.row div, div#learning_word div#review-definitions div.row ol");
                for (int i = 0; i < deElements.size(); i++) {
                    String key = deElements.get(i).select("span.part-of-speech").text();
                    if (!"".equals(key)) {
                        deStringBuffer.append(key);
                    }
                    Elements contentElements = deElements.get(i).select("span.content");
                    for (int j = 0; j < contentElements.size(); j++) {
                        String value = contentElements.get(j).text();
                        if (!"".equals(value)) {
                            deStringBuffer.append("<br />");
                            deStringBuffer.append(value);
                            deList.add(deStringBuffer.toString());
                            System.out.println(deStringBuffer.toString());
                            deStringBuffer = new StringBuffer();
                        }
                    }

                }

                List<String> esSysList = new ArrayList<>();
                Elements exSysElements = doc.select("div#learning-examples-box div#ex-sys-box li.row");
                for (int i = 0; i < exSysElements.size(); i++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(exSysElements.get(i).select("div.index").text());
                    sb.append(". ");
                    sb.append(exSysElements.get(i).select("div.enex").text());
                    sb.append("<br />");
                    sb.append(exSysElements.get(i).select("div.cnex").text());
                    System.out.println(sb.toString());
                    esSysList.add(sb.toString());
                }

                List<String> exUserList = new ArrayList<>();
                Elements exUserElements = doc.select("div#learning-examples-box div#ex-user-box li.row");
                for (int i = 0; i < exUserElements.size(); i++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(exUserElements.get(i).select("div.index").text());
                    sb.append(". ");
                    sb.append(exUserElements.get(i).select("div.enex").text());
                    sb.append("<br />");
                    sb.append(exUserElements.get(i).select("div.cnex").text());
                    System.out.println(sb.toString());
                    exUserList.add(sb.toString());
                }

                List<String> noteList = new ArrayList<>();
                Elements noteBoxElements = doc.select("div#notes-box div#note-user-box li.row");
                for (int i = 0; i < noteBoxElements.size(); i++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(noteBoxElements.get(i).select("div.index").text());
                    sb.append(". ");
                    sb.append(noteBoxElements.get(i).select("div.content span").text());
                    System.out.println(sb.toString());
                    noteList.add(sb.toString());
                }

                String definitions = listToString(deList, "<br />");
                String explain = listToString(explainList, "<br />");
                String exempli_gratia_sys = listToString(esSysList, "<br />");
                String exempli_gratia_user = listToString(exUserList, "<br />");
                String notes = listToString(noteList, "<br />");
                String voices = listToString(voiceList, ";");
                parsingService.insertDict(word, null, definitions, explain, exempli_gratia_sys, exempli_gratia_user, notes, voices);

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.err.println(a + "-----------------------------------------------");
            a++;
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
