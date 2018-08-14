package org.luncert.decoratorModel;

import java.io.IOException;
import java.io.Writer;

public class UpperCaseFilter extends BasicFilter {

    public UpperCaseFilter(Writer out) {
        super(out);
    }

	@Override
	public void write(int c) throws IOException {
        out.write(Character.toUpperCase((char)c));
	}

}