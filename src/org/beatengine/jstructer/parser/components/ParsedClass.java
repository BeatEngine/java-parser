package org.beatengine.jstructer.parser.components;

import org.beatengine.jstructer.parser.FunctionResult;
import org.beatengine.jstructer.parser.Whitespace;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

public class ParsedClass
{
    private String name;

    public List<ParsedVariable> getMemberVariables()
    {
        return memberVariables;
    }

    public void setMemberVariables(final List<ParsedVariable> memberVariables)
    {
        this.memberVariables = memberVariables;
    }

    private List<ParsedVariable> memberVariables = new ArrayList<ParsedVariable>();

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

    /*private FunctionResult findNextFunction(final String s, int position)
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
            || result.getFunctionDefinition().replace(" ", "").replace("\r","").replace("\n","").equals(")")
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
    }*/

    private void parseOperations(final String opCode)
    {
        int db = 1;
    }

    /*private void parseBlock(final String blockCode)
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

    }*/

    int findScopeEnd(final String s, int position)
    {
        int i = 0;
        while (i == 0 && position < s.length())
        {
            if(s.charAt(position) == '{')
            {
                i++;
            }
            position++;
        }
        if(position >= s.length())
        {
            return 0;
        }
        while (i != 0 && position < s.length())
        {
            if(s.charAt(position) == '"')
            {
                //Skipping Strings
                position++;
                while(position < s.length())
                {
                    if(s.charAt(position) == '"' && s.charAt(position-1) != '\\')
                    {
                        position++;
                        break;
                    }
                }
            }
            if(s.charAt(position) == '{')
            {
                i++;
            }
            else if(s.charAt(position) == '}')
            {
                i--;
            }
            position++;
        }
        return position;
    }

    private void inti(final String s)
    {


        //String[] lines = s.split(";\r\n|;\n");
        int dbg = 1;
    }

    private void parseVariables(final String s)
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
            if(!c.isBlank())
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
                        memberVariables.add(new ParsedVariable(c.trim(), ""));
                    }
                    else
                    {
                        memberVariables.add(new ParsedVariable(left, right));
                    }
                }
            }
        }
    }

    private void parseFunction(final String head, final String body)
    {
        if(head.isBlank())
        {
            return;
        }
        final ParsedFunction parsedFunction = new ParsedFunction();
        String params = "";
        String functionDeclaration = "";
        int b = 0;
        for(int i = head.length()-1; i >= 0; i--)
        {
            if (head.charAt(i) == ')')
            {
                int l = i;
                boolean isInString = false;
                while (l < head.length())
                {
                    if(head.charAt(l) == '"' || head.charAt(l) == '\'')
                    {
                        if(l > 0 && head.charAt(l-1) != '\\')
                        {
                            isInString = true;
                            break;
                        }
                    }
                    else if(head.charAt(l) == '\n')
                    {
                        break;
                    }
                    l++;
                }
                if(!isInString)
                {
                    b++;
                }
            }
            else if (head.charAt(i) == '(')
            {
                int l = i;
                boolean isInString = false;
                while (l < head.length())
                {
                    if(head.charAt(l) == '"' || head.charAt(l) == '\'')
                    {
                        if(l > 0 && head.charAt(l-1) != '\\')
                        {
                            isInString = true;
                            break;
                        }
                    }
                    else if(head.charAt(l) == '\n')
                    {
                        break;
                    }
                    l++;
                }
                if(!isInString)
                {
                    b--;
                }
            }
            if(b == 0)
            {
                functionDeclaration = head.substring(0, i);
                params = head.substring(i);
                break;
            }
        }

        if(!params.isBlank() && !functionDeclaration.isBlank())
        {
            params = params.substring(1,params.length()-1);
            //Parameter
            if(!params.isEmpty())
            {
                parsedFunction.parseParameters(params);
            }
            int pos = 0;
            int end = functionDeclaration.length();
            int a = 0;
            for (int i = functionDeclaration.length()-1; i >= 0; i--)
            {
                if(Whitespace.isWhitespace(functionDeclaration.charAt(i)))
                {
                    parsedFunction.setName(functionDeclaration.substring(i, end).trim());
                    end = i;
                    break;
                }
            }
            for(int i = 0; i < functionDeclaration.length(); i++)
            {
                if(Whitespace.isWhitespace(functionDeclaration.charAt(i)))
                {
                    final String keyWord = functionDeclaration.substring(a, i).trim();

                    if(pos == 0)
                    {
                        parsedFunction.setVisibillity(keyWord);
                        pos++;
                    }
                    else if("synchronized".equals(keyWord))
                    {
                        parsedFunction.setSyncronised(keyWord);
                    }
                    else if("static".equals(keyWord))
                    {
                        parsedFunction.setSyncronised(keyWord);
                    }
                    else
                    {
                        parsedFunction.setReturnType(functionDeclaration.substring(a, end).trim());
                        break;
                    }
                    a = i;
                }
            }
        }

        parsedFunction.parseBody(body);
        functions.add(parsedFunction);
    }

    public void parse(final String s)
    {
        final List<String> scopeBlocks = new ArrayList<String>();
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

        //Parsing functions and Variables

        for(int i = 0; i < variableBlocks.size(); i++)
        {
            final String head = variableBlocks.get(i);
            String scopeHead = "";
            String variableHead = "";
            for(int h = head.length()-1; h >= 0; h--)
            {
                if(head.indexOf("public",h) == h || head.indexOf("private",h) == h || head.indexOf("protected",h) == h)
                {
                    scopeHead = head.substring(h).replace("\r","").replace("\n"," ").trim();
                    variableHead = head.substring(0, h);
                    break;
                }
            }
            System.out.println(scopeHead);
            parseVariables(variableHead);
            parseFunction(scopeHead, scopeBlocks.get(i));
        }
        int dbg = 1;
    }
}
