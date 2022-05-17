package org.beatengine.jstructer.parser.components;

import java.util.ArrayList;
import java.util.List;

public class ParsedFunctionBody implements ParsedProgramBlock
{
    private ParsedFunction parentFunction;

    ParsedConditionalProgramPath body = new ParsedConditionalProgramPath();

    public ParsedFunctionBody()
    {

    }

    public ParsedFunctionBody(final String body, final ParsedFunction parentFunction)
    {
        this.parentFunction = parentFunction;
        this.parse(body);
    }

    public void parse(final String s)
    {
        body.parse(s);
    }

    @Override
    public List<ParsedProgramBlock> getBlocks()
    {
        return body.getBlocks();
    }

    @Override
    public void setBlocks(List<ParsedProgramBlock> blocks)
    {
        body.setBlocks(blocks);
    }

    @Override
    public void addBlock(ParsedProgramBlock next)
    {
       body.addBlock(next);
    }
}
