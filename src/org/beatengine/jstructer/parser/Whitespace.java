package org.beatengine.jstructer.parser;

public class Whitespace
{

    public static boolean isWhitespace(final char c)
    {
        return c == ' ' || c == '\t' || c == '\r';
    }

    public static boolean isEndline(final char c)
    {
        return c == '\n';
    }


}
