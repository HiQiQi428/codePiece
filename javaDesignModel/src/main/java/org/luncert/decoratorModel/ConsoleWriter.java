package org.luncert.decoratorModel;

import java.io.IOException;
import java.io.Writer;

public class ConsoleWriter extends Writer {

    @Override
    public void write(int c) throws IOException {
        System.out.print((char)c);
    }

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < len; i++)
            write(cbuf[i]);
    }

	@Override
	public void flush() throws IOException {
		System.out.flush();
	}

	@Override
	public void close() throws IOException {
        // not supported now
	}

}