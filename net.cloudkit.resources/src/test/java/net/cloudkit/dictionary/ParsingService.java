package net.cloudkit.dictionary;

import java.util.List;
import java.util.Map;

public class ParsingService {

    public List<Map<String, Object>> getDictionaryList() {
        String sql = "SELECT d.word FROM dictionary d WHERE d.phonetic_voice IS NULL";
        List<Map<String, Object>> results = DBUtil.query(sql);
        return results;
    }

    public boolean updateDictionary(String word, String pronounce, String explainText, String associateAdditional, String phonetic_voice) {
        String sql = "UPDATE dictionary d SET d.pronounce = ?, d.explain_text = ?, d.associate_additional = ?, d.phonetic_voice = ? WHERE d.word = ?";
        int affectCount = DBUtil.execute(sql, pronounce, explainText, associateAdditional, phonetic_voice, word);
        return ((affectCount > 0)? true : false);
    }

    public List<Map<String, Object>> getDictList() {
        String sql = "SELECT d.word FROM dict_temp d WHERE d.word NOT LIKE '% %' AND d.word NOT LIKE '%-%' AND d.word NOT LIKE '%.%' AND d.word NOT LIKE '%\"%' AND d.phonetic_voice_youdao IS NULL";
        List<Map<String, Object>> results = DBUtil.query(sql);
        return results;
    }

    public boolean updateDict(String word, String pronounce) {
        String sql = "UPDATE dict d SET d.pronounce = ? WHERE d.word = ?";
        int affectCount = DBUtil.execute(sql, pronounce, word);
        return ((affectCount > 0)? true : false);
    }

    public boolean updateDict2(String word, String explain_youdao, String associate_additional_youdao, String phonetic_voice_youdao) {
        String sql = "UPDATE dict_temp d SET d.explain_youdao = ?, d.associate_additional_youdao = ?, d.phonetic_voice_youdao = ? WHERE d.word = ?";
        int affectCount = DBUtil.execute(sql, explain_youdao, associate_additional_youdao, phonetic_voice_youdao, word);
        return ((affectCount > 0)? true : false);
    }

    public boolean insertDict(String word, String pronounce, String definitions, String explain, String exempli_gratia_sys, String exempli_gratia_user, String user_note, String phonetic_voice) {
        String sql = "INSERT INTO `dict` (`word`, `pronounce`, `definitions`, `explain`, `exempli_gratia_sys`, `exempli_gratia_user`, `user_note`, `phonetic_voice`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int affectCount = DBUtil.execute(sql, word, pronounce, definitions, explain, exempli_gratia_sys, exempli_gratia_user, user_note, phonetic_voice);
        return ((affectCount > 0)? true : false);
    }

    public boolean insertDict2(String word, String pronounce, String detail, String dual, String en, String voice, String explain,
                               String sentence, String patt, String phrase, String coll, String auth, String ess,
                               String discrim, String comn, String etm, String nfw, String nwd) {
        String sql = "INSERT INTO `dicts` (`word`, `pronounce`, `detail`, `dual`, `en`, `voice`, `explain`, `sentence`, patt, phrase, coll, auth, ess, discrim, comn, etm, nfw, nwd) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int affectCount = DBUtil.execute(sql, word, pronounce, detail, dual, en, voice, explain,
            sentence, patt, phrase, coll, auth, ess,
            discrim, comn, etm, nfw, nwd);
        return ((affectCount > 0)? true : false);
    }
}
