package com.example.urlshorten.utils;

import org.springframework.stereotype.Component;

@Component
public class Base62Conversion {
    public static final String BASE62_CHARACTER = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int BASE = 62;
    public String encode(long number){
        StringBuilder res = new StringBuilder();
        while(number > 0){
            res.append(BASE62_CHARACTER.charAt((int)(number % BASE)) + "");
            number = number/BASE;
        }
        return res.reverse().toString();
    }

    public long deocde(String base62){
        System.out.println("length 62: " + BASE62_CHARACTER.length());
        long res = 0;
        int n = base62.length();
        int baseCount = 0;
        for (int i = n -1; i >= 0; i--){
            res += BASE62_CHARACTER.indexOf(base62.charAt(i)) * Math.pow(BASE, baseCount);
            baseCount++;
        }
        return res;
    }
    public static void main(String[] args) {
        long base10 = 12341235123512L;
        Base62Conversion conversion = new Base62Conversion();
        String base62 =  conversion.encode(base10);
        System.out.println("base62: " + base62);
        System.out.println("base10: " + conversion.deocde(base62));

        // max long
        System.out.println("\n Max long\n");
        System.out.println("max long: " + Long.MAX_VALUE);
        String maxbase62 =  conversion.encode(Long.MAX_VALUE);
        System.out.println("base62: " + maxbase62);
        System.out.println("base10: " + conversion.deocde(maxbase62));
    }
}
