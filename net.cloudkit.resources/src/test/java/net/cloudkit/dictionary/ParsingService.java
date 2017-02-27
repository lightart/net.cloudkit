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

    public List<Map<String, Object>> getDictionaryList2() {
        String sql = "SELECT d.word FROM dict d WHERE d.word NOT LIKE '% %' AND d.pronounce IS NULL LIMIT 100";
        List<Map<String, Object>> results = DBUtil.query(sql);
        return results;
    }

    public boolean updateDictionary2(String word, String pronounce) {
        String sql = "UPDATE dict d SET d.pronounce = ? WHERE d.word = ?";
        int affectCount = DBUtil.execute(sql, pronounce, word);
        return ((affectCount > 0)? true : false);
    }

    public boolean insertDictionary(String word, String pronounce, String definitions, String explain, String exempli_gratia_sys, String exempli_gratia_user, String user_note, String phonetic_voice) {
        String sql = "INSERT INTO `dict` (`word`, `pronounce`, `definitions`, `explain`, `exempli_gratia_sys`, `exempli_gratia_user`, `user_note`, `phonetic_voice`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int affectCount = DBUtil.execute(sql, word, pronounce, definitions, explain, exempli_gratia_sys, exempli_gratia_user, user_note, phonetic_voice);
        return ((affectCount > 0)? true : false);
    }
}
