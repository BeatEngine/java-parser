package org.beatengine.jstructer.parser;

import org.beatengine.jstructer.parser.components.ParsedClass;

public class ClassParser
{
    private ParsedClass parsedClass;

    private void parse(final String code)
    {
        if(!code.contains(" class "))
        {
            System.err.println("Input is not a class!");
            return;
        }
        int i = code.indexOf(" class ");
        i++;
        while (i < code.length() && !Whitespace.isEndline(code.charAt(i)))
        {
            i++;
        }
        int endDec = i;
        String className = "";
        while (i >= 0)
        {
            if(Whitespace.isWhitespace(code.charAt(i)) && (code.charAt(i) != '\r'))
            {
                className = code.substring(i+1, endDec).replace("\n", "").replace("\r", "");
                break;
            }
            i--;
        }
        parsedClass = new ParsedClass();
        parsedClass.setName(className);
        i = endDec;
        int a = i;
        int b = code.length();
        while (i < code.length())
        {
            if(code.charAt(i) == '{')
            {
                a = i+1;
                break;
            }
            i++;
        }
        i = code.length()-1;
        while (i > endDec)
        {
            if(code.charAt(i) == '}')
            {
                b = i-1;
                break;
            }
            i--;
        }
        parsedClass.parse(code.substring(a, b));
    }

    public ClassParser(final String text)
    {
        parse(text);
    }

    public ParsedClass getParsedClass()
    {
        return parsedClass;
    }
}
