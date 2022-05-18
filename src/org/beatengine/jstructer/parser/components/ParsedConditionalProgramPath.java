package org.beatengine.jstructer.parser.components;

import java.util.ArrayList;
import java.util.List;

public class ParsedConditionalProgramPath implements ParsedProgramBlock
{
    private List<ParsedProgramBlock> blocks;
    private ParsedProgramBlock ifPath;
    private OperationLine ifCondition;

    private List<ParsedProgramBlock> elsePaths = new ArrayList<ParsedProgramBlock>();
    private List<OperationLine> elseConditions = new ArrayList<OperationLine>();

    public ParsedConditionalProgramPath(final String conditionHead, final String body)
    {
        //todo parse condition
        this.parse(body);
    }

    public ParsedConditionalProgramPath()
    {

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
    public void parse(final String s)
    {
        blocks = ParsedProgramBlock.parser(s);
    }
}
