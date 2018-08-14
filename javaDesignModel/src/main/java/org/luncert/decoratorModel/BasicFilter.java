package org.luncert.decoratorModel;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public abstract class BasicFilter extends FilterWriter {

    protected BasicFilter(Writer out) {
        super(out);
    }

    public void write(char cbuf[], int offset, int length) throws IOException {
        for (int i = offset; i < length; i++)
            write(cbuf[i]);
    }

    public abstract void write(int c) throws IOException;

    public void write(String s, int offset, int length) throws IOException {
        write(s.toCharArray(), offset, length);
    }

}