package org.luncert.interpreterModel;

public class AdditionExpression extends OperatorExpression {

    public AdditionExpression(ArithmeticExpression arithmeticExpression1, ArithmeticExpression arithmeticExpression2) {
        super(arithmeticExpression1, arithmeticExpression2);
    }

	@Override
	public int interpret() {
		return arithmeticExpression1.interpret() + arithmeticExpression2.interpret();
	}

}