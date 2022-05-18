package org.beatengine.jstructer.parser.components;

public abstract class ParsedOperationPart
{

    public static ParsedOperationPart parse(final String variableOrFunctionCall)
    {
        boolean isFunctionCall = false;
        for(int i = 0; i < variableOrFunctionCall.length(); i++)
        {
            if(variableOrFunctionCall.charAt(i) == '(')
            {
                int l = i;
                boolean isInString = false;
                while (l < variableOrFunctionCall.length())
                {
                    if(variableOrFunctionCall.charAt(l) == '"' || variableOrFunctionCall.charAt(l) == '\'')
                    {
                        if(l > 0 && variableOrFunctionCall.charAt(l-1) != '\\')
                        {
                            int f = l+1;
                            int res = 0;
                            while (f < variableOrFunctionCall.length())
                            {
                                if(f > 0 && variableOrFunctionCall.charAt(l) == '"' || variableOrFunctionCall.charAt(l) == '\'' && variableOrFunctionCall.charAt(f-1) != '\\')
                                {
                                    res++;
                                }
                                f++;
                            }
                            if(res % 2 != 0)
                            {
                                isInString = true;
                                break;
                            }
                        }
                    }
                    l++;
                }
                if(!isInString)
                {
                    isFunctionCall = true;
                    break;
                }
            }
        }

        if(isFunctionCall)
        {
            return new ParsedFunctionCall(variableOrFunctionCall);
        }
        else
        {
            return new ParsedOperationVariable(variableOrFunctionCall);
        }
    }

}
