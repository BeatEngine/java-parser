package org.beatengine.jstructer.parser.components;

import java.util.ArrayList;
import java.util.List;

public interface ParsedProgramBlock
{
    public List<ParsedProgramBlock> getBlocks();

    public void setBlocks(List<ParsedProgramBlock> blocks);

    public void addBlock(ParsedProgramBlock next);

    public void parse(String s);

    public static List<ParsedProgramBlock> parser(final String s)
    {
        final List<ParsedProgramBlock> resultBlocks = new ArrayList<>();
        final List<String> scopeBlocks    = new ArrayList<String>();
        final List<String> variableBlocks = new ArrayList<String>();

        int a = 0;
        int b = 0;
        int e = 0;

        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == '{')
            {
                int l = i;
                boolean isInString = false;
                while (l < s.length())
                {
                    if(s.charAt(l) == '"' || s.charAt(l) == '\'')
                    {
                        if(l > 0 && s.charAt(l-1) != '\\')
                        {
                            isInString = true;
                            break;
                        }
                    }
                    else if(s.charAt(l) == '\n')
                    {
                        break;
                    }
                    l++;
                }
                if(!isInString)
                {
                    if(b == 0)
                    {
                        variableBlocks.add(s.substring(e+1, i));
                        a = i;
                    }
                    b++;
                }
            }
            if(s.charAt(i) == '}')
            {
                int l = i;
                boolean isInString = false;
                while (l < s.length())
                {
                    if(s.charAt(l) == '"' || s.charAt(l) == '\'')
                    {
                        if(l > 0 && s.charAt(l-1) != '\\')
                        {
                            isInString = true;
                            break;
                        }
                    }
                    else if(s.charAt(l) == '\n')
                    {
                        break;
                    }
                    l++;
                }
                if(!isInString)
                {
                    if(b == 1)
                    {
                        e = i;
                        scopeBlocks.add(s.substring(a+1, i));
                    }
                    b--;
                }
            }
        }

        //Parsing loops, conditions and Variables

        for(int i = 0; i < variableBlocks.size(); i++)
        {
            final String head = variableBlocks.get(i);
            String scopeHead = "";
            String variableHead = "";
            for(int h = head.length()-1; h >= 0; h--)
            {
                if(head.indexOf("if",h) == h || head.indexOf("else",h) == h || head.indexOf("while",h) == h || head.indexOf("for",h) == h || head.indexOf("do",h) == h)
                {
                    scopeHead = head.substring(h).replace("\r","").replace("\n"," ").trim();
                    variableHead = head.substring(0, h);
                    break;
                }
            }
            resultBlocks.add(new ParsedProgramOperationBlock(variableHead));
            resultBlocks.add(new ParsedConditionalProgramPath(scopeHead, scopeBlocks.get(i)));
            //System.out.println(scopeHead);
            //parseVariables(variableHead);
            //System.out.println(scopeBlocks.get(i));
            //parseFunction(scopeHead, scopeBlocks.get(i));
        }
        return resultBlocks;
    }

}
