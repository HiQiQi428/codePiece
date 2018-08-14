package org.luncert.visitorModel;

public class GeneralEmployee extends Employee {

    private String name;

    private int timeSheet;

    private double wage;

    private int punishmentTime;

    public GeneralEmployee(String name, int timeSheet, double wage, int punishmentTime) {
        this.name = name;
        this.timeSheet = timeSheet;
        this.wage = wage;
        this.punishmentTime = punishmentTime;
    }

	@Override
	public void accept(Department department) {
        department.visit(this);
    }
    
    public int getTotalTimeSheet() {
        return timeSheet * 22 - punishmentTime;
    }

    public double getTotalWage() {
        return wage - punishmentTime * 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public int getPunishmentTime() {
        return punishmentTime;
    }

    public void setPunishmentTime(int punishmentTime) {
        this.punishmentTime = punishmentTime;
    }

}