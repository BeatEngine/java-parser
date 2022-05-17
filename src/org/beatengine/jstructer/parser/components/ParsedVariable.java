package org.beatengine.jstructer.parser.components;

import org.beatengine.jstructer.parser.Whitespace;

public class ParsedVariable
{

    public ParsedVariable()
    {

    }

    public ParsedVariable(final String left, final String right)
    {
        for (int i = left.length()-1; i >= 0; i--)
        {
            if(Whitespace.isWhitespace(left.charAt(i)))
            {
                name = left.substring(i).trim();
                type = left.substring(0, i).trim();
                break;
            }
        }
        initialisation = right;
    }

    private String type;

    private String name;

    private String initialisation;

    private String value;


}
