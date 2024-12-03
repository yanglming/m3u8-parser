package io.lindstrom.m3u8.parser;

public interface Attribute<T, B> {
    void read(B builder, String value) throws PlaylistParserException;
    void write(T value, TextBuilder textBuilder);
    String name();

    default String key() {
        String name = name();
        return name.contains("_") ? name.replace("_", "-") : name;
    }

    default String camelCaseKey() {
        String name = name();
        StringBuilder result = new StringBuilder();
        boolean nextUpperCase = false;

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    c = Character.toUpperCase(c);
                    nextUpperCase = false;
                } else {
                    c = Character.toLowerCase(c);
                }
                result.append(c);
            }
        }
        // 将首字母大写
        result.setCharAt(0, Character.toUpperCase(result.charAt(0)));
        return result.toString();
    }

    default void read(B builder, String key, String value) {
        // no-op
    }
}
