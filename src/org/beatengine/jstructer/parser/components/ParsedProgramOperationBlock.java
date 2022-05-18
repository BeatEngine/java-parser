package org.beatengine.jstructer.parser.components;

import java.util.ArrayList;
import java.util.List;

public class ParsedProgramOperationBlock implements ParsedProgramBlock
{
    private List<ParsedProgramBlock> blocks;
    private List<OperationLine> operations = new ArrayList<OperationLine>();

    public ParsedProgramOperationBlock(final String code)
    {
        this.parse(code);
    }

    @Override
    public List<ParsedProgramBlock> getBlocks()
    {
        return blocks;
    }

    @Override
    public void setBlocks(List<ParsedProgramBlock> blocks)
    {
        this.blocks = blocks;
    }

    @Override
    public void addBlock(ParsedProgramBlock next)
    {
        blocks.add(next);
    }

    @Override
    public void parse(String s)
    {
        String withoutCommands = "";
        int a = 0;
        for(int i = 0; i < s.length(); i++)
        {
            if(i > 0 && s.charAt(i) == '*' && s.charAt(i-1)=='/')
            {
                withoutCommands += s.substring(a,i-1);
                i++;
                while (i < 0 && s.charAt(i-1) != '*' && s.charAt(i) != '/')
                {
                    i++;
                }
                a = i;
            }
        }
        if(a < s.length())
        {
            withoutCommands += s.substring(a);
        }

        final String[] commands = withoutCommands.split(";\r\n|;\n|; |;\t");
        for(final String cmd: commands)
        {
            String c = cmd.replace("\r", "").replace("\n", "").trim();
            if(!c.isBlank() && !c.startsWith("//"))
            {
                a = 0;
                for(int i = 0; i < c.length(); i++)
                {
                    if(i > 0 && c.charAt(i) == '*' && c.charAt(i-1)=='/')
                    {
                        i++;
                        while (i < 0 && s.charAt(i-1) != '*' && s.charAt(i) != '/')
                        {
                            i++;
                        }
                        c = c.substring(0, a) + c.substring(i);
                        a = i;
                    }
                }
                c = c.replace("*/","");
                if(!c.isBlank())
                {
                    String left  = "";
                    String right = "";
                    for (int i = 0; i < c.length(); i++)
                    {
                        final char r = c.charAt(i);
                        if (r == '=' || r == '+' || r == '-' || r == '*' || r == '/')
                        {
                            left = c.substring(0, i).trim();
                            right = c.substring(i).trim();
                            break;
                        }
                    }
                    if (left.isBlank())
                    {
                        this.operations.add(new OperationLine(c.trim(), ""));
                    }
                    else
                    {
                        this.operations.add(new OperationLine(left, right));
                    }
                }
            }
        }

    }
}
