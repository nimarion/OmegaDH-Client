package de.nimarion.osv;

public class StringUtils {

    public static String toAscii(String data) {
        StringBuilder builder = new StringBuilder();
        for (char c : data.toCharArray()) {
            builder.append((int) c).append(" ");
        }
        return builder.toString().trim();
    }

    public static String fromAscii(String ascii) {
        StringBuilder builder = new StringBuilder();
        for (String s : ascii.split(" ")) {
            if (s.isEmpty()) {
                continue;
            }
            builder.append((char) Integer.parseInt(s));
        }
        return builder.toString().trim();
    }

}
