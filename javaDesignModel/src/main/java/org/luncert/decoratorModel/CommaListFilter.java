package org.luncert.decoratorModel;

import java.io.IOException;
import java.io.Writer;

public class CommaListFilter extends BasicFilter {

    protected boolean needComma = false;

    public CommaListFilter(Writer out) {
        super(out);
    }

	@Override
	public void write(int c) throws IOException {
		if (needComma) {
            out.write(',');
            out.write(' ');
        }
        out.write(c);
        needComma = true;
    }
    
    @Override
    public void write(String s) throws IOException {
        if (needComma)
            out.write(", ");
        out.write(s);
        needComma = true;
    }

}