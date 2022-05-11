package org.beatengine.jstructer;

import org.beatengine.jstructer.parser.ClassParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        try
        {
            ClassParser parser = new ClassParser(new String(new FileInputStream("./src/org/beatengine/jstructer/parser/components/ParsedClass.java").readAllBytes()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
