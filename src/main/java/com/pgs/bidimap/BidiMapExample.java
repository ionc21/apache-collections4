package com.pgs.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class BidiMapExample {
    
    private static final String locals = "en-Englis;ro-Romanian;fr-French";

    public static BidiMap<String, String> getLanguageKeyValuePair() {
        final BidiMap<String, String> langs = new DualHashBidiMap<String, String>();
        final String[] langsFromProp = locals.split(";");
        for (String st : langsFromProp) {
            final String[] parts = st.split("-");
            langs.put(parts[0], parts[1]);
        }
        return langs;
    }

    public static void main(String[] args) {
        BidiMap<String, String> localsBidiMap = getLanguageKeyValuePair();
        System.out.println("Print key: " +localsBidiMap.getKey("French"));
        System.out.println("Print value: " +localsBidiMap.get("fr"));
    }
}
