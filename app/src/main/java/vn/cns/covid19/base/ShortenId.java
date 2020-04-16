package vn.cns.covid19.base;

public class ShortenId {
    private static final String ALPHABET = "23456789BCDFGHJKLMNPQRSTVWXYZ";
    private static final int BASE = ALPHABET.length();

    public static String encode(long num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            int index = (int)(num % BASE);
            str.insert(0, ALPHABET.charAt(index));
            num = num / BASE;
        }
        return str.toString();
    }

    public static long decode(String str) {
        long num = 0;
        for (int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i);
            int index = ALPHABET.indexOf(ch);
            if (index < 0){
                throw new RuntimeException("Invalid char " + (char)ch);
            }

            num = num * BASE + index;
        }
        return num;
    }
}
