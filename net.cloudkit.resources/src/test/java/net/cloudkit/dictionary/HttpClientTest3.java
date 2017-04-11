package net.cloudkit.dictionary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientTest3 {

    public static void main(String[] args) {

        while (true) {
            ParsingService parsingService = new ParsingService();
            List<Map<String, Object>> dictList = parsingService.getDictList();

            for (Map<String, Object> dictMap : dictList) {
                System.out.println("==================================================");

                String word = (String) dictMap.get("word");

                try {

                    // http://dict.youdao.com/w/
                    Document doc = Jsoup.connect("http://dict.cn/" + word)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
//                        .timeout(5000)
                        .get();

                    // pronounce
                    List<String> pronounceList = new ArrayList<>();
                    Elements pronounceElements = doc.select("div.phonetic bdo[lang=EN-US]");
                    // for (Element element:elements) {}
                    for (int i = 0; i < pronounceElements.size(); i++) {
                        // System.out.println(pronounceElements.get(i).text());
                        pronounceList.add(pronounceElements.get(i).text());
                        if (i == 1) {
                            break;
                        }
                    }
                    String pronounce = new ObjectMapper().writeValueAsString(pronounceList);
                    // System.out.println(pronounce);

                    // 详尽释义
                    Elements detailElements = doc.select("div.layout.detail");
                    List<Map<String, Object>> details = new ArrayList<>();
                    for (int i = 0; i < detailElements.size(); i++) {
                        Elements detailSpanElements = detailElements.get(i).select("span");
                        Elements detailOlElements = detailElements.get(i).select("ol");
                        for (int j = 0; j < detailSpanElements.size(); j++) {
                            Map<String, Object> detailMap = new HashMap<>();
                            // System.out.println(detailSpanElements.get(j).text());
                            detailMap.put("#1", detailSpanElements.get(j).text());

                            Elements detailListElements = detailOlElements.get(j).select("li");
                            List<String> detailList = new ArrayList<>();
                            for (int k = 0; k < detailListElements.size(); k++) {
                                // System.out.println(detailListElements.get(k).text());
                                detailList.add(detailListElements.get(k).text());
                            }
                            detailMap.put("#2", detailList);
                            details.add(detailMap);
                        }
                    }
                    String detail = new ObjectMapper().writeValueAsString(details);
                    System.out.println(detail);

                    // 双解释义
                    Elements dualElements = doc.select("div.layout.dual");
                    List<Map<String, Object>> duals = new ArrayList<>();
                    for (int i = 0; i < dualElements.size(); i++) {
                        Elements dualSpanElements = dualElements.get(i).select("span");
                        Elements dualOlElements = dualElements.get(i).select("ol");
                        for (int j = 0; j < dualSpanElements.size(); j++) {
                            Map<String, Object> dualMap = new HashMap<>();
                            // System.out.println(dualSpanElements.get(j).text());
                            dualMap.put("#1", dualSpanElements.get(j).text());

                            Elements dualListElements = dualOlElements.get(j).select("li");
                            List<String> dualList = new ArrayList<>();
                            for (int k = 0; k < dualListElements.size(); k++) {
                                // System.out.println(dualListElements.get(k).text());
                                dualList.add(dualListElements.get(k).text());
                            }
                            dualMap.put("#2", dualList);
                            duals.add(dualMap);
                        }
                    }
                    String dual = new ObjectMapper().writeValueAsString(duals);
                    System.out.println(dual);


                    // 英英释义
                    Elements enElements = doc.select("div.layout.en");
                    List<Map<String, Object>> ens = new ArrayList<>();
                    for (int i = 0; i < enElements.size(); i++) {
                        Elements enSpanElements = enElements.get(i).select("span");
                        Elements enOlElements = enElements.get(i).select("ol");
                        for (int j = 0; j < enSpanElements.size(); j++) {
                            Map<String, Object> enMap = new HashMap<>();
                            // System.out.println(enSpanElements.get(j).text());
                            enMap.put("#1", enSpanElements.get(j).text());

                            Elements enListElements = enOlElements.get(j).select("li");
                            List<String> enList = new ArrayList<>();
                            for (int k = 0; k < enListElements.size(); k++) {
                                // System.out.println(enListElements.get(k).text());
                                enList.add(enListElements.get(k).text());
                            }
                            enMap.put("#2", enList);
                            ens.add(enMap);
                        }
                    }
                    String en = new ObjectMapper().writeValueAsString(ens);
                    System.out.println(en);

                    // 发音
                    Map<String, String> voiceMap = new HashMap<>();
                    Elements femaleVoiceElements = doc.select("div.phonetic i.sound[title=女生版发音]");
                    for (int i = 0; i < femaleVoiceElements.size(); i++) {
                        // System.out.println(femaleVoiceElements.get(i).attr("naudio"));
                        if (i == 0) {
                            voiceMap.put("#UK_FEMALE", "http://audio.dict.cn/" + femaleVoiceElements.get(i).attr("naudio"));
                        } else {
                            voiceMap.put("#US_FEMALE", "http://audio.dict.cn/" + femaleVoiceElements.get(i).attr("naudio"));
                        }
                    }
                    Elements maleVoiceElements = doc.select("div.phonetic i.sound[title=男生版发音]");
                    for (int i = 0; i < maleVoiceElements.size(); i++) {
                        // System.out.println(maleVoiceElements.get(i).attr("naudio"));

                        if (i == 0) {
                            voiceMap.put("#UK_MALE", "http://audio.dict.cn/" + femaleVoiceElements.get(i).attr("naudio"));
                        } else {
                            voiceMap.put("#US_MALE", "http://audio.dict.cn/" + femaleVoiceElements.get(i).attr("naudio"));
                        }
                    }

                    // voice
                    Map<String, String> voices = new HashMap<>();
                    for (Map.Entry<String, String> entry : voiceMap.entrySet()) {

                        try {
                            URL url = new URL(entry.getValue());
                            InputStream is = url.openStream();

                            ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
                            byte[] temp = new byte[1024];
                            int len = 0;
                            while ((len = is.read(temp, 0, 1024)) != -1) {
                                byteArrOut.write(temp, 0, len);
                            }
                            byteArrOut.flush();
                            byte[] bytes = byteArrOut.toByteArray();

                            // System.out.println(entry.getValue());
                            // System.out.println(new String(Base64Encrypt.encode(bytes)));
                            voices.put(entry.getKey(), new String(Base64Encrypt.encode(bytes)));

                            is.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            // is.close();
                        }
                    }
                    String voice = new ObjectMapper().writeValueAsString(voices);
                    System.out.println(voice);

                    // explain
                    Elements explainElements = doc.select("ul.dict-basic-ul li");
                    StringBuffer explainStringBuffer = new StringBuffer();
                    for (int i = 0; i < explainElements.size(); i++) {
                        explainStringBuffer.append(explainElements.get(i).select("span").text());
                        explainStringBuffer.append(explainElements.get(i).select("strong").text());
                        explainStringBuffer.append("<br />");
                    }
                    String explain = explainStringBuffer.length() >= 6 ? explainStringBuffer.toString().substring(0, explainStringBuffer.length() - 6) : explainStringBuffer.toString();
                    System.out.println(explain);


                    // 例句
                    Elements sentenceElements = doc.select("div.layout.sort li");
                    List<Map<String, String>> sentenceList = new ArrayList<>();
                    for (int i = 0; i < sentenceElements.size(); i++) {
                        Map<String, String> sentenceMap = new HashMap<>();
                        // System.out.println(sentenceElements.get(i).text());
                        sentenceMap.put("#SENTENCE", sentenceElements.get(i).text());

                        Elements sentenceFemaleVoiceElements = sentenceElements.get(i).select("i.sound[title=女生版读音]");
                        for (int j = 0; j < sentenceFemaleVoiceElements.size(); j++) {
                            // System.out.println("http://audio.dict.cn/" + sentenceFemaleVoiceElements.get(j).attr("naudio"));
                            // TODO
                            sentenceMap.put("#FEMALE_AUDIO", voiceBase64("http://audio.dict.cn/" + sentenceFemaleVoiceElements.get(j).attr("naudio")));
                        }

                        Elements sentenceMaleVoiceElements = sentenceElements.get(i).select("i.sound[title=男生版读音]");
                        for (int j = 0; j < sentenceMaleVoiceElements.size(); j++) {
                            // System.out.println("http://audio.dict.cn/" + sentenceMaleVoiceElements.get(j).attr("naudio"));
                            // TODO
                            sentenceMap.put("#MALE_AUDIO", voiceBase64("http://audio.dict.cn/" + sentenceMaleVoiceElements.get(j).attr("naudio")));
                        }
                        sentenceList.add(sentenceMap);
                    }
                    String sentence = new ObjectMapper().writeValueAsString(sentenceList);
                    System.out.println(sentence);

                    // 常见句型
                    Elements pattElements = doc.select("div.layout.patt li");
                    List<Map<String, String>> pattList = new ArrayList<>();
                    for (int i = 0; i < pattElements.size(); i++) {
                        Map<String, String> pattMap = new HashMap<>();
                        // System.out.println(pattElements.get(i).text());
                        pattMap.put("#PATT", pattElements.get(i).text());

                        Elements sentenceFemaleVoiceElements = pattElements.get(i).select("i.sound[title=女生版读音]");
                        for (int j = 0; j < sentenceFemaleVoiceElements.size(); j++) {
                            // System.out.println("http://audio.dict.cn/" + sentenceFemaleVoiceElements.get(j).attr("naudio"));
                            pattMap.put("#FEMALE_AUDIO", voiceBase64("http://audio.dict.cn/" + sentenceFemaleVoiceElements.get(j).attr("naudio")));
                        }

                        Elements sentenceMaleVoiceElements = pattElements.get(i).select("i.sound[title=男生版读音]");
                        for (int j = 0; j < sentenceMaleVoiceElements.size(); j++) {
                            // System.out.println("http://audio.dict.cn/" + sentenceMaleVoiceElements.get(j).attr("naudio"));
                            pattMap.put("#MALE_AUDIO", voiceBase64("http://audio.dict.cn/" + sentenceMaleVoiceElements.get(j).attr("naudio")));
                        }
                        pattList.add(pattMap);
                    }
                    String patt = new ObjectMapper().writeValueAsString(pattList);
                    System.out.println(patt);

                    // 常用短语
                    Elements phraseElements = doc.select("div.layout.phrase li");
                    List<Map<String, String>> phraseList = new ArrayList<>();
                    for (int i = 0; i < phraseElements.size(); i++) {
                        Map<String, String> phraseMap = new HashMap<>();
                        // System.out.println(phraseElements.get(i).text());
                        phraseMap.put("#PHRASE", phraseElements.get(i).text());

                        Elements sentenceFemaleVoiceElements = phraseElements.get(i).select("i.sound[title=女生版读音]");
                        for (int j = 0; j < sentenceFemaleVoiceElements.size(); j++) {
                            System.out.println("http://audio.dict.cn/" + sentenceFemaleVoiceElements.get(j).attr("naudio"));
                            phraseMap.put("#FEMALE_AUDIO", voiceBase64("http://audio.dict.cn/" + sentenceFemaleVoiceElements.get(j).attr("naudio")));
                        }

                        Elements sentenceMaleVoiceElements = phraseElements.get(i).select("i.sound[title=男生版读音]");
                        for (int j = 0; j < sentenceMaleVoiceElements.size(); j++) {
                            System.out.println("http://audio.dict.cn/" + sentenceMaleVoiceElements.get(j).attr("naudio"));
                            phraseMap.put("#MALE_AUDIO", voiceBase64("http://audio.dict.cn/" + sentenceMaleVoiceElements.get(j).attr("naudio")));
                        }
                        phraseList.add(phraseMap);
                    }
                    String phrase = new ObjectMapper().writeValueAsString(phraseList);
                    System.out.println(phrase);

                    // 词汇搭配
                    Elements coll1Elements = doc.select("div.layout.coll > b");
                    Elements coll2Elements = doc.select("div.layout.coll ul");
                    Map<String, Object> collMap = new HashMap<>();
                    for (int i = 0; i < coll1Elements.size(); i++) {
                        // System.out.println(coll1Elements.get(i).text());
                        collMap.put("#COLL", coll1Elements.get(i).text());
                        Elements collliElements = coll2Elements.get(i).select("li");
                        List<String> collList = new ArrayList<>();
                        for (int j = 0; j < collliElements.size(); j++) {
                            // System.out.println(collliElements.get(j).text());
                            collList.add(collliElements.get(j).text());
                        }
                        collMap.put("#LIST", collList);
                    }
                    String coll = new ObjectMapper().writeValueAsString(collMap);
                    System.out.println(coll);

                    // 经典引文
                    Elements authElements = doc.select("div.layout.auth li");
                    List<Map<String, String>> authList = new ArrayList<>();
                    for (int i = 0; i < authElements.size(); i++) {
                        Map<String, String> authMap = new HashMap<>();
                        // System.out.println(authElements.get(i).select("p").text());
                        authMap.put("#TEXT", authElements.get(i).select("p").text());
                        authMap.put("#LINK", authElements.get(i).select("b").text());
                        authList.add(authMap);
                    }
                    String auth = new ObjectMapper().writeValueAsString(authList);
                    System.out.println(auth);

                    // 词语用法
                    Elements essElements = doc.select("div.layout.ess");
                    List<Map<String, Object>> essList = new ArrayList<>();
                    for (int i = 0; i < essElements.size(); i++) {
                        Map<String, Object> essMap = new HashMap<>();

                        Elements essSpanElements = essElements.get(i).select("span");
                        Elements essOlElements = essElements.get(i).select("ol");
                        for (int j = 0; j < essOlElements.size(); j++) {
                            if (j > 0) {
                                // System.out.println(essSpanElements.get(j - 1).text());
                                essMap.put("#TITLE", essSpanElements.get(j - 1).text());
                            }
                            Elements essLiElements = essOlElements.get(j).select("li");
                            List<String> essLiList = new ArrayList<>();
                            for (int k = 0; k < essLiElements.size(); k++) {
                                // System.out.println(essLiElements.get(k).text());
                                essLiList.add(essLiElements.get(k).text());
                            }
                            essMap.put("#LIST", essLiList);
                        }
                        essList.add(essMap);
                    }
                    String ess = new ObjectMapper().writeValueAsString(essList);
                    System.out.println(ess);


                    // 词义辨析
                    Elements discrimElements = doc.select("div.layout.discrim dl");
                    List<Map<String, Object>> discrimList = new ArrayList<>();
                    for (int i = 0; i < discrimElements.size(); i++) {
                        Elements discrimDtElements = discrimElements.get(i).select("dt");
                        Elements discrimDdDivElements = discrimElements.get(i).select("dd div");

                        Map<String, Object> discrimMap = new HashMap<>();

                        // System.out.println(discrimDtElements.text());
                        discrimMap.put("#TITLE", discrimDtElements.text());

                        List<String> discrimDdList = new ArrayList<>();
                        for (int j = 0; j < discrimDdDivElements.size(); j++) {
                            // System.out.println(discrimDdDivElements.get(j).text());
                            discrimDdList.add(discrimDdDivElements.get(j).text());
                        }
                        discrimMap.put("#LIST", discrimDdList);
                        discrimList.add(discrimMap);
                    }
                    String discrim = new ObjectMapper().writeValueAsString(discrimList);
                    System.out.println(discrim);

                    // 常见错误
                    Elements comnElements = doc.select("div.layout.comn");
                    List<Map<String, Object>> comnList = new ArrayList<>();
                    for (int i = 0; i < comnElements.size(); i++) {
                        Elements comnDivElements = doc.select("div.layout.comn div");
                        Elements comnUlElements = comnElements.get(i).select("ul");

                        for (int j = 0; j < comnUlElements.size(); j++) {
                            Map<String, Object> comnMap = new HashMap<>();
                            // System.out.println(comnDivElements.get(j).text());
                            comnMap.put("#TITLE", comnDivElements.get(j).text());
                            Elements comnUlLiElements = comnUlElements.get(j).select("li p");
                            List<String> comnLiPList = new ArrayList<>();
                            for (int k = 0; k < comnUlLiElements.size(); k++) {
                                // System.out.println(comnUlLiElements.get(k).text());
                                comnLiPList.add(comnUlLiElements.get(k).text());
                            }
                            comnMap.put("#LIST", comnLiPList);
                            comnList.add(comnMap);
                        }
                    }
                    String comn = new ObjectMapper().writeValueAsString(comnList);
                    System.out.println(comn);

                    // 词源解说
                    Elements etmElements = doc.select("div.layout.etm ul li");
                    List<String> etmList = new ArrayList<>();
                    for (int i = 0; i < etmElements.size(); i++) {
                        // System.out.println(etmElements.get(i).text());
                        etmList.add(etmElements.get(i).text());
                    }
                    String etm = new ObjectMapper().writeValueAsString(etmList);
                    System.out.println(etm);

                    // 近反义词
                    Elements nfwElements = doc.select("div.layout.nfw");
                    List<Map<String, Object>> nfwList = new ArrayList<>();
                    for (int i = 0; i < nfwElements.size(); i++) {
                        Elements nfwDivElements = nfwElements.get(i).select(">div");
                        Elements nfwUlElements = nfwElements.get(i).select("ul");

                        for (int j = 0; j < nfwUlElements.size(); j++) {
                            Map<String, Object> nfwMap = new HashMap<>();
                            // System.out.println(nfwDivElements.get(j).text());

                            Elements nfwUlSpanLiElements = nfwUlElements.get(j).select("li span");
                            // System.out.println(nfwUlSpanLiElements.text());

                            nfwMap.put("#TITLE", nfwDivElements.get(j).text() + "#" + nfwUlSpanLiElements.text());
                            Elements nfwUlLiAElements = nfwUlElements.get(j).select("li a");

                            List<String> nfwLiAList = new ArrayList<>();
                            for (int k = 0; k < nfwUlLiAElements.size(); k++) {
                                // System.out.println(nfwUlLiAElements.get(k).text());
                                nfwLiAList.add(nfwUlLiAElements.get(k).text());
                            }
                            nfwMap.put("#LIST", nfwLiAList);
                            nfwList.add(nfwMap);
                        }
                    }
                    String nfw = new ObjectMapper().writeValueAsString(nfwList);
                    System.out.println(nfw);
//
//                    // 派生词
//                    // 缩略词
//
                    // 临近单词
                    Elements nwdElements = doc.select("div.layout.nwd a");
                    List<String> nwdList = new ArrayList<>();
                    for (int i = 0; i < nwdElements.size(); i++) {
                        // System.out.println(nwdElements.get(i).text());
                        nwdList.add(nwdElements.get(i).text());
                    }
                    String nwd = new ObjectMapper().writeValueAsString(nwdList);
                    System.out.println(nwd);

                    parsingService.insertDict2(word, pronounce, detail, dual, en, voice, explain,
                        sentence, patt, phrase, coll, auth, ess,
                        discrim, comn, etm, nfw, nwd);

                } catch (IOException e) {
                    e.printStackTrace();
                }

//                try {
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
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

    public static String voiceBase64(String uri) {
        // voice

        String result = "#ERROR";
        try {
            URL url = new URL(uri);
            InputStream is = url.openStream();

            ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            int len = 0;
            while ((len = is.read(temp, 0, 1024)) != -1) {
                byteArrOut.write(temp, 0, len);
            }
            byteArrOut.flush();
            byte[] bytes = byteArrOut.toByteArray();

            // System.out.println(entry.getValue());
            // System.out.println(new String(Base64Encrypt.encode(bytes)));
            result = new String(Base64Encrypt.encode(bytes));

            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // is.close();
        }
        return result;
    }
}
