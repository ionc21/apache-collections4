package com.pgs.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class BidiMapExample {

    @Value("${locals}")
    private static String locals;

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
