package org.beatengine.jstructer.parser.components;

import java.util.ArrayList;
import java.util.List;

public class ParsedFunctionCall extends ParsedOperationPart
{
    private String functionName;

    private List<ParsedParameter> parameters = new ArrayList<>();

    public ParsedFunctionCall()
    {

    }

    public ParsedFunctionCall(final String functionLine)
    {
        String params = "";
        for(int i = 0; i < functionLine.length(); i++)
        {
            if(functionLine.charAt(i) == '(')
            {
                functionName = functionLine.substring(0, i);
                params = functionLine.substring(i+1, functionLine.length()-1);
                break;
            }
        }
        //todo look for the brackets a(b(1,2),c(3)) ...
        final String[] parms = params.split(",");
        for(int i = 0; i < parms.length; i++)
        {
            parameters.add(new ParsedParameter("", parms[i].trim()));
        }
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName(String functionName)
    {
        this.functionName = functionName;
    }

    public List<ParsedParameter> getParameters()
    {
        return parameters;
    }

}
