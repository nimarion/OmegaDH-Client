package de.nimarion.osv.utils;

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

    public static byte[] hexToAscii(String hexStr) {
        if(hexStr.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have an even length");
        }
        hexStr = hexStr.replaceAll(" ", "");
        byte[] bytes = new byte[hexStr.length() / 2];

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(str, 16);
        }
        return bytes;
    }

}
