package org.luncert.decoratorModel;

import java.io.IOException;
import java.io.Writer;

public class TitleCaseFilter extends BasicFilter {

    private boolean inWhite = true;

    public TitleCaseFilter(Writer out) {
        super(out);
    }

	@Override
	public void write(int c) throws IOException {
        out.write(inWhite ? Character.toUpperCase(c) : Character.toLowerCase(c));
        inWhite = Character.isWhitespace(c) || c == '"';
	}

}