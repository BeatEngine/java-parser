package org.beatengine.jstructer.parser.components;

import org.beatengine.jstructer.parser.Whitespace;
import org.w3c.dom.ls.LSProgressEvent;

import java.util.ArrayList;
import java.util.List;

public class ParsedFunction
{
    private String visibillity = "";
    private String syncronised = "";
    private String static_ = "";
    private String returnType = "";

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    private String name = "";

    private List<ParsedParameter> parameters = new ArrayList<ParsedParameter>();

    private ParsedProgramBlock program;

    public void parseParameters(final String parameterLine)
    {
        final String[] params = parameterLine.split(",");

        for(final String p : params)
        {
            final String param = p.trim();
           for(int i = param.length()-1; i >= 0; i--)
           {
               if(Whitespace.isWhitespace(param.charAt(i)))
               {
                   parameters.add(new ParsedParameter(param.substring(0, i).trim(), param.substring(i).trim()));
                   break;
               }
           }
        }
    }

    public void parseBody(final String s)
    {
        program = new ParsedFunctionBody(s, this);
    }

    public String getVisibillity()
    {
        return visibillity;
    }

    public void setVisibillity(final String visibillity)
    {
        this.visibillity = visibillity;
    }

    public String getSyncronised()
    {
        return syncronised;
    }

    public void setSyncronised(final String syncronised)
    {
        this.syncronised = syncronised;
    }

    public String getStatic_()
    {
        return static_;
    }

    public void setStatic_(final String static_)
    {
        this.static_ = static_;
    }

    public String getReturnType()
    {
        return returnType;
    }

    public void setReturnType(final String returnType)
    {
        this.returnType = returnType;
    }
}
