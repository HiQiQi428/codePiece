package org.luncert.memento;

/**
 * 管理者角色
 */
public class CareTaker {
    
    private EmpMemento memento;

    public EmpMemento getMemento() { return memento; }

    public void setMemento(EmpMemento memento) { this.memento = memento; }
}