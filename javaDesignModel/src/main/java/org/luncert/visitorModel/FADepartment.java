package org.luncert.visitorModel;

public class FADepartment extends Department {

	@Override
    public void visit(ManagerEmployee me) {
        String desc = new StringBuilder()
            .append("ManagerEmployee [name = ").append(me.getName())
            .append(", wage = ").append(me.getWage())
            .append(", punishmentTime = ").append(me.getPunishmentTime())
            .append(", actualWage = ").append(me.getTotalWage()).append("]").toString();
        System.out.println(desc);
    }

	@Override
	public void visit(GeneralEmployee ge) {
        String desc = new StringBuilder()
            .append("ManagerEmployee [name = ").append(ge.getName())
            .append(", wage = ").append(ge.getWage())
            .append(", punishmentTime = ").append(ge.getPunishmentTime())
            .append(", actualWage = ").append(ge.getTotalWage()).append("]").toString();
        System.out.println(desc);
	}

}