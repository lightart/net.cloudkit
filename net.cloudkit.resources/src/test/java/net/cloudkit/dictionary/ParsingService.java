package net.cloudkit.dictionary;

import java.util.List;
import java.util.Map;

public class ParsingService {

    public List<Map<String, Object>> getDictionaryList() {
        String sql = "SELECT d.word FROM dictionary d";
        List<Map<String, Object>> results = DBUtil.query(sql);
        return results;
    }

    public boolean updateDictionary(String word, String pronounce, String explainText, String associateAdditional, String phonetic_voice) {
        String sql = "UPDATE dictionary d SET d.pronounce = ?, d.explain_text = ?, d.associate_additional = ?, d.phonetic_voice = ? WHERE d.word = ?";
        int affectCount = DBUtil.execute(sql, pronounce, explainText, associateAdditional, phonetic_voice, word);
        return ((affectCount > 0)? true : false);
    }
}
