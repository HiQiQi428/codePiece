package org.luncert.decoratorModel;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class RandomCaseFilter extends BasicFilter {

    private Random random = new Random();

    public RandomCaseFilter(Writer out) {
        super(out);
    }

	@Override
	public void write(int c) throws IOException {
        out.write(random.nextBoolean() ? Character.toUpperCase(c) : Character.toLowerCase(c));
	}

}