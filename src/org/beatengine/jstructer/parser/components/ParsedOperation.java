package org.beatengine.jstructer.parser.components;

public class ParsedOperation
{
    private ParsedOperationPart leftVariable;

    private String operator;

    private ParsedOperationPart rightVariable;

    public ParsedOperation(ParsedOperationPart leftVariable, String operator, ParsedOperationPart rightVariable)
    {
        this.leftVariable = leftVariable;
        this.operator = operator;
        this.rightVariable = rightVariable;
    }

    public ParsedOperation()
    {

    }

    public ParsedOperationPart getLeftVariable()
    {
        return leftVariable;
    }

    public String getOperator()
    {
        return operator;
    }

    public ParsedOperationPart getRightVariable()
    {
        return rightVariable;
    }
}
