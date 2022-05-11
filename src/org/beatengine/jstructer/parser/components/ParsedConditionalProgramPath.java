package org.beatengine.jstructer.parser.components;

import java.util.ArrayList;
import java.util.List;

public class ParsedConditionalProgramPath
{

    private ParsedProgramBlock ifPath;
    private OperationLine ifCondition;

    private List<ParsedProgramBlock> elsePaths = new ArrayList<ParsedProgramBlock>();
    private List<OperationLine> elseConditions = new ArrayList<OperationLine>();


}
