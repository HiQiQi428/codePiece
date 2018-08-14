package org.luncert.decoratorModel;

import java.io.IOException;
import java.io.Writer;

public class LowerCaseFilter extends BasicFilter {

    public LowerCaseFilter(Writer out) {
        super(out);
    }

	@Override
	public void write(int c) throws IOException {
		out.write(Character.toLowerCase(c));
	}

}