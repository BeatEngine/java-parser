package org.beatengine.jstructer.parser.components;

import java.util.ArrayList;
import java.util.List;

public class ParsedClass
{
    private String name;

    private List<ParsedFunction> functions = new ArrayList<ParsedFunction>();

    public ParsedClass()
    {

    }

    public void addFunction(final ParsedFunction function)
    {
        functions.add(function);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<ParsedFunction> getFunctions()
    {
        return functions;
    }

    public void setFunctions(List<ParsedFunction> functions)
    {
        this.functions = functions;
    }

    public void parse(String substring)
    {
        //todo ...
    }
}
