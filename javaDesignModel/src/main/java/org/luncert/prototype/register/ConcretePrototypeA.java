package org.luncert.prototype.register;

public class ConcretePrototypeA implements Prototype {

    private String name;

	@Override
	public Prototype clone() {
        Prototype prototype = new ConcretePrototypeA();
        prototype.setName(name);
		return prototype;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
    }
    
    @Override
    public String toString() {
        return "Now in PrototypeA, name = " + name;
    }
    
}