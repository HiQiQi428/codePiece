package org.luncert.visitorModel;

public abstract class Department {

    public abstract void visit(ManagerEmployee me);

    public abstract void visit(GeneralEmployee ge);
    
}