package org.beatengine.jstructer.parser;

public class FunctionResult
{
    private int position;
    private String functionDefinition = "";

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public String getFunctionDefinition()
    {
        return functionDefinition;
    }

    public void setFunctionDefinition(String functionDefinition)
    {
        this.functionDefinition = functionDefinition;
    }

    public int getEndPosition()
    {
        return position + functionDefinition.length();
    }
}
