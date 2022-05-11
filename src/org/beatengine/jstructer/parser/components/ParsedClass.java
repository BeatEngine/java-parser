package org.beatengine.jstructer.parser.components;

import org.beatengine.jstructer.parser.FunctionResult;
import org.beatengine.jstructer.parser.Whitespace;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

public class ParsedClass
{
    private String name;

    private List<ParsedFunction> functions = new ArrayList<ParsedFunction>();

    public ParsedClass()
    {

    }

    private int debugging = 1;

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

    private FunctionResult findNextFunction(final String s, int position)
    {
        final FunctionResult result = new FunctionResult();
        int a = s.indexOf(")", position);
        a++;
        int fend = a;
        if(fend <= position)
        {
            return result;
        }
        while (a < s.length())
        {
            final char c = s.charAt(a);
            if(Whitespace.isWhitespace(c))
            {

            }
            else if(Whitespace.isEndline(c))
            {
                fend = a;
                break;
            }
            else
            {
                a = s.indexOf(")", a);
            }
            a++;
        }
        a = fend-1;
        if(a < position)
        {
            return result;
        }
        while (a >= position)
        {
            if(Whitespace.isEndline(s.charAt(a)))
            {
                result.setFunctionDefinition( s.substring(a, fend));
                result.setPosition(a);
                break;
            }
            a--;
        }
        if(result.getFunctionDefinition().contains("while(") || result.getFunctionDefinition().contains("while (") || result.getFunctionDefinition().contains("while\t(")
         || result.getFunctionDefinition().contains("if(") || result.getFunctionDefinition().contains("if (") || result.getFunctionDefinition().contains("if\t(")
                || result.getFunctionDefinition().contains("do") || result.getFunctionDefinition().contains("do\r\n") || result.getFunctionDefinition().contains("do\n")
                || result.getFunctionDefinition().contains("else ") || result.getFunctionDefinition().contains("else\r\n") || result.getFunctionDefinition().contains("else\n")
                || result.getFunctionDefinition().contains("for(") || result.getFunctionDefinition().contains("for (") || result.getFunctionDefinition().contains("for\t(")
                )
        {
            return findNextFunction(s, fend+1);
        }
        if(result.getFunctionDefinition().isBlank() && fend +1 < s.length())
        {
            return findNextFunction(s,  fend +1);
        }

        return result;
    }

    private int findNextLoopOrConditional(final String s, final int position)
    {
        int i = s.indexOf("if(", position);
        if(i < 0)
        {
            i = s.indexOf("if (", position);
        }
        if(i < 0)
        {
            i = s.indexOf("if\t(", position);
        }
        if(i < 0)
        {
            i = Integer.MAX_VALUE;
        }

        int f = s.indexOf("for(", position);
        if(f < 0)
        {
            f = s.indexOf("for (", position);
        }
        if(f < 0)
        {
            f = s.indexOf("while\t(", position);
        }
        if(f < 0)
        {
            f = Integer.MAX_VALUE;
        }

        int w = s.indexOf("while(", position);
        if(w < 0)
        {
            w = s.indexOf("while (", position);
        }
        if(w < 0)
        {
            w = s.indexOf("while\t(", position);
        }
        if(w < 0)
        {
            w = Integer.MAX_VALUE;
        }

        if(i == f && f == w)
        {
            return -1;
        }

        if(i <= f && i <= w)
        {
            return i;
        }
        if(f <= w && f <= i)
        {
            return f;
        }
        if(w <= f && w <= i)
        {
            return w;
        }
        return -1;
    }

    private void parseOperations(final String opCode)
    {
        int db = 1;
    }

    private void parseBlock(final String blockCode)
    {
        int b = 1;
        int pos = 0;
        while (pos < blockCode.length())
        {
            int idx = findNextLoopOrConditional(blockCode, pos);
            if(pos == 0 && idx < 0)
            {
                parseOperations(blockCode);
            }
            else if(idx < 0)
            {
                if(pos >= 0)
                {
                    parseOperations(blockCode.substring(pos));
                }
                break;
            }
            else
            {
                parseOperations(blockCode.substring(pos, idx));
            }
            pos = idx;
        }

    }

    public void parse(final String substring)
    {

        int pos = 0;
        while (pos < substring.length())
        {
            FunctionResult nextFunction = findNextFunction(substring, pos);
            if(nextFunction.getFunctionDefinition().isBlank())
            {
                break;
            }
            if(pos < nextFunction.getEndPosition() && pos >= 0)
            {
                parseBlock(substring.substring(pos, nextFunction.getPosition()));
            }
            pos = nextFunction.getEndPosition()+1;
            System.out.println(nextFunction.getFunctionDefinition());
        }
    }
}
