package org.luncert.interpreterModel;

public abstract class OperatorExpression extends ArithmeticExpression {

    protected ArithmeticExpression arithmeticExpression1, arithmeticExpression2;

    public OperatorExpression(ArithmeticExpression arithmeticExpression1, ArithmeticExpression arithmeticExpression2) {
        this.arithmeticExpression1 = arithmeticExpression1;
        this.arithmeticExpression2 = arithmeticExpression2;
    }
    
    @Override
    public String toString() {
        return "(Add)";
    }

}