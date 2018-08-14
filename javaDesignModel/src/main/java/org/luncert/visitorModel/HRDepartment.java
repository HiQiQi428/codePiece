package org.luncert.visitorModel;

public class HRDepartment extends Department {

	@Override
    public void visit(ManagerEmployee me) {
        me.getTotalTimeSheet();
    }

	@Override
	public void visit(GeneralEmployee ge) {
        ge.getTotalTimeSheet();
	}

}