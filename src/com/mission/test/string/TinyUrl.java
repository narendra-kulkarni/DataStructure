package com.mission.test.string;

import java.util.HashMap;
import java.util.Map;

public class TinyUrl {

    private static final String BASE_URL = "http://tinyurl.com/";

    private final Map<String, String> shortToLong;
    private final Map<String, String> longToShort;
    private int counter = 1;

    public TinyUrl() {
        shortToLong = new HashMap<>();
        longToShort = new HashMap<>();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return BASE_URL + longToShort.get(longUrl);
        }

        String key = Integer.toString(counter, 36);
        counter++;

        shortToLong.put(key, longUrl);
        longToShort.put(longUrl, key);

        return BASE_URL + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String key = shortUrl.replace(BASE_URL, "");
        return shortToLong.get(key);
    }

    public static void main(String[] args) {
        TinyUrl t = new TinyUrl();
        String shortUrl = t.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println("Encoded URL : " + shortUrl);
        System.out.println("Decoded URL : " + t.decode(shortUrl));
    }
}
