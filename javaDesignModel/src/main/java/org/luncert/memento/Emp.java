package org.luncert.memento;

/**
 * 发起者角色
 */
public class Emp {
    
    private String name;

    private int age;

    private int salary;

    public EmpMemento memento() {
        return new EmpMemento(this);
    }

    public void recovery(EmpMemento em) {
        this.name = em.getName();
        this.age = em.getAge();
        this.salary = em.getSalary();
    }

    public Emp(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public int getSalary() { return salary; }

    public void setSaraly(int salary) { this.salary = salary; }

    @Override
    public String toString() {
        return new StringBuilder()
                    .append("Emp [name = ").append(name)
                    .append(", age = ").append(age)
                    .append(", salary = ").append(salary)
                    .append("]").toString();
    }

}