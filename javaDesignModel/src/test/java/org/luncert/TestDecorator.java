package org.luncert;

import java.io.IOException;
import java.io.Writer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.decoratorModel.ConsoleWriter;
import org.luncert.decoratorModel.RandomCaseFilter;
import org.luncert.decoratorModel.TitleCaseFilter;

@RunWith(JUnit4.class)
public class TestDecorator {

    @Test
    public void test() throws IOException {
        Writer out = new ConsoleWriter();
        out = new TitleCaseFilter(out);
        out.write("This Text, notably ALL in upper casE!\n");
        out.close();

        out = new ConsoleWriter();
        out = new RandomCaseFilter(out);
        out.write("This Text, notably ALL in upper casE!\n");
        out.close();
    }
    
}