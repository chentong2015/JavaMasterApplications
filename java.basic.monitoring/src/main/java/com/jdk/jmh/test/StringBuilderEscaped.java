package com.jdk.jmh.test;

public class StringBuilderEscaped {

    StringBuilder sb = new StringBuilder();

    public StringBuilderEscaped append(final CharSequence value) {
        sb.append(value);
        return this;
    }

    public StringBuilderEscaped append(int nb) {
        sb.append(nb);
        return this;
    }

    public StringBuilderEscaped appendEscaped(final String value) {
        int length = value.length();
        int start = 0;
        int current = 0;
        for (; current < length; ) {
            char c = value.charAt(current);
            // Search for character needing escaping
            if (c < 0x20 || c == 0x22 || c == 0x5C) {
                // What we have so far does not contains anything to escape, write them all in once.
                sb.append(value.substring(start, current));
                switch (c) {
                    case '"':
                        sb.append("\\\"");
                        break;
                    case '\\':
                        sb.append("\\\\");
                        break;
                    case '\b':
                        sb.append("\\b");
                        break;
                    case '\f':
                        sb.append("\\f");
                        break;
                    case '\n':
                        sb.append("\\n");
                        break;
                    case '\r':
                        sb.append("\\r");
                        break;
                    case '\t':
                        sb.append("\\t");
                        break;
                    case '\0':
                        sb.append("\\0");
                        break;
                    default:
                        sb.append("u" + Integer.toString(c));
                        break;
                }

                start = current + 1;
            }
            ++current;
        }
        if (start == 0) {
            return this;
        }
        // Write remainings chars.
        if (current != start) {
            sb.append(value.substring(start, current));
        }
        return this;
    }

    public String toString() {
        return sb.toString();
    }
}
