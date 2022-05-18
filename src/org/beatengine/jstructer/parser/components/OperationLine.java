package org.beatengine.jstructer.parser.components;

import org.beatengine.jstructer.parser.Whitespace;

import java.util.ArrayList;
import java.util.List;

public class OperationLine
{
    private ParsedVariable variable;
    private List<ParsedOperation> operationChain = new ArrayList<>();

    public OperationLine()
    {

    }

    public OperationLine(final String left, final String right)
    {
        variable = new ParsedVariable(left, right);
        this.parse(left, right);
    }

    public void parse(final String left, final String right)
    {
        String firstVar = "";
        for(int i = left.length()-1; i >= 0; i--)
        {
            if(Whitespace.isWhitespace(left.charAt(i)) || i == 0)
            {
                firstVar = left.substring(i).trim();
                break;
            }
        }
        int a = 0;
        for(int i = 0; i < right.length(); i++)
        {
            final char r = right.charAt(i);
            if(r == '=' || r == '+' || r == '-' || r == '*' || r == '/')
            {
                //todo find a solution --> look for brackets "(" and ")" to make complex possible like a(2+b(3)) ...
                String rightVar = "";
                if(a > 0)
                {
                    firstVar = right.substring(a, i-1);
                }
                int l = i+2;
                while (l < right.length() && right.charAt(l) != '=' && right.charAt(l) != '+' && right.charAt(l) != '-' && right.charAt(l) != '*' && right.charAt(l) != '/')
                {
                    l++;
                }
                rightVar = right.substring(i+1, l).trim();


                if(i > 0 && r == '=' && right.charAt(i-1) == '+')
                {
                    // +=
                    operationChain.add(new ParsedOperation(ParsedOperationPart.parse(firstVar), "++", ParsedOperationPart.parse(rightVar)));
                }
                else if(i > 0 && r == '=' && right.charAt(i-1) == '-')
                {
                    // -=
                    operationChain.add(new ParsedOperation(ParsedOperationPart.parse(firstVar), "++", ParsedOperationPart.parse(rightVar)));
                }
                else if(i > 0 && r == '=' && right.charAt(i-1) == '*')
                {
                    // *=
                    operationChain.add(new ParsedOperation(ParsedOperationPart.parse(firstVar), "++", ParsedOperationPart.parse(rightVar)));
                }
                else if(i > 0 && r == '=' && right.charAt(i-1) == '/')
                {
                    // /=
                    operationChain.add(new ParsedOperation(ParsedOperationPart.parse(firstVar), "++", ParsedOperationPart.parse(rightVar)));
                }
                else if(i +1 < right.length() && r == '+' &&  right.charAt(i+1) == '+')
                {
                    // ++
                    operationChain.add(new ParsedOperation(new ParsedOperationVariable(firstVar), "++", new ParsedOperationVariable("")));
                    break;
                }
                else if(i +1 < right.length() && r == '-' &&  right.charAt(i+1) == '-')
                {
                    // --
                    operationChain.add(new ParsedOperation(new ParsedOperationVariable(firstVar), "--", new ParsedOperationVariable("")));
                    break;
                }
                else if(r == '=' && rightVar.startsWith("new") && rightVar.endsWith(")"))
                {
                    //new Object
                    for(int v = rightVar.length()-1; v >= 0; v--)
                    {
                        if(Whitespace.isWhitespace(rightVar.charAt(v)) || v == 0)
                        {
                            rightVar = rightVar.substring(v).trim();
                            break;
                        }
                    }
                    operationChain.add(new ParsedOperation(new ParsedOperationVariable(firstVar), "--", new ParsedFunctionCall(rightVar)));
                    break;
                }
                else
                {
                    // =
                    operationChain.add(new ParsedOperation(new ParsedOperationVariable(firstVar), "--", ParsedOperationPart.parse(rightVar)));
                }
                a = i;
            }
        }
        int dbg = 1;

    }

    public ParsedVariable getVariable()
    {
        return variable;
    }

    public void setVariable(ParsedVariable variable)
    {
        this.variable = variable;
    }

    public List<ParsedOperation> getOperationChain()
    {
        return operationChain;
    }

}
